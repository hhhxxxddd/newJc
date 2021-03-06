package com.jinchi.common.mapper;

import com.jinchi.common.domain.ProductionBatchDeviceMap;
import com.jinchi.common.domain.ProductionBatchDeviceMapExample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface ProductionBatchDeviceMapMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_batch_device_map
     *
     * @mbggenerated
     */
    int countByExample(ProductionBatchDeviceMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_batch_device_map
     *
     * @mbggenerated
     */
    int deleteByExample(ProductionBatchDeviceMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_batch_device_map
     *
     * @mbggenerated
     */
    @Insert({
        "insert into production_batch_device_map (code, batch_code, ",
        "batch, rule_detail_code, ",
        "rule_value, start_time, ",
        "end_time, device_code, ",
        "fixedassets_code, device_name, ",
        "specification)",
        "values (#{code,jdbcType=BIGINT}, #{batchCode,jdbcType=BIGINT}, ",
        "#{batch,jdbcType=VARCHAR}, #{ruleDetailCode,jdbcType=SMALLINT}, ",
        "#{ruleValue,jdbcType=VARCHAR}, #{startTime,jdbcType=TIMESTAMP}, ",
        "#{endTime,jdbcType=TIMESTAMP}, #{deviceCode,jdbcType=BIGINT}, ",
        "#{fixedassetsCode,jdbcType=VARCHAR}, #{deviceName,jdbcType=VARCHAR}, ",
        "#{specification,jdbcType=VARCHAR})"
    })
    int insert(ProductionBatchDeviceMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_batch_device_map
     *
     * @mbggenerated
     */
    int insertSelective(ProductionBatchDeviceMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_batch_device_map
     *
     * @mbggenerated
     */
    List<ProductionBatchDeviceMap> selectByExample(ProductionBatchDeviceMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_batch_device_map
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ProductionBatchDeviceMap record, @Param("example") ProductionBatchDeviceMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_batch_device_map
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ProductionBatchDeviceMap record, @Param("example") ProductionBatchDeviceMapExample example);
}