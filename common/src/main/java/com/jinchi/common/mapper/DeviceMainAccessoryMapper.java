package com.jinchi.common.mapper;

import com.jinchi.common.domain.DeviceMainAccessory;
import com.jinchi.common.domain.DeviceMainAccessoryExample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DeviceMainAccessoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_accessory
     *
     * @mbggenerated
     */
    int countByExample(DeviceMainAccessoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_accessory
     *
     * @mbggenerated
     */
    int deleteByExample(DeviceMainAccessoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_accessory
     *
     * @mbggenerated
     */
    @Insert({
        "insert into device_main_accessory (code, name, ",
        "specification, counts, ",
        "main_code)",
        "values (#{code,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{specification,jdbcType=VARCHAR}, #{counts,jdbcType=INTEGER}, ",
        "#{mainCode,jdbcType=BIGINT})"
    })
    int insert(DeviceMainAccessory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_accessory
     *
     * @mbggenerated
     */
    int insertSelective(DeviceMainAccessory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_accessory
     *
     * @mbggenerated
     */
    List<DeviceMainAccessory> selectByExample(DeviceMainAccessoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_accessory
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") DeviceMainAccessory record, @Param("example") DeviceMainAccessoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_accessory
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") DeviceMainAccessory record, @Param("example") DeviceMainAccessoryExample example);
}