package com.example.transation.entity.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Account {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.id
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.user_id
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.share_num
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    private String shareNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.amount
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    private BigDecimal amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.create_time
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.update_time
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.id
     *
     * @return the value of account.id
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.id
     *
     * @param id the value for account.id
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.user_id
     *
     * @return the value of account.user_id
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.user_id
     *
     * @param userId the value for account.user_id
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.share_num
     *
     * @return the value of account.share_num
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    public String getShareNum() {
        return shareNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.share_num
     *
     * @param shareNum the value for account.share_num
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    public void setShareNum(String shareNum) {
        this.shareNum = shareNum == null ? null : shareNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.amount
     *
     * @return the value of account.amount
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.amount
     *
     * @param amount the value for account.amount
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.create_time
     *
     * @return the value of account.create_time
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.create_time
     *
     * @param createTime the value for account.create_time
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.update_time
     *
     * @return the value of account.update_time
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.update_time
     *
     * @param updateTime the value for account.update_time
     *
     * @mbg.generated Sat Apr 25 14:17:24 CST 2020
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}