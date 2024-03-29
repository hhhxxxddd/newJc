package com.jinchi.common.mapper;

import com.jinchi.common.domain.ProductStorageStatisticByLineDetail;
import com.jinchi.common.domain.ProductStorageStatisticByLineDetailExample;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface ProductStorageStatisticByLineDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_storage_statistic_by_line_detail
     *
     * @mbggenerated
     */
    int countByExample(ProductStorageStatisticByLineDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_storage_statistic_by_line_detail
     *
     * @mbggenerated
     */
    int deleteByExample(ProductStorageStatisticByLineDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_storage_statistic_by_line_detail
     *
     * @mbggenerated
     */
    @Delete({
        "delete from product_storage_statistic_by_line_detail",
        "where code = #{code,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_storage_statistic_by_line_detail
     *
     * @mbggenerated
     */
    @Insert({
        "insert into product_storage_statistic_by_line_detail (statistic_code, periods, ",
        "line_code, totals, ni_value, ",
        "co_value, mn_value)",
        "values (#{statisticCode,jdbcType=BIGINT}, #{periods,jdbcType=INTEGER}, ",
        "#{lineCode,jdbcType=INTEGER}, #{totals,jdbcType=REAL}, #{niValue,jdbcType=REAL}, ",
        "#{coValue,jdbcType=REAL}, #{mnValue,jdbcType=REAL})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="code", before=false, resultType=Long.class)
    int insert(ProductStorageStatisticByLineDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_storage_statistic_by_line_detail
     *
     * @mbggenerated
     */
    int insertSelective(ProductStorageStatisticByLineDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_storage_statistic_by_line_detail
     *
     * @mbggenerated
     */
    List<ProductStorageStatisticByLineDetail> selectByExample(ProductStorageStatisticByLineDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_storage_statistic_by_line_detail
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "code, statistic_code, periods, line_code, totals, ni_value, co_value, mn_value",
        "from product_storage_statistic_by_line_detail",
        "where code = #{code,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    ProductStorageStatisticByLineDetail selectByPrimaryKey(Long code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_storage_statistic_by_line_detail
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ProductStorageStatisticByLineDetail record, @Param("example") ProductStorageStatisticByLineDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_storage_statistic_by_line_detail
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ProductStorageStatisticByLineDetail record, @Param("example") ProductStorageStatisticByLineDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_storage_statistic_by_line_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ProductStorageStatisticByLineDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_storage_statistic_by_line_detail
     *
     * @mbggenerated
     */
    @Update({
        "update product_storage_statistic_by_line_detail",
        "set statistic_code = #{statisticCode,jdbcType=BIGINT},",
          "periods = #{periods,jdbcType=INTEGER},",
          "line_code = #{lineCode,jdbcType=INTEGER},",
          "totals = #{totals,jdbcType=REAL},",
          "ni_value = #{niValue,jdbcType=REAL},",
          "co_value = #{coValue,jdbcType=REAL},",
          "mn_value = #{mnValue,jdbcType=REAL}",
        "where code = #{code,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ProductStorageStatisticByLineDetail record);
}