package com.example.transation.schedule;

import com.example.transation.entity.bean.AccountExample;
import com.example.transation.entity.bean.Shares;
import com.example.transation.entity.bean.SharesExample;
import com.example.transation.mapper.AccountMapper;
import com.example.transation.mapper.SharesMapper;
import com.example.transation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/4/16 0016 下午 16:57
 * @description : 循环获取数据库的股票，然后分别进行预测，定时查询股票数据，然后进行购买或者抛售
 * @modified : zhoutiejun@youngyedu.com, 2020/4/16 0016 下午 16:57
 */

@Configuration      //1.主要用于标记配置类，兼备Component的效果。

public class PredictGuPiao {

    @Autowired
    private TransactionService transactionService;

    /**
     * 每天预测第二天的阈值情况
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void yuce(){
        List<Shares> sharesList = transactionService.listAllShares();

        String path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\example\\transation\\schedule\\py\\predict.py";

        // for 循环不同的股票进行预测
        for(Shares shares : sharesList){

            String[] arguments = new String[] {"python", path, shares.getNum()};
            try {
                Process process = Runtime.getRuntime().exec(arguments);
//            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//            in.close();
                //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
                //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
                int re = process.waitFor();
                System.out.println("调用脚本结果预测股票"+shares.getNum());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("====预测完毕，开始自动购买====");

        // 当股票涨跌预测完了之后 自动交易
        transactionService.autoTransaction();
    }

    public static void main(String[] args) {

        String path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\example\\transation\\schedule\\py\\predict.py";

        String[] arguments = new String[] {"python", path,"000002.SZ"};
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            int re = process.waitFor();
            System.out.println(re);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
