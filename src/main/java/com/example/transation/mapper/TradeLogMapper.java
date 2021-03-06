package com.example.transation.mapper;

import com.example.transation.entity.bean.TradeLog;
import com.example.transation.entity.bean.TradeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TradeLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_log
     *
     * @mbg.generated Mon Apr 27 20:37:21 CST 2020
     */
    long countByExample(TradeLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_log
     *
     * @mbg.generated Mon Apr 27 20:37:21 CST 2020
     */
    int deleteByExample(TradeLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_log
     *
     * @mbg.generated Mon Apr 27 20:37:21 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_log
     *
     * @mbg.generated Mon Apr 27 20:37:21 CST 2020
     */
    int insert(TradeLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_log
     *
     * @mbg.generated Mon Apr 27 20:37:21 CST 2020
     */
    int insertSelective(TradeLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_log
     *
     * @mbg.generated Mon Apr 27 20:37:21 CST 2020
     */
    List<TradeLog> selectByExample(TradeLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_log
     *
     * @mbg.generated Mon Apr 27 20:37:21 CST 2020
     */
    TradeLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_log
     *
     * @mbg.generated Mon Apr 27 20:37:21 CST 2020
     */
    int updateByExampleSelective(@Param("record") TradeLog record, @Param("example") TradeLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_log
     *
     * @mbg.generated Mon Apr 27 20:37:21 CST 2020
     */
    int updateByExample(@Param("record") TradeLog record, @Param("example") TradeLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_log
     *
     * @mbg.generated Mon Apr 27 20:37:21 CST 2020
     */
    int updateByPrimaryKeySelective(TradeLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_log
     *
     * @mbg.generated Mon Apr 27 20:37:21 CST 2020
     */
    int updateByPrimaryKey(TradeLog record);
}