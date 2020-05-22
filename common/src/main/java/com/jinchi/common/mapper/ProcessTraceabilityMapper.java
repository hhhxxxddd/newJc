package com.jinchi.common.mapper;

import com.jinchi.common.domain.ProcessTraceability;
import com.jinchi.common.domain.ProcessTraceabilityExample;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface ProcessTraceabilityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    int countByExample(ProcessTraceabilityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    int deleteByExample(ProcessTraceabilityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    @Delete({
        "delete from processtraceability",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    @Insert({
        "insert into processtraceability (SingleTraceCode, Quality, ",
        "PositiveAndNegativePowderBatchNumber, ",
        "SolutionBatchNumber)",
        "values (#{singletracecode,jdbcType=VARCHAR}, #{quality,jdbcType=VARCHAR}, ",
        "#{positiveandnegativepowderbatchnumber,jdbcType=VARCHAR}, ",
        "#{solutionbatchnumber,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(ProcessTraceability record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    int insertSelective(ProcessTraceability record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    List<ProcessTraceability> selectByExample(ProcessTraceabilityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "id, SingleTraceCode, Quality, PositiveAndNegativePowderBatchNumber, SolutionBatchNumber",
        "from processtraceability",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    ProcessTraceability selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ProcessTraceability record, @Param("example") ProcessTraceabilityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ProcessTraceability record, @Param("example") ProcessTraceabilityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ProcessTraceability record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    @Update({
        "update processtraceability",
        "set SingleTraceCode = #{singletracecode,jdbcType=VARCHAR},",
          "Quality = #{quality,jdbcType=VARCHAR},",
          "PositiveAndNegativePowderBatchNumber = #{positiveandnegativepowderbatchnumber,jdbcType=VARCHAR},",
          "SolutionBatchNumber = #{solutionbatchnumber,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ProcessTraceability record);
}
