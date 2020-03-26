package com.jinchi.app.mapper.fireMage;

import com.jinchi.app.domain.fireMage.FireMageDept;
import com.jinchi.app.domain.fireMage.FireMageDeptExample;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FireMageDeptMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_dept
     *
     * @mbggenerated
     */
    int countByExample(FireMageDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_dept
     *
     * @mbggenerated
     */
    int deleteByExample(FireMageDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_dept
     *
     * @mbggenerated
     */
    @Delete({
        "delete from fire_mage_dept",
        "where code = #{code,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_dept
     *
     * @mbggenerated
     */
    @Insert({
        "insert into fire_mage_dept (dept_name, descr)",
        "values (#{deptName,jdbcType=VARCHAR}, #{descr,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="code", before=false, resultType=Integer.class)
    int insert(FireMageDept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_dept
     *
     * @mbggenerated
     */
    int insertSelective(FireMageDept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_dept
     *
     * @mbggenerated
     */
    List<FireMageDept> selectByExample(FireMageDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_dept
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "code, dept_name, descr",
        "from fire_mage_dept",
        "where code = #{code,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    FireMageDept selectByPrimaryKey(Integer code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_dept
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") FireMageDept record, @Param("example") FireMageDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_dept
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") FireMageDept record, @Param("example") FireMageDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_dept
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FireMageDept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_dept
     *
     * @mbggenerated
     */
    @Update({
        "update fire_mage_dept",
        "set dept_name = #{deptName,jdbcType=VARCHAR},",
          "descr = #{descr,jdbcType=VARCHAR}",
        "where code = #{code,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FireMageDept record);
}