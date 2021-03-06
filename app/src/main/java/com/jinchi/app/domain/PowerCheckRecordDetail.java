package com.jinchi.app.domain;

public class PowerCheckRecordDetail {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power_check_record_detail.code
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    private Long code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power_check_record_detail.record_code
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    private Long recordCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power_check_record_detail.place
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    private String place;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power_check_record_detail.check_item
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    private String checkItem;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power_check_record_detail.check_content
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    private String checkContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power_check_record_detail.check_value
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    private Byte checkValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power_check_record_detail.check_result
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    private String checkResult;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power_check_record_detail.data_type
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    private Byte dataType;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power_check_record_detail
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public PowerCheckRecordDetail(Long code, Long recordCode, String place, String checkItem, String checkContent, Byte checkValue, String checkResult, Byte dataType) {
        this.code = code;
        this.recordCode = recordCode;
        this.place = place;
        this.checkItem = checkItem;
        this.checkContent = checkContent;
        this.checkValue = checkValue;
        this.checkResult = checkResult;
        this.dataType = dataType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power_check_record_detail
     *
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public PowerCheckRecordDetail() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power_check_record_detail.code
     *
     * @return the value of power_check_record_detail.code
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public Long getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power_check_record_detail.code
     *
     * @param code the value for power_check_record_detail.code
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power_check_record_detail.record_code
     *
     * @return the value of power_check_record_detail.record_code
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public Long getRecordCode() {
        return recordCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power_check_record_detail.record_code
     *
     * @param recordCode the value for power_check_record_detail.record_code
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public void setRecordCode(Long recordCode) {
        this.recordCode = recordCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power_check_record_detail.place
     *
     * @return the value of power_check_record_detail.place
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public String getPlace() {
        return place;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power_check_record_detail.place
     *
     * @param place the value for power_check_record_detail.place
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power_check_record_detail.check_item
     *
     * @return the value of power_check_record_detail.check_item
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public String getCheckItem() {
        return checkItem;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power_check_record_detail.check_item
     *
     * @param checkItem the value for power_check_record_detail.check_item
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public void setCheckItem(String checkItem) {
        this.checkItem = checkItem == null ? null : checkItem.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power_check_record_detail.check_content
     *
     * @return the value of power_check_record_detail.check_content
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public String getCheckContent() {
        return checkContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power_check_record_detail.check_content
     *
     * @param checkContent the value for power_check_record_detail.check_content
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent == null ? null : checkContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power_check_record_detail.check_value
     *
     * @return the value of power_check_record_detail.check_value
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public Byte getCheckValue() {
        return checkValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power_check_record_detail.check_value
     *
     * @param checkValue the value for power_check_record_detail.check_value
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public void setCheckValue(Byte checkValue) {
        this.checkValue = checkValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power_check_record_detail.check_result
     *
     * @return the value of power_check_record_detail.check_result
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public String getCheckResult() {
        return checkResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power_check_record_detail.check_result
     *
     * @param checkResult the value for power_check_record_detail.check_result
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult == null ? null : checkResult.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power_check_record_detail.data_type
     *
     * @return the value of power_check_record_detail.data_type
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public Byte getDataType() {
        return dataType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power_check_record_detail.data_type
     *
     * @param dataType the value for power_check_record_detail.data_type
     * @mbggenerated Wed Mar 25 13:25:42 CST 2020
     */
    public void setDataType(Byte dataType) {
        this.dataType = dataType;
    }
}