package com.jinchi.app.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PowerCheckModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power_check_model.code
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    private Long code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power_check_model.model_name
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    private String modelName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power_check_model.site_code
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    private Long siteCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power_check_model.batch_number
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    private String batchNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power_check_model.effective_date
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date effectiveDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power_check_model.frequency
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    private String frequency;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power_check_model
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public PowerCheckModel(Long code, String modelName, Long siteCode, String batchNumber, Date effectiveDate, String frequency) {
        this.code = code;
        this.modelName = modelName;
        this.siteCode = siteCode;
        this.batchNumber = batchNumber;
        this.effectiveDate = effectiveDate;
        this.frequency = frequency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power_check_model
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public PowerCheckModel() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power_check_model.code
     *
     * @return the value of power_check_model.code
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public Long getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power_check_model.code
     *
     * @param code the value for power_check_model.code
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power_check_model.model_name
     *
     * @return the value of power_check_model.model_name
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power_check_model.model_name
     *
     * @param modelName the value for power_check_model.model_name
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power_check_model.site_code
     *
     * @return the value of power_check_model.site_code
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public Long getSiteCode() {
        return siteCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power_check_model.site_code
     *
     * @param siteCode the value for power_check_model.site_code
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public void setSiteCode(Long siteCode) {
        this.siteCode = siteCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power_check_model.batch_number
     *
     * @return the value of power_check_model.batch_number
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power_check_model.batch_number
     *
     * @param batchNumber the value for power_check_model.batch_number
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber == null ? null : batchNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power_check_model.effective_date
     *
     * @return the value of power_check_model.effective_date
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power_check_model.effective_date
     *
     * @param effectiveDate the value for power_check_model.effective_date
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power_check_model.frequency
     *
     * @return the value of power_check_model.frequency
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power_check_model.frequency
     *
     * @param frequency the value for power_check_model.frequency
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency == null ? null : frequency.trim();
    }
}