import pandas as pd
import numpy as np
import tushare as ts
from datetime import datetime
import matplotlib.pyplot as plt
from sklearn.ensemble import GradientBoostingRegressor
import warnings
import MySQLdb
import sys
warnings.filterwarnings('ignore')

# 获取数据
pro = ts.pro_api('c328246404e592350038a1c7d86e0bd99bfff609872cd464bbf68ef4')
df = pro.daily(ts_code=sys.argv[1])
initPrice = df.loc[0,"close"]
df = df.sort_values(by=['trade_date']).reset_index(drop=True)



# 基于K线数据构造几个滚动均值作为特征,总共5*3=15个特征
cols = ['close','open','high','low','vol']
Ns = [5,10,20]
fea_cols = []
for col in cols:
    for N in Ns:
        fea_cols.append(col+'_'+str(N))
        df[col+'_'+str(N)] = df[col].rolling(window=N).mean()
        
# 取出所需的列且丢弃前面的缺失值
df = df[['trade_date','pct_chg'] + fea_cols]
df = df.dropna().reset_index(drop=True)

# 切分训练和测试数据
cut_date = '2018-01-01'
df_train = df[df['trade_date'] < cut_date]
df_test = df[df['trade_date'] >= cut_date]

# 模型训练且基于模型对测试进行预测
model = GradientBoostingRegressor()
model.fit(df_train[fea_cols],df_train['pct_chg'])
preds = model.predict(df_test[fea_cols])

# 基于预测的涨跌幅，尝试基于多种阈值下的买入策略回测收益
df_test['pred'] = preds
vs = np.linspace(-2,2,11)
back_test_cols = []
xiabiao_list = []
for v in vs:
    back_test_cols.append('my_value_'+str(round(v,2)))
    xiabiao_list.append(str(round(v,2)))
    df_test['my_value_'+str(round(v,2))] = (df_test['pred'] > v)*df_test['pct_chg']
    
# 画出11种阈值下的回测收益走势
dd = df_test.copy()
dd.index = df_test['trade_date'].apply(lambda x:datetime.strptime(x,'%Y%m%d'))
(1+dd[back_test_cols]/100).cumprod().plot(figsize=(12,5))


# 显示11种阈值下的最终累计收益，可以看到阈值为-0.8时最终收益最大。
dd = (1+df_test[back_test_cols]/100).cumprod().reset_index(drop=True)
kk = dd.iloc[len(dd)-1,:]-1
# 初始值kk[0]
init = kk[0]
max_index = 0
for index in range(len(kk)):
	if float(kk[index]) > float(init):
		print('index:'+str(index)+"的值更大"+str(kk[index]))
		init = kk[index]
		max_index = index



# 预测的最大收益值 
predict_percent = float(1.0+float(xiabiao_list[max_index]))
print('预测百分比',predict_percent)

# 打开数据库连接
#db = MySQLdb.connect("cdb-2m498nqi.bj.tencentcdb.com", "xuanhui", "XUge123456", "transaction", charset='utf8',port= '10054' )
db = MySQLdb.connect(host = 'cdb-2m498nqi.bj.tencentcdb.com',user = 'xuanhui',passwd = 'XUge123456',db='transaction', connect_timeout=10,port= 10054)  

# 使用cursor()方法获取操作游标 
cursor = db.cursor()



# 查询数据库对应日期指定股票的数据
#querySql = "select price from shares_log where DATE_FORMAT(create_time,'%%Y-%%m-%%d') = DATE_FORMAT(now(),'%%Y-%%m-%%d') and share_num = '%s' " % (sys.argv[1])
#initPrice = 0;
#try:
#   # 执行SQL语句
#   cursor.execute(querySql)
   # 获取所有记录列表
#   results = cursor.fetchall()
#   for row in results:
#      initPrice = row[0]
#      # 打印结果
#      print ("price=%s"%initPrice)
#except Exception as e:
#   print("Error: unable to fecth data")
#   print(repr(e))
#   sys.exit(1)


# 更新股票预测价格语句
print('价格',str(initPrice))
print('预测价格',str(float(initPrice)*predict_percent))
insertSql = " INSERT INTO shares_log (share_num, price) VALUES ('%s', %f) " % (sys.argv[1], initPrice)
sql = "UPDATE shares SET predict_price=%f, predict_percent=%f, current_price=%f WHERE num='%s' " % (float(initPrice)*predict_percent, predict_percent,initPrice,sys.argv[1])
print(sql)
try:
   # 执行SQL语句
   cursor.execute(insertSql)
   cursor.execute(sql)
   # 提交到数据库执行
   db.commit()
except:
   # 发生错误时回滚
   db.rollback()

# 关闭数据库连接
db.close()

