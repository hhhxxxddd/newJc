package com.jinchi.common.domain;

import java.util.Date;

public class SwmsStockOutRecordHead {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.application_form_id
     *
     * @mbggenerated
     */
    private Long applicationFormId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.stock_out_record_head_code
     *
     * @mbggenerated
     */
    private Long stockOutRecordHeadCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.dept_code
     *
     * @mbggenerated
     */
    private Integer deptCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.hf_line_code
     *
     * @mbggenerated
     */
    private Integer hfLineCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.sf_line_code
     *
     * @mbggenerated
     */
    private Integer sfLineCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.delivery_type_code
     *
     * @mbggenerated
     */
    private Integer deliveryTypeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.delivery_address_code
     *
     * @mbggenerated
     */
    private Integer deliveryAddressCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.material_sub_type_id
     *
     * @mbggenerated
     */
    private Integer materialSubTypeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.material_workshop_id
     *
     * @mbggenerated
     */
    private Integer materialWorkshopId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.created_time
     *
     * @mbggenerated
     */
    private Date createdTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.created_person_id
     *
     * @mbggenerated
     */
    private Integer createdPersonId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.created_person
     *
     * @mbggenerated
     */
    private String createdPerson;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.completion_time
     *
     * @mbggenerated
     */
    private Date completionTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.material_status
     *
     * @mbggenerated
     */
    private Byte materialStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_out_record_head.sys_flag
     *
     * @mbggenerated
     */
    private Boolean sysFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_stock_out_record_head
     *
     * @mbggenerated
     */
    public SwmsStockOutRecordHead(Long id, Long applicationFormId, Long stockOutRecordHeadCode, Integer deptCode, Integer hfLineCode, Integer sfLineCode, Integer deliveryTypeCode, Integer deliveryAddressCode, Integer materialSubTypeId, Integer materialWorkshopId, Date createdTime, Integer createdPersonId, String createdPerson, Date completionTime, Byte materialStatus, Boolean sysFlag) {
        this.id = id;
        this.applicationFormId = applicationFormId;
        this.stockOutRecordHeadCode = stockOutRecordHeadCode;
        this.deptCode = deptCode;
        this.hfLineCode = hfLineCode;
        this.sfLineCode = sfLineCode;
        this.deliveryTypeCode = deliveryTypeCode;
        this.deliveryAddressCode = deliveryAddressCode;
        this.materialSubTypeId = materialSubTypeId;
        this.materialWorkshopId = materialWorkshopId;
        this.createdTime = createdTime;
        this.createdPersonId = createdPersonId;
        this.createdPerson = createdPerson;
        this.completionTime = completionTime;
        this.materialStatus = materialStatus;
        this.sysFlag = sysFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_stock_out_record_head
     *
     * @mbggenerated
     */
    public SwmsStockOutRecordHead() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.id
     *
     * @return the value of SWMS_stock_out_record_head.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.id
     *
     * @param id the value for SWMS_stock_out_record_head.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.application_form_id
     *
     * @return the value of SWMS_stock_out_record_head.application_form_id
     *
     * @mbggenerated
     */
    public Long getApplicationFormId() {
        return applicationFormId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.application_form_id
     *
     * @param applicationFormId the value for SWMS_stock_out_record_head.application_form_id
     *
     * @mbggenerated
     */
    public void setApplicationFormId(Long applicationFormId) {
        this.applicationFormId = applicationFormId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.stock_out_record_head_code
     *
     * @return the value of SWMS_stock_out_record_head.stock_out_record_head_code
     *
     * @mbggenerated
     */
    public Long getStockOutRecordHeadCode() {
        return stockOutRecordHeadCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.stock_out_record_head_code
     *
     * @param stockOutRecordHeadCode the value for SWMS_stock_out_record_head.stock_out_record_head_code
     *
     * @mbggenerated
     */
    public void setStockOutRecordHeadCode(Long stockOutRecordHeadCode) {
        this.stockOutRecordHeadCode = stockOutRecordHeadCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.dept_code
     *
     * @return the value of SWMS_stock_out_record_head.dept_code
     *
     * @mbggenerated
     */
    public Integer getDeptCode() {
        return deptCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.dept_code
     *
     * @param deptCode the value for SWMS_stock_out_record_head.dept_code
     *
     * @mbggenerated
     */
    public void setDeptCode(Integer deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.hf_line_code
     *
     * @return the value of SWMS_stock_out_record_head.hf_line_code
     *
     * @mbggenerated
     */
    public Integer getHfLineCode() {
        return hfLineCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.hf_line_code
     *
     * @param hfLineCode the value for SWMS_stock_out_record_head.hf_line_code
     *
     * @mbggenerated
     */
    public void setHfLineCode(Integer hfLineCode) {
        this.hfLineCode = hfLineCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.sf_line_code
     *
     * @return the value of SWMS_stock_out_record_head.sf_line_code
     *
     * @mbggenerated
     */
    public Integer getSfLineCode() {
        return sfLineCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.sf_line_code
     *
     * @param sfLineCode the value for SWMS_stock_out_record_head.sf_line_code
     *
     * @mbggenerated
     */
    public void setSfLineCode(Integer sfLineCode) {
        this.sfLineCode = sfLineCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.delivery_type_code
     *
     * @return the value of SWMS_stock_out_record_head.delivery_type_code
     *
     * @mbggenerated
     */
    public Integer getDeliveryTypeCode() {
        return deliveryTypeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.delivery_type_code
     *
     * @param deliveryTypeCode the value for SWMS_stock_out_record_head.delivery_type_code
     *
     * @mbggenerated
     */
    public void setDeliveryTypeCode(Integer deliveryTypeCode) {
        this.deliveryTypeCode = deliveryTypeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.delivery_address_code
     *
     * @return the value of SWMS_stock_out_record_head.delivery_address_code
     *
     * @mbggenerated
     */
    public Integer getDeliveryAddressCode() {
        return deliveryAddressCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.delivery_address_code
     *
     * @param deliveryAddressCode the value for SWMS_stock_out_record_head.delivery_address_code
     *
     * @mbggenerated
     */
    public void setDeliveryAddressCode(Integer deliveryAddressCode) {
        this.deliveryAddressCode = deliveryAddressCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.material_sub_type_id
     *
     * @return the value of SWMS_stock_out_record_head.material_sub_type_id
     *
     * @mbggenerated
     */
    public Integer getMaterialSubTypeId() {
        return materialSubTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.material_sub_type_id
     *
     * @param materialSubTypeId the value for SWMS_stock_out_record_head.material_sub_type_id
     *
     * @mbggenerated
     */
    public void setMaterialSubTypeId(Integer materialSubTypeId) {
        this.materialSubTypeId = materialSubTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.material_workshop_id
     *
     * @return the value of SWMS_stock_out_record_head.material_workshop_id
     *
     * @mbggenerated
     */
    public Integer getMaterialWorkshopId() {
        return materialWorkshopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.material_workshop_id
     *
     * @param materialWorkshopId the value for SWMS_stock_out_record_head.material_workshop_id
     *
     * @mbggenerated
     */
    public void setMaterialWorkshopId(Integer materialWorkshopId) {
        this.materialWorkshopId = materialWorkshopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.created_time
     *
     * @return the value of SWMS_stock_out_record_head.created_time
     *
     * @mbggenerated
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.created_time
     *
     * @param createdTime the value for SWMS_stock_out_record_head.created_time
     *
     * @mbggenerated
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.created_person_id
     *
     * @return the value of SWMS_stock_out_record_head.created_person_id
     *
     * @mbggenerated
     */
    public Integer getCreatedPersonId() {
        return createdPersonId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.created_person_id
     *
     * @param createdPersonId the value for SWMS_stock_out_record_head.created_person_id
     *
     * @mbggenerated
     */
    public void setCreatedPersonId(Integer createdPersonId) {
        this.createdPersonId = createdPersonId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.created_person
     *
     * @return the value of SWMS_stock_out_record_head.created_person
     *
     * @mbggenerated
     */
    public String getCreatedPerson() {
        return createdPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.created_person
     *
     * @param createdPerson the value for SWMS_stock_out_record_head.created_person
     *
     * @mbggenerated
     */
    public void setCreatedPerson(String createdPerson) {
        this.createdPerson = createdPerson == null ? null : createdPerson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.completion_time
     *
     * @return the value of SWMS_stock_out_record_head.completion_time
     *
     * @mbggenerated
     */
    public Date getCompletionTime() {
        return completionTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.completion_time
     *
     * @param completionTime the value for SWMS_stock_out_record_head.completion_time
     *
     * @mbggenerated
     */
    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.material_status
     *
     * @return the value of SWMS_stock_out_record_head.material_status
     *
     * @mbggenerated
     */
    public Byte getMaterialStatus() {
        return materialStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.material_status
     *
     * @param materialStatus the value for SWMS_stock_out_record_head.material_status
     *
     * @mbggenerated
     */
    public void setMaterialStatus(Byte materialStatus) {
        this.materialStatus = materialStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_out_record_head.sys_flag
     *
     * @return the value of SWMS_stock_out_record_head.sys_flag
     *
     * @mbggenerated
     */
    public Boolean getSysFlag() {
        return sysFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_out_record_head.sys_flag
     *
     * @param sysFlag the value for SWMS_stock_out_record_head.sys_flag
     *
     * @mbggenerated
     */
    public void setSysFlag(Boolean sysFlag) {
        this.sysFlag = sysFlag;
    }
}