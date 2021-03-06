package com.jinchi.app.mapper;

import com.jinchi.app.domain.BasicInfoDeviceProcess;
import com.jinchi.app.domain.BasicInfoDeviceProcessExample;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BasicInfoDeviceProcessMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_device_process
     *
     * @mbggenerated
     */
    int countByExample(BasicInfoDeviceProcessExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_device_process
     *
     * @mbggenerated
     */
    int deleteByExample(BasicInfoDeviceProcessExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_device_process
     *
     * @mbggenerated
     */
    @Delete({
            "delete from basic_info_device_process",
            "where code = #{code,jdbcType=SMALLINT}"
    })
    int deleteByPrimaryKey(Short code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_device_process
     *
     * @mbggenerated
     */
    @Insert({
            "insert into basic_info_device_process (process_name, dept_code)",
            "values (#{processName,jdbcType=VARCHAR}, #{deptCode,jdbcType=INTEGER})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "code", before = false, resultType = Short.class)
    int insert(BasicInfoDeviceProcess record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_device_process
     *
     * @mbggenerated
     */
    int insertSelective(BasicInfoDeviceProcess record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_device_process
     *
     * @mbggenerated
     */
    List<BasicInfoDeviceProcess> selectByExample(BasicInfoDeviceProcessExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_device_process
     *
     * @mbggenerated
     */
    @Select({
            "select",
            "code, process_name, dept_code",
            "from basic_info_device_process",
            "where code = #{code,jdbcType=SMALLINT}"
    })
    @ResultMap("BaseResultMap")
    BasicInfoDeviceProcess selectByPrimaryKey(Short code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_device_process
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BasicInfoDeviceProcess record, @Param("example") BasicInfoDeviceProcessExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_device_process
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BasicInfoDeviceProcess record, @Param("example") BasicInfoDeviceProcessExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_device_process
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BasicInfoDeviceProcess record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_device_process
     *
     * @mbggenerated
     */
    @Update({
            "update basic_info_device_process",
            "set process_name = #{processName,jdbcType=VARCHAR},",
            "dept_code = #{deptCode,jdbcType=INTEGER}",
            "where code = #{code,jdbcType=SMALLINT}"
    })
    int updateByPrimaryKey(BasicInfoDeviceProcess record);
}