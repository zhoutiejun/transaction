package com.example.transation.mapper;

import com.example.transation.entity.bean.Shares;
import com.example.transation.entity.bean.SharesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SharesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shares
     *
     * @mbg.generated Sat Apr 25 16:15:31 CST 2020
     */
    long countByExample(SharesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shares
     *
     * @mbg.generated Sat Apr 25 16:15:31 CST 2020
     */
    int deleteByExample(SharesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shares
     *
     * @mbg.generated Sat Apr 25 16:15:31 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shares
     *
     * @mbg.generated Sat Apr 25 16:15:31 CST 2020
     */
    int insert(Shares record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shares
     *
     * @mbg.generated Sat Apr 25 16:15:31 CST 2020
     */
    int insertSelective(Shares record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shares
     *
     * @mbg.generated Sat Apr 25 16:15:31 CST 2020
     */
    List<Shares> selectByExample(SharesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shares
     *
     * @mbg.generated Sat Apr 25 16:15:31 CST 2020
     */
    Shares selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shares
     *
     * @mbg.generated Sat Apr 25 16:15:31 CST 2020
     */
    int updateByExampleSelective(@Param("record") Shares record, @Param("example") SharesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shares
     *
     * @mbg.generated Sat Apr 25 16:15:31 CST 2020
     */
    int updateByExample(@Param("record") Shares record, @Param("example") SharesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shares
     *
     * @mbg.generated Sat Apr 25 16:15:31 CST 2020
     */
    int updateByPrimaryKeySelective(Shares record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shares
     *
     * @mbg.generated Sat Apr 25 16:15:31 CST 2020
     */
    int updateByPrimaryKey(Shares record);
}