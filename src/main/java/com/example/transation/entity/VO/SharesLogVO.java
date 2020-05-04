package com.example.transation.entity.VO;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/4/25 0025 下午 21:16
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/4/25 0025 下午 21:16
 */
@Data
public class SharesLogVO {
    /** 股票编号*/
    private String shareNum;
    /** 股票名称*/
    private String name;
    /** 股票价格*/
    private BigDecimal price;
    /** 记录时间*/
    private String createTime;
}
