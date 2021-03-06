package com.jinchi.common.mapper;

import com.jinchi.common.domain.SwmsStockOutRecordHead;
import com.jinchi.common.domain.SwmsStockOutRecordHeadExample;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SwmsStockOutRecordHeadMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_stock_out_record_head
     *
     * @mbggenerated
     */
    int countByExample(SwmsStockOutRecordHeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_stock_out_record_head
     *
     * @mbggenerated
     */
    int deleteByExample(SwmsStockOutRecordHeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_stock_out_record_head
     *
     * @mbggenerated
     */
    @Delete({
        "delete from SWMS_stock_out_record_head",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_stock_out_record_head
     *
     * @mbggenerated
     */
    @Insert({
        "insert into SWMS_stock_out_record_head (application_form_id, stock_out_record_head_code, ",
        "dept_code, hf_line_code, ",
        "sf_line_code, delivery_type_code, ",
        "delivery_address_code, material_sub_type_id, ",
        "material_workshop_id, created_time, ",
        "created_person_id, created_person, ",
        "completion_time, material_status, ",
        "sys_flag)",
        "values (#{applicationFormId,jdbcType=BIGINT}, #{stockOutRecordHeadCode,jdbcType=BIGINT}, ",
        "#{deptCode,jdbcType=INTEGER}, #{hfLineCode,jdbcType=INTEGER}, ",
        "#{sfLineCode,jdbcType=INTEGER}, #{deliveryTypeCode,jdbcType=INTEGER}, ",
        "#{deliveryAddressCode,jdbcType=INTEGER}, #{materialSubTypeId,jdbcType=INTEGER}, ",
        "#{materialWorkshopId,jdbcType=INTEGER}, #{createdTime,jdbcType=TIMESTAMP}, ",
        "#{createdPersonId,jdbcType=INTEGER}, #{createdPerson,jdbcType=VARCHAR}, ",
        "#{completionTime,jdbcType=TIMESTAMP}, #{materialStatus,jdbcType=TINYINT}, ",
        "#{sysFlag,jdbcType=BIT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SwmsStockOutRecordHead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_stock_out_record_head
     *
     * @mbggenerated
     */
    int insertSelective(SwmsStockOutRecordHead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_stock_out_record_head
     *
     * @mbggenerated
     */
    List<SwmsStockOutRecordHead> selectByExample(SwmsStockOutRecordHeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_stock_out_record_head
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "id, application_form_id, stock_out_record_head_code, dept_code, hf_line_code, ",
        "sf_line_code, delivery_type_code, delivery_address_code, material_sub_type_id, ",
        "material_workshop_id, created_time, created_person_id, created_person, completion_time, ",
        "material_status, sys_flag",
        "from SWMS_stock_out_record_head",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    SwmsStockOutRecordHead selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_stock_out_record_head
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SwmsStockOutRecordHead record, @Param("example") SwmsStockOutRecordHeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_stock_out_record_head
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SwmsStockOutRecordHead record, @Param("example") SwmsStockOutRecordHeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_stock_out_record_head
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SwmsStockOutRecordHead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_stock_out_record_head
     *
     * @mbggenerated
     */
    @Update({
        "update SWMS_stock_out_record_head",
        "set application_form_id = #{applicationFormId,jdbcType=BIGINT},",
          "stock_out_record_head_code = #{stockOutRecordHeadCode,jdbcType=BIGINT},",
          "dept_code = #{deptCode,jdbcType=INTEGER},",
          "hf_line_code = #{hfLineCode,jdbcType=INTEGER},",
          "sf_line_code = #{sfLineCode,jdbcType=INTEGER},",
          "delivery_type_code = #{deliveryTypeCode,jdbcType=INTEGER},",
          "delivery_address_code = #{deliveryAddressCode,jdbcType=INTEGER},",
          "material_sub_type_id = #{materialSubTypeId,jdbcType=INTEGER},",
          "material_workshop_id = #{materialWorkshopId,jdbcType=INTEGER},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "created_person_id = #{createdPersonId,jdbcType=INTEGER},",
          "created_person = #{createdPerson,jdbcType=VARCHAR},",
          "completion_time = #{completionTime,jdbcType=TIMESTAMP},",
          "material_status = #{materialStatus,jdbcType=TINYINT},",
          "sys_flag = #{sysFlag,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SwmsStockOutRecordHead record);
}