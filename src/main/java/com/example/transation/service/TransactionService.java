package com.example.transation.service;

import com.example.transation.entity.bean.Shares;
import com.example.transation.entity.bean.TradeLog;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/4/25 0025 下午 14:18
 * @description :交易相关方法
 * @modified : zhoutiejun@youngyedu.com, 2020/4/25 0025 下午 14:18
 */
public interface TransactionService {
    /**
     * 卖股票
     * @param shareNum 股票代码
     * @param amount 股票数量
     * @param userId 用户id
     */
    String sellShare(String shareNum, BigDecimal amount, Integer userId);


    /**
     * 买股票
     * @param shareNum 股票代码
     * @param amount 股票数量
     * @param userId 用户id
     */
    String buyShare(String shareNum, BigDecimal amount, Integer userId);

    /**
     * @Description : 
     * @Author : zhoutiejun@youngyedu.com, 2020/4/25 0025  下午 15:44
     * @Modified : zhoutiejun@youngyedu.com, 2020/4/25 0025  下午 15:44
     * @Param 
     * @return 
     */
    void autoTransaction();

    /**
     * 列举所有股票
     * @return
     */
    List<Shares> listAllShares();

    /**
     * 列举用户交易
     * @param userId
     * @return
     */
    List<TradeLog> list(Integer userId);
}
