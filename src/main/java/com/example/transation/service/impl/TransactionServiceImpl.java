package com.example.transation.service.impl;

import com.example.transation.entity.bean.*;
import com.example.transation.mapper.AccountMapper;
import com.example.transation.mapper.SharesLogMapper;
import com.example.transation.mapper.SharesMapper;
import com.example.transation.mapper.TradeLogMapper;
import com.example.transation.service.TransactionService;
import freemarker.template.utility.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/4/25 0025 下午 14:18
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/4/25 0025 下午 14:18
 */
@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private SharesLogMapper sharesLogMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private TradeLogMapper tradeLogMapper;

    @Autowired
    private SharesMapper sharesMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String sellShare(String shareNum, BigDecimal amount, Integer userId) {
        // 首先查询该股票价格，然后卖出
        SharesExample sharesExample = new SharesExample();
        sharesExample.createCriteria().andNumEqualTo(shareNum);

        List<Shares> sharesList = sharesMapper.selectByExample(sharesExample);
        if (CollectionUtils.isEmpty(sharesList)) {
            return "卖出失败";
        }
        Shares shares= sharesList.get(0);
        // 当前股票价格
        BigDecimal price = shares.getCurrentPrice();
        // 当前股票价值
        BigDecimal sum = amount.multiply(price);

        // 扣掉用户的原来的股票对应账户  增加用户的rmb账户

        // 扣掉对应股票账户
        String res1 = changeAccount(userId, shareNum, BigDecimal.ZERO.subtract(amount));

        // 增加对应RMB账户
        String res2 = changeAccount(userId, "RMB", sum);

        if (StringUtils.isNotBlank(res1) || StringUtils.isNotBlank(res2)){
            return "卖出失败";
        }
        //记录trade_log里面去
        TradeLog tradeLog = new TradeLog();
        // 机器操作
        tradeLog.setOperateType(0);
        tradeLog.setShareNum(shareNum);
        tradeLog.setAmount(amount);
        // 卖出
        tradeLog.setType(1);
        tradeLog.setUserId(userId);
        tradeLog.setCostRmb(sum);
        int res3 = tradeLogMapper.insertSelective(tradeLog);
        if (res3 >= 1){
            return "";
        }
        return "卖出失败";
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String buyShare(String shareNum, BigDecimal amount, Integer userId) {
        // 首先查询该股票价格，然后卖出
        SharesExample sharesExample = new SharesExample();
        sharesExample.createCriteria().andNumEqualTo(shareNum);

        List<Shares> sharesList = sharesMapper.selectByExample(sharesExample);
        if (CollectionUtils.isEmpty(sharesList)) {
            return "买入失败";
        }
        Shares shares= sharesList.get(0);
        // 当前股票价格
        BigDecimal price = shares.getCurrentPrice();
        // 当前股票价值
        BigDecimal sum = amount.multiply(price);


        // 增加对应股票账户
        String res1 = changeAccount(userId, shareNum, amount);

        // 减少对应RMB账户
        String res2 = changeAccount(userId, "RMB", BigDecimal.ZERO.subtract(sum));

        if (StringUtils.isNotBlank(res1) || StringUtils.isNotBlank(res2)){
            return "买入失败";
        }

        //记录trade_log里面去
        TradeLog tradeLog = new TradeLog();
        // 机器操作
        tradeLog.setOperateType(0);
        tradeLog.setShareNum(shareNum);
        tradeLog.setAmount(amount);
        // 卖出
        tradeLog.setType(0);
        tradeLog.setCostRmb(sum);
        tradeLog.setUserId(userId);
        int res3 = tradeLogMapper.insertSelective(tradeLog);
        if (res3 >= 1){
            return "";
        }
        return "买入失败";
    }

    @Override
    public List<TradeLog> list(Integer userId) {
        TradeLogExample tradeLogExample = new TradeLogExample();
        tradeLogExample.setOrderByClause(" create_time desc");
        tradeLogExample.createCriteria().andUserIdEqualTo(userId);
        return tradeLogMapper.selectByExample(tradeLogExample);
    }

    @Override
    public void autoTransaction() {
        /**
         * for 循环股票 进行股票的交易  如果预测大于1  就需要买入否则需要卖出
         * 然后再for 循环用户  ， 判断 用户是或否有股票， 有就卖出或者买入
         */
        List<Shares> sharesList = sharesMapper.selectByExample(null);
        for (Shares shares : sharesList) {
            // 股票编码
            String shareNum = shares.getNum();
            System.out.println("====开始股票的自动买卖====股票编号"+shareNum);
            BigDecimal currentPrice = shares.getCurrentPrice();

            // 循环有这只股票的用户
            AccountExample accountExample = new AccountExample();
            accountExample.createCriteria().andShareNumEqualTo(shareNum);
            List<Account> accountList = accountMapper.selectByExample(accountExample);
            for (Account account : accountList) {
                BigDecimal amount = account.getAmount();
                Integer userId = account.getUserId();

                // 没有改变就不动
                if (shares.getPredictPercent().compareTo(BigDecimal.ONE) == 0) {
                    continue;
                }

                // 判断，买进还是卖出
                if (shares.getPredictPercent().compareTo(BigDecimal.ONE) > 0) {
//                    accountExample.clear();
//                    accountExample.createCriteria().andShareNumEqualTo("RMB").andUserIdEqualTo(userId);
//                    List<Account> rmbAccountList = accountMapper.selectByExample(accountExample);
//                    Account rmbAccount = rmbAccountList.get(0);
//                    // 账户余额
//                    BigDecimal rmbAmount = rmbAccount.getAmount();
//                    // 如果用户账户不够买两份就不买了
//                    if (currentPrice.add(currentPrice).compareTo(rmbAmount) > 0) {
//                        continue;
//                    }
//
//                    System.out.println("====自动买入的账户====用户编号"+userId);
//
//                    // 应该买多少份
//                    BigDecimal shouldBuy = rmbAmount.divide(currentPrice.add(currentPrice), 2, BigDecimal.ROUND_DOWN);
//
//                    // 预测会涨， 所以要买进 看看账户有多少钱 花账户一半的钱买进
//                    buyShare(shareNum, shouldBuy, userId);
                } else {
                    // 预测会跌， 所以要卖出 全卖出
                    System.out.println("====自动卖出的账户====用户编号"+userId);
                    sellShare(shareNum, amount, userId);
                }
            }
        }
        System.out.println("====结束自动买卖====");
    }

    @Override
    public List<Shares> listAllShares() {
        return sharesMapper.selectByExample(null);
    }

    private String changeAccount(Integer userId, String shareNum, BigDecimal amount) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andShareNumEqualTo(shareNum)
                .andUserIdEqualTo(userId);
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        if (CollectionUtils.isEmpty(accountList)) {
            if(amount.compareTo(BigDecimal.ZERO) <= 0){
                return "操作失败";
            }
            Account account = new Account();
            account.setUserId(userId);
            account.setShareNum(shareNum);
            account.setAmount(amount);
            accountMapper.insertSelective(account);
        }else{
            // 更新账户对应股票
            Account account = accountList.get(0);
            if(account.getAmount().add(amount).compareTo(BigDecimal.ZERO) < 0){
                throw new IllegalArgumentException("账户余额不足");
            }

            account.setAmount(account.getAmount().add(amount));
            int res = accountMapper.updateByPrimaryKeySelective(account);
            if (res >= 1){
                return "";
            }
            return "操作失败";
        }
        return "";
    }
}
