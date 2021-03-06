package com.jinchi.common.mapper;

import com.jinchi.common.domain.BasicInfoAnodeMaterialLineMap;
import com.jinchi.common.domain.BasicInfoAnodeMaterialLineMapExample;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface BasicInfoAnodeMaterialLineMapMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_anode_material_line_map
     *
     * @mbggenerated
     */
    int countByExample(BasicInfoAnodeMaterialLineMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_anode_material_line_map
     *
     * @mbggenerated
     */
    int deleteByExample(BasicInfoAnodeMaterialLineMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_anode_material_line_map
     *
     * @mbggenerated
     */
    @Delete({
        "delete from basic_info_anode_material_line_map",
        "where code = #{code,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_anode_material_line_map
     *
     * @mbggenerated
     */
    @Insert({
        "insert into basic_info_anode_material_line_map (material_code, line_code)",
        "values (#{materialCode,jdbcType=INTEGER}, #{lineCode,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="code", before=false, resultType=Integer.class)
    int insert(BasicInfoAnodeMaterialLineMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_anode_material_line_map
     *
     * @mbggenerated
     */
    int insertSelective(BasicInfoAnodeMaterialLineMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_anode_material_line_map
     *
     * @mbggenerated
     */
    List<BasicInfoAnodeMaterialLineMap> selectByExample(BasicInfoAnodeMaterialLineMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_anode_material_line_map
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "code, material_code, line_code",
        "from basic_info_anode_material_line_map",
        "where code = #{code,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    BasicInfoAnodeMaterialLineMap selectByPrimaryKey(Integer code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_anode_material_line_map
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BasicInfoAnodeMaterialLineMap record, @Param("example") BasicInfoAnodeMaterialLineMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_anode_material_line_map
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BasicInfoAnodeMaterialLineMap record, @Param("example") BasicInfoAnodeMaterialLineMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_anode_material_line_map
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BasicInfoAnodeMaterialLineMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_anode_material_line_map
     *
     * @mbggenerated
     */
    @Update({
        "update basic_info_anode_material_line_map",
        "set material_code = #{materialCode,jdbcType=INTEGER},",
          "line_code = #{lineCode,jdbcType=INTEGER}",
        "where code = #{code,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BasicInfoAnodeMaterialLineMap record);
}