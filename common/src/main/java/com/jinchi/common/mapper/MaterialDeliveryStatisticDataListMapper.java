package com.jinchi.common.mapper;

import com.jinchi.common.domain.MaterialDeliveryStatisticDataList;
import com.jinchi.common.domain.MaterialDeliveryStatisticDataListExample;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface MaterialDeliveryStatisticDataListMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_delivery_statistic_data_list
     *
     * @mbggenerated
     */
    int countByExample(MaterialDeliveryStatisticDataListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_delivery_statistic_data_list
     *
     * @mbggenerated
     */
    int deleteByExample(MaterialDeliveryStatisticDataListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_delivery_statistic_data_list
     *
     * @mbggenerated
     */
    @Delete({
        "delete from material_delivery_statistic_data_list",
        "where code = #{code,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_delivery_statistic_data_list
     *
     * @mbggenerated
     */
    @Insert({
        "insert into material_delivery_statistic_data_list (statistic_code, periods, ",
        "data_type, material_type_code, ",
        "material_type_name, totals, ",
        "ni_value, co_value, mn_value)",
        "values (#{statisticCode,jdbcType=BIGINT}, #{periods,jdbcType=INTEGER}, ",
        "#{dataType,jdbcType=TINYINT}, #{materialTypeCode,jdbcType=INTEGER}, ",
        "#{materialTypeName,jdbcType=VARCHAR}, #{totals,jdbcType=REAL}, ",
        "#{niValue,jdbcType=REAL}, #{coValue,jdbcType=REAL}, #{mnValue,jdbcType=REAL})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="code", before=false, resultType=Long.class)
    int insert(MaterialDeliveryStatisticDataList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_delivery_statistic_data_list
     *
     * @mbggenerated
     */
    int insertSelective(MaterialDeliveryStatisticDataList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_delivery_statistic_data_list
     *
     * @mbggenerated
     */
    List<MaterialDeliveryStatisticDataList> selectByExample(MaterialDeliveryStatisticDataListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_delivery_statistic_data_list
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "code, statistic_code, periods, data_type, material_type_code, material_type_name, ",
        "totals, ni_value, co_value, mn_value",
        "from material_delivery_statistic_data_list",
        "where code = #{code,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    MaterialDeliveryStatisticDataList selectByPrimaryKey(Long code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_delivery_statistic_data_list
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MaterialDeliveryStatisticDataList record, @Param("example") MaterialDeliveryStatisticDataListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_delivery_statistic_data_list
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MaterialDeliveryStatisticDataList record, @Param("example") MaterialDeliveryStatisticDataListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_delivery_statistic_data_list
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MaterialDeliveryStatisticDataList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_delivery_statistic_data_list
     *
     * @mbggenerated
     */
    @Update({
        "update material_delivery_statistic_data_list",
        "set statistic_code = #{statisticCode,jdbcType=BIGINT},",
          "periods = #{periods,jdbcType=INTEGER},",
          "data_type = #{dataType,jdbcType=TINYINT},",
          "material_type_code = #{materialTypeCode,jdbcType=INTEGER},",
          "material_type_name = #{materialTypeName,jdbcType=VARCHAR},",
          "totals = #{totals,jdbcType=REAL},",
          "ni_value = #{niValue,jdbcType=REAL},",
          "co_value = #{coValue,jdbcType=REAL},",
          "mn_value = #{mnValue,jdbcType=REAL}",
        "where code = #{code,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MaterialDeliveryStatisticDataList record);
}