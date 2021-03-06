package com.jinchi.common.mapper;

import com.jinchi.common.domain.AnodeCostAccountingStatisticTotals;
import com.jinchi.common.domain.AnodeCostAccountingStatisticTotalsExample;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface AnodeCostAccountingStatisticTotalsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table anode_cost_accounting_statistic_totals
     *
     * @mbggenerated
     */
    int countByExample(AnodeCostAccountingStatisticTotalsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table anode_cost_accounting_statistic_totals
     *
     * @mbggenerated
     */
    int deleteByExample(AnodeCostAccountingStatisticTotalsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table anode_cost_accounting_statistic_totals
     *
     * @mbggenerated
     */
    @Delete({
        "delete from anode_cost_accounting_statistic_totals",
        "where code = #{code,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table anode_cost_accounting_statistic_totals
     *
     * @mbggenerated
     */
    @Insert({
        "insert into anode_cost_accounting_statistic_totals (statistic_code, periods, ",
        "material_requisitions, material_balance, ",
        "last_material_in_process_first, current_goods_in_process_first, ",
        "last_goods_in_process_second, current_goods_in_process_second, ",
        "unit_consumption_precursor, unit_consumption_neurolithium, ",
        "product_storage)",
        "values (#{statisticCode,jdbcType=BIGINT}, #{periods,jdbcType=INTEGER}, ",
        "#{materialRequisitions,jdbcType=REAL}, #{materialBalance,jdbcType=REAL}, ",
        "#{lastMaterialInProcessFirst,jdbcType=REAL}, #{currentGoodsInProcessFirst,jdbcType=REAL}, ",
        "#{lastGoodsInProcessSecond,jdbcType=REAL}, #{currentGoodsInProcessSecond,jdbcType=REAL}, ",
        "#{unitConsumptionPrecursor,jdbcType=REAL}, #{unitConsumptionNeurolithium,jdbcType=REAL}, ",
        "#{productStorage,jdbcType=REAL})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="code", before=false, resultType=Long.class)
    int insert(AnodeCostAccountingStatisticTotals record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table anode_cost_accounting_statistic_totals
     *
     * @mbggenerated
     */
    int insertSelective(AnodeCostAccountingStatisticTotals record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table anode_cost_accounting_statistic_totals
     *
     * @mbggenerated
     */
    List<AnodeCostAccountingStatisticTotals> selectByExample(AnodeCostAccountingStatisticTotalsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table anode_cost_accounting_statistic_totals
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "code, statistic_code, periods, material_requisitions, material_balance, last_material_in_process_first, ",
        "current_goods_in_process_first, last_goods_in_process_second, current_goods_in_process_second, ",
        "unit_consumption_precursor, unit_consumption_neurolithium, product_storage",
        "from anode_cost_accounting_statistic_totals",
        "where code = #{code,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    AnodeCostAccountingStatisticTotals selectByPrimaryKey(Long code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table anode_cost_accounting_statistic_totals
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") AnodeCostAccountingStatisticTotals record, @Param("example") AnodeCostAccountingStatisticTotalsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table anode_cost_accounting_statistic_totals
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") AnodeCostAccountingStatisticTotals record, @Param("example") AnodeCostAccountingStatisticTotalsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table anode_cost_accounting_statistic_totals
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AnodeCostAccountingStatisticTotals record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table anode_cost_accounting_statistic_totals
     *
     * @mbggenerated
     */
    @Update({
        "update anode_cost_accounting_statistic_totals",
        "set statistic_code = #{statisticCode,jdbcType=BIGINT},",
          "periods = #{periods,jdbcType=INTEGER},",
          "material_requisitions = #{materialRequisitions,jdbcType=REAL},",
          "material_balance = #{materialBalance,jdbcType=REAL},",
          "last_material_in_process_first = #{lastMaterialInProcessFirst,jdbcType=REAL},",
          "current_goods_in_process_first = #{currentGoodsInProcessFirst,jdbcType=REAL},",
          "last_goods_in_process_second = #{lastGoodsInProcessSecond,jdbcType=REAL},",
          "current_goods_in_process_second = #{currentGoodsInProcessSecond,jdbcType=REAL},",
          "unit_consumption_precursor = #{unitConsumptionPrecursor,jdbcType=REAL},",
          "unit_consumption_neurolithium = #{unitConsumptionNeurolithium,jdbcType=REAL},",
          "product_storage = #{productStorage,jdbcType=REAL}",
        "where code = #{code,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(AnodeCostAccountingStatisticTotals record);
}