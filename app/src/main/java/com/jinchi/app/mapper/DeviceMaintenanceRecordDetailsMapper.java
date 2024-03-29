package com.jinchi.app.mapper;

import com.jinchi.app.domain.DeviceMaintenanceRecordDetails;
import com.jinchi.app.domain.DeviceMaintenanceRecordDetailsExample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DeviceMaintenanceRecordDetailsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_maintenance_record_details
     *
     * @mbggenerated
     */
    int countByExample(DeviceMaintenanceRecordDetailsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_maintenance_record_details
     *
     * @mbggenerated
     */
    int deleteByExample(DeviceMaintenanceRecordDetailsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_maintenance_record_details
     *
     * @mbggenerated
     */
    @Insert({
        "insert into device_maintenance_record_details (code, plan_code, ",
        "maintenance_items, maintenance_content, ",
        "opt_type, main_values, ",
        "main_content, items_code)",
        "values (#{code,jdbcType=BIGINT}, #{planCode,jdbcType=BIGINT}, ",
        "#{maintenanceItems,jdbcType=VARCHAR}, #{maintenanceContent,jdbcType=VARCHAR}, ",
        "#{optType,jdbcType=INTEGER}, #{mainValues,jdbcType=INTEGER}, ",
        "#{mainContent,jdbcType=VARCHAR}, #{itemsCode,jdbcType=BIGINT})"
    })
    int insert(DeviceMaintenanceRecordDetails record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_maintenance_record_details
     *
     * @mbggenerated
     */
    int insertSelective(DeviceMaintenanceRecordDetails record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_maintenance_record_details
     *
     * @mbggenerated
     */
    List<DeviceMaintenanceRecordDetails> selectByExample(DeviceMaintenanceRecordDetailsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_maintenance_record_details
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") DeviceMaintenanceRecordDetails record, @Param("example") DeviceMaintenanceRecordDetailsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_maintenance_record_details
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") DeviceMaintenanceRecordDetails record, @Param("example") DeviceMaintenanceRecordDetailsExample example);
}