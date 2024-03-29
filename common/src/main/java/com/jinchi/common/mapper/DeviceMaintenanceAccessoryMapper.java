package com.jinchi.common.mapper;

import com.jinchi.common.domain.DeviceMaintenanceAccessory;
import com.jinchi.common.domain.DeviceMaintenanceAccessoryExample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DeviceMaintenanceAccessoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_maintenance_accessory
     *
     * @mbggenerated
     */
    int countByExample(DeviceMaintenanceAccessoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_maintenance_accessory
     *
     * @mbggenerated
     */
    int deleteByExample(DeviceMaintenanceAccessoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_maintenance_accessory
     *
     * @mbggenerated
     */
    @Insert({
        "insert into device_maintenance_accessory (code, name, ",
        "specification, counts, ",
        "plan_code, units)",
        "values (#{code,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{specification,jdbcType=VARCHAR}, #{counts,jdbcType=INTEGER}, ",
        "#{planCode,jdbcType=BIGINT}, #{units,jdbcType=TINYINT})"
    })
    int insert(DeviceMaintenanceAccessory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_maintenance_accessory
     *
     * @mbggenerated
     */
    int insertSelective(DeviceMaintenanceAccessory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_maintenance_accessory
     *
     * @mbggenerated
     */
    List<DeviceMaintenanceAccessory> selectByExample(DeviceMaintenanceAccessoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_maintenance_accessory
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") DeviceMaintenanceAccessory record, @Param("example") DeviceMaintenanceAccessoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_maintenance_accessory
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") DeviceMaintenanceAccessory record, @Param("example") DeviceMaintenanceAccessoryExample example);
}