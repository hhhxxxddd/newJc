package com.jinchi.app.mapper.fireMage;

import com.jinchi.app.domain.fireMage.FireMageNumberUnit;
import com.jinchi.app.domain.fireMage.FireMageNumberUnitExample;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FireMageNumberUnitMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_number_unit
     *
     * @mbggenerated
     */
    int countByExample(FireMageNumberUnitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_number_unit
     *
     * @mbggenerated
     */
    int deleteByExample(FireMageNumberUnitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_number_unit
     *
     * @mbggenerated
     */
    @Delete({
        "delete from fire_mage_number_unit",
        "where code = #{code,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_number_unit
     *
     * @mbggenerated
     */
    @Insert({
        "insert into fire_mage_number_unit (value, description, ",
        "enable)",
        "values (#{value,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{enable,jdbcType=BIT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="code", before=false, resultType=Integer.class)
    int insert(FireMageNumberUnit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_number_unit
     *
     * @mbggenerated
     */
    int insertSelective(FireMageNumberUnit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_number_unit
     *
     * @mbggenerated
     */
    List<FireMageNumberUnit> selectByExample(FireMageNumberUnitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_number_unit
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "code, value, description, enable",
        "from fire_mage_number_unit",
        "where code = #{code,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    FireMageNumberUnit selectByPrimaryKey(Integer code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_number_unit
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") FireMageNumberUnit record, @Param("example") FireMageNumberUnitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_number_unit
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") FireMageNumberUnit record, @Param("example") FireMageNumberUnitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_number_unit
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FireMageNumberUnit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_number_unit
     *
     * @mbggenerated
     */
    @Update({
        "update fire_mage_number_unit",
        "set value = #{value,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "enable = #{enable,jdbcType=BIT}",
        "where code = #{code,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FireMageNumberUnit record);
}