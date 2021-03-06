package com.jinchi.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProductionBatchAssayMap {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_batch_assay_map.code
     *
     * @mbggenerated
     */
    private Long code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_batch_assay_map.batch_code
     *
     * @mbggenerated
     */
    private Long batchCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_batch_assay_map.batch
     *
     * @mbggenerated
     */
    private String batch;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_batch_assay_map.sample_code
     *
     * @mbggenerated
     */
    private Long sampleCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_batch_assay_map.type
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_batch_assay_map.deliverer_id
     *
     * @mbggenerated
     */
    private Integer delivererId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_batch_assay_map.sample_delivering_date
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sampleDeliveringDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_batch_assay_map.delivery_factory_id
     *
     * @mbggenerated
     */
    private Integer deliveryFactoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_batch_assay_map.test_items
     *
     * @mbggenerated
     */
    private String testItems;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_batch_assay_map.rule_detail_code
     *
     * @mbggenerated
     */
    private Short ruleDetailCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_batch_assay_map.rule_value
     *
     * @mbggenerated
     */
    private String ruleValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_batch_assay_map.start_time
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_batch_assay_map.end_time
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_batch_assay_map
     *
     * @mbggenerated
     */
    public ProductionBatchAssayMap(Long code, Long batchCode, String batch, Long sampleCode, Integer type, Integer delivererId, Date sampleDeliveringDate, Integer deliveryFactoryId, String testItems, Short ruleDetailCode, String ruleValue, Date startTime, Date endTime) {
        this.code = code;
        this.batchCode = batchCode;
        this.batch = batch;
        this.sampleCode = sampleCode;
        this.type = type;
        this.delivererId = delivererId;
        this.sampleDeliveringDate = sampleDeliveringDate;
        this.deliveryFactoryId = deliveryFactoryId;
        this.testItems = testItems;
        this.ruleDetailCode = ruleDetailCode;
        this.ruleValue = ruleValue;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_batch_assay_map
     *
     * @mbggenerated
     */
    public ProductionBatchAssayMap() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_batch_assay_map.code
     *
     * @return the value of production_batch_assay_map.code
     *
     * @mbggenerated
     */
    public Long getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_batch_assay_map.code
     *
     * @param code the value for production_batch_assay_map.code
     *
     * @mbggenerated
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_batch_assay_map.batch_code
     *
     * @return the value of production_batch_assay_map.batch_code
     *
     * @mbggenerated
     */
    public Long getBatchCode() {
        return batchCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_batch_assay_map.batch_code
     *
     * @param batchCode the value for production_batch_assay_map.batch_code
     *
     * @mbggenerated
     */
    public void setBatchCode(Long batchCode) {
        this.batchCode = batchCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_batch_assay_map.batch
     *
     * @return the value of production_batch_assay_map.batch
     *
     * @mbggenerated
     */
    public String getBatch() {
        return batch;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_batch_assay_map.batch
     *
     * @param batch the value for production_batch_assay_map.batch
     *
     * @mbggenerated
     */
    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_batch_assay_map.sample_code
     *
     * @return the value of production_batch_assay_map.sample_code
     *
     * @mbggenerated
     */
    public Long getSampleCode() {
        return sampleCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_batch_assay_map.sample_code
     *
     * @param sampleCode the value for production_batch_assay_map.sample_code
     *
     * @mbggenerated
     */
    public void setSampleCode(Long sampleCode) {
        this.sampleCode = sampleCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_batch_assay_map.type
     *
     * @return the value of production_batch_assay_map.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_batch_assay_map.type
     *
     * @param type the value for production_batch_assay_map.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_batch_assay_map.deliverer_id
     *
     * @return the value of production_batch_assay_map.deliverer_id
     *
     * @mbggenerated
     */
    public Integer getDelivererId() {
        return delivererId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_batch_assay_map.deliverer_id
     *
     * @param delivererId the value for production_batch_assay_map.deliverer_id
     *
     * @mbggenerated
     */
    public void setDelivererId(Integer delivererId) {
        this.delivererId = delivererId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_batch_assay_map.sample_delivering_date
     *
     * @return the value of production_batch_assay_map.sample_delivering_date
     *
     * @mbggenerated
     */
    public Date getSampleDeliveringDate() {
        return sampleDeliveringDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_batch_assay_map.sample_delivering_date
     *
     * @param sampleDeliveringDate the value for production_batch_assay_map.sample_delivering_date
     *
     * @mbggenerated
     */
    public void setSampleDeliveringDate(Date sampleDeliveringDate) {
        this.sampleDeliveringDate = sampleDeliveringDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_batch_assay_map.delivery_factory_id
     *
     * @return the value of production_batch_assay_map.delivery_factory_id
     *
     * @mbggenerated
     */
    public Integer getDeliveryFactoryId() {
        return deliveryFactoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_batch_assay_map.delivery_factory_id
     *
     * @param deliveryFactoryId the value for production_batch_assay_map.delivery_factory_id
     *
     * @mbggenerated
     */
    public void setDeliveryFactoryId(Integer deliveryFactoryId) {
        this.deliveryFactoryId = deliveryFactoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_batch_assay_map.test_items
     *
     * @return the value of production_batch_assay_map.test_items
     *
     * @mbggenerated
     */
    public String getTestItems() {
        return testItems;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_batch_assay_map.test_items
     *
     * @param testItems the value for production_batch_assay_map.test_items
     *
     * @mbggenerated
     */
    public void setTestItems(String testItems) {
        this.testItems = testItems == null ? null : testItems.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_batch_assay_map.rule_detail_code
     *
     * @return the value of production_batch_assay_map.rule_detail_code
     *
     * @mbggenerated
     */
    public Short getRuleDetailCode() {
        return ruleDetailCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_batch_assay_map.rule_detail_code
     *
     * @param ruleDetailCode the value for production_batch_assay_map.rule_detail_code
     *
     * @mbggenerated
     */
    public void setRuleDetailCode(Short ruleDetailCode) {
        this.ruleDetailCode = ruleDetailCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_batch_assay_map.rule_value
     *
     * @return the value of production_batch_assay_map.rule_value
     *
     * @mbggenerated
     */
    public String getRuleValue() {
        return ruleValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_batch_assay_map.rule_value
     *
     * @param ruleValue the value for production_batch_assay_map.rule_value
     *
     * @mbggenerated
     */
    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue == null ? null : ruleValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_batch_assay_map.start_time
     *
     * @return the value of production_batch_assay_map.start_time
     *
     * @mbggenerated
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_batch_assay_map.start_time
     *
     * @param startTime the value for production_batch_assay_map.start_time
     *
     * @mbggenerated
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_batch_assay_map.end_time
     *
     * @return the value of production_batch_assay_map.end_time
     *
     * @mbggenerated
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_batch_assay_map.end_time
     *
     * @param endTime the value for production_batch_assay_map.end_time
     *
     * @mbggenerated
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}