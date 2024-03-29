package com.jinchi.common.mapper;

import com.jinchi.common.domain.CostAccountingStatisticByLineDetail;
import com.jinchi.common.domain.CostAccountingStatisticByLineDetailExample;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface CostAccountingStatisticByLineDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost_accounting_statistic_by_line_detail
     *
     * @mbggenerated
     */
    int countByExample(CostAccountingStatisticByLineDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost_accounting_statistic_by_line_detail
     *
     * @mbggenerated
     */
    int deleteByExample(CostAccountingStatisticByLineDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost_accounting_statistic_by_line_detail
     *
     * @mbggenerated
     */
    @Delete({
        "delete from cost_accounting_statistic_by_line_detail",
        "where code = #{code,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost_accounting_statistic_by_line_detail
     *
     * @mbggenerated
     */
    @Insert({
        "insert into cost_accounting_statistic_by_line_detail (statistic_code, periods, ",
        "line_code, material_type, ",
        "element_type, material_requisitions, ",
        "current_goods_in_process, last_goods_in_process, ",
        "product_storage, intermediate_products_variation, ",
        "unit_consumption)",
        "values (#{statisticCode,jdbcType=BIGINT}, #{periods,jdbcType=INTEGER}, ",
        "#{lineCode,jdbcType=INTEGER}, #{materialType,jdbcType=TINYINT}, ",
        "#{elementType,jdbcType=TINYINT}, #{materialRequisitions,jdbcType=REAL}, ",
        "#{currentGoodsInProcess,jdbcType=REAL}, #{lastGoodsInProcess,jdbcType=REAL}, ",
        "#{productStorage,jdbcType=REAL}, #{intermediateProductsVariation,jdbcType=REAL}, ",
        "#{unitConsumption,jdbcType=REAL})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="code", before=false, resultType=Long.class)
    int insert(CostAccountingStatisticByLineDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost_accounting_statistic_by_line_detail
     *
     * @mbggenerated
     */
    int insertSelective(CostAccountingStatisticByLineDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost_accounting_statistic_by_line_detail
     *
     * @mbggenerated
     */
    List<CostAccountingStatisticByLineDetail> selectByExample(CostAccountingStatisticByLineDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost_accounting_statistic_by_line_detail
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "code, statistic_code, periods, line_code, material_type, element_type, material_requisitions, ",
        "current_goods_in_process, last_goods_in_process, product_storage, intermediate_products_variation, ",
        "unit_consumption",
        "from cost_accounting_statistic_by_line_detail",
        "where code = #{code,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    CostAccountingStatisticByLineDetail selectByPrimaryKey(Long code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost_accounting_statistic_by_line_detail
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CostAccountingStatisticByLineDetail record, @Param("example") CostAccountingStatisticByLineDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost_accounting_statistic_by_line_detail
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CostAccountingStatisticByLineDetail record, @Param("example") CostAccountingStatisticByLineDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost_accounting_statistic_by_line_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CostAccountingStatisticByLineDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cost_accounting_statistic_by_line_detail
     *
     * @mbggenerated
     */
    @Update({
        "update cost_accounting_statistic_by_line_detail",
        "set statistic_code = #{statisticCode,jdbcType=BIGINT},",
          "periods = #{periods,jdbcType=INTEGER},",
          "line_code = #{lineCode,jdbcType=INTEGER},",
          "material_type = #{materialType,jdbcType=TINYINT},",
          "element_type = #{elementType,jdbcType=TINYINT},",
          "material_requisitions = #{materialRequisitions,jdbcType=REAL},",
          "current_goods_in_process = #{currentGoodsInProcess,jdbcType=REAL},",
          "last_goods_in_process = #{lastGoodsInProcess,jdbcType=REAL},",
          "product_storage = #{productStorage,jdbcType=REAL},",
          "intermediate_products_variation = #{intermediateProductsVariation,jdbcType=REAL},",
          "unit_consumption = #{unitConsumption,jdbcType=REAL}",
        "where code = #{code,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CostAccountingStatisticByLineDetail record);
}