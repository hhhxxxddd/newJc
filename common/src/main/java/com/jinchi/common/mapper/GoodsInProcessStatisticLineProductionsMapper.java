package com.jinchi.common.mapper;

import com.jinchi.common.domain.GoodsInProcessStatisticLineProductions;
import com.jinchi.common.domain.GoodsInProcessStatisticLineProductionsExample;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface GoodsInProcessStatisticLineProductionsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_line_productions
     *
     * @mbggenerated
     */
    int countByExample(GoodsInProcessStatisticLineProductionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_line_productions
     *
     * @mbggenerated
     */
    int deleteByExample(GoodsInProcessStatisticLineProductionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_line_productions
     *
     * @mbggenerated
     */
    @Delete({
        "delete from goods_in_process_statistic_line_productions",
        "where code = #{code,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_line_productions
     *
     * @mbggenerated
     */
    @Insert({
        "insert into goods_in_process_statistic_line_productions (statistic_code, process_code, ",
        "line_code, production_type)",
        "values (#{statisticCode,jdbcType=BIGINT}, #{processCode,jdbcType=INTEGER}, ",
        "#{lineCode,jdbcType=INTEGER}, #{productionType,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="code", before=false, resultType=Long.class)
    int insert(GoodsInProcessStatisticLineProductions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_line_productions
     *
     * @mbggenerated
     */
    int insertSelective(GoodsInProcessStatisticLineProductions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_line_productions
     *
     * @mbggenerated
     */
    List<GoodsInProcessStatisticLineProductions> selectByExample(GoodsInProcessStatisticLineProductionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_line_productions
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "code, statistic_code, process_code, line_code, production_type",
        "from goods_in_process_statistic_line_productions",
        "where code = #{code,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    GoodsInProcessStatisticLineProductions selectByPrimaryKey(Long code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_line_productions
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") GoodsInProcessStatisticLineProductions record, @Param("example") GoodsInProcessStatisticLineProductionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_line_productions
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") GoodsInProcessStatisticLineProductions record, @Param("example") GoodsInProcessStatisticLineProductionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_line_productions
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(GoodsInProcessStatisticLineProductions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_line_productions
     *
     * @mbggenerated
     */
    @Update({
        "update goods_in_process_statistic_line_productions",
        "set statistic_code = #{statisticCode,jdbcType=BIGINT},",
          "process_code = #{processCode,jdbcType=INTEGER},",
          "line_code = #{lineCode,jdbcType=INTEGER},",
          "production_type = #{productionType,jdbcType=VARCHAR}",
        "where code = #{code,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(GoodsInProcessStatisticLineProductions record);
}