package com.jinchi.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProductionInstrumentData {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.code
     *
     * @mbggenerated
     */
    private Long code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.cell_num
     *
     * @mbggenerated
     */
    private String cellNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.sample_time
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sampleTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.ph_value
     *
     * @mbggenerated
     */
    private Float phValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.temperature
     *
     * @mbggenerated
     */
    private Float temperature;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.salt_flow1
     *
     * @mbggenerated
     */
    private Float saltFlow1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.salt_flow2
     *
     * @mbggenerated
     */
    private Float saltFlow2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.salt_flow3
     *
     * @mbggenerated
     */
    private Float saltFlow3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.salt_flow4
     *
     * @mbggenerated
     */
    private Float saltFlow4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.ammonia_bases1
     *
     * @mbggenerated
     */
    private Float ammoniaBases1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.ammonia_bases2
     *
     * @mbggenerated
     */
    private Float ammoniaBases2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.ammonia_water
     *
     * @mbggenerated
     */
    private Float ammoniaWater;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.ammonia_gas
     *
     * @mbggenerated
     */
    private Float ammoniaGas;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.solid_containing_content
     *
     * @mbggenerated
     */
    private Float solidContainingContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.transducer_show
     *
     * @mbggenerated
     */
    private Float transducerShow;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column production_instrument_data.measured_3c
     *
     * @mbggenerated
     */
    private Float measured3c;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_instrument_data
     *
     * @mbggenerated
     */
    public ProductionInstrumentData(Long code, String cellNum, Date sampleTime, Float phValue, Float temperature, Float saltFlow1, Float saltFlow2, Float saltFlow3, Float saltFlow4, Float ammoniaBases1, Float ammoniaBases2, Float ammoniaWater, Float ammoniaGas, Float solidContainingContent, Float transducerShow, Float measured3c) {
        this.code = code;
        this.cellNum = cellNum;
        this.sampleTime = sampleTime;
        this.phValue = phValue;
        this.temperature = temperature;
        this.saltFlow1 = saltFlow1;
        this.saltFlow2 = saltFlow2;
        this.saltFlow3 = saltFlow3;
        this.saltFlow4 = saltFlow4;
        this.ammoniaBases1 = ammoniaBases1;
        this.ammoniaBases2 = ammoniaBases2;
        this.ammoniaWater = ammoniaWater;
        this.ammoniaGas = ammoniaGas;
        this.solidContainingContent = solidContainingContent;
        this.transducerShow = transducerShow;
        this.measured3c = measured3c;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_instrument_data
     *
     * @mbggenerated
     */
    public ProductionInstrumentData() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.code
     *
     * @return the value of production_instrument_data.code
     *
     * @mbggenerated
     */
    public Long getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.code
     *
     * @param code the value for production_instrument_data.code
     *
     * @mbggenerated
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.cell_num
     *
     * @return the value of production_instrument_data.cell_num
     *
     * @mbggenerated
     */
    public String getCellNum() {
        return cellNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.cell_num
     *
     * @param cellNum the value for production_instrument_data.cell_num
     *
     * @mbggenerated
     */
    public void setCellNum(String cellNum) {
        this.cellNum = cellNum == null ? null : cellNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.sample_time
     *
     * @return the value of production_instrument_data.sample_time
     *
     * @mbggenerated
     */
    public Date getSampleTime() {
        return sampleTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.sample_time
     *
     * @param sampleTime the value for production_instrument_data.sample_time
     *
     * @mbggenerated
     */
    public void setSampleTime(Date sampleTime) {
        this.sampleTime = sampleTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.ph_value
     *
     * @return the value of production_instrument_data.ph_value
     *
     * @mbggenerated
     */
    public Float getPhValue() {
        return phValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.ph_value
     *
     * @param phValue the value for production_instrument_data.ph_value
     *
     * @mbggenerated
     */
    public void setPhValue(Float phValue) {
        this.phValue = phValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.temperature
     *
     * @return the value of production_instrument_data.temperature
     *
     * @mbggenerated
     */
    public Float getTemperature() {
        return temperature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.temperature
     *
     * @param temperature the value for production_instrument_data.temperature
     *
     * @mbggenerated
     */
    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.salt_flow1
     *
     * @return the value of production_instrument_data.salt_flow1
     *
     * @mbggenerated
     */
    public Float getSaltFlow1() {
        return saltFlow1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.salt_flow1
     *
     * @param saltFlow1 the value for production_instrument_data.salt_flow1
     *
     * @mbggenerated
     */
    public void setSaltFlow1(Float saltFlow1) {
        this.saltFlow1 = saltFlow1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.salt_flow2
     *
     * @return the value of production_instrument_data.salt_flow2
     *
     * @mbggenerated
     */
    public Float getSaltFlow2() {
        return saltFlow2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.salt_flow2
     *
     * @param saltFlow2 the value for production_instrument_data.salt_flow2
     *
     * @mbggenerated
     */
    public void setSaltFlow2(Float saltFlow2) {
        this.saltFlow2 = saltFlow2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.salt_flow3
     *
     * @return the value of production_instrument_data.salt_flow3
     *
     * @mbggenerated
     */
    public Float getSaltFlow3() {
        return saltFlow3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.salt_flow3
     *
     * @param saltFlow3 the value for production_instrument_data.salt_flow3
     *
     * @mbggenerated
     */
    public void setSaltFlow3(Float saltFlow3) {
        this.saltFlow3 = saltFlow3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.salt_flow4
     *
     * @return the value of production_instrument_data.salt_flow4
     *
     * @mbggenerated
     */
    public Float getSaltFlow4() {
        return saltFlow4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.salt_flow4
     *
     * @param saltFlow4 the value for production_instrument_data.salt_flow4
     *
     * @mbggenerated
     */
    public void setSaltFlow4(Float saltFlow4) {
        this.saltFlow4 = saltFlow4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.ammonia_bases1
     *
     * @return the value of production_instrument_data.ammonia_bases1
     *
     * @mbggenerated
     */
    public Float getAmmoniaBases1() {
        return ammoniaBases1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.ammonia_bases1
     *
     * @param ammoniaBases1 the value for production_instrument_data.ammonia_bases1
     *
     * @mbggenerated
     */
    public void setAmmoniaBases1(Float ammoniaBases1) {
        this.ammoniaBases1 = ammoniaBases1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.ammonia_bases2
     *
     * @return the value of production_instrument_data.ammonia_bases2
     *
     * @mbggenerated
     */
    public Float getAmmoniaBases2() {
        return ammoniaBases2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.ammonia_bases2
     *
     * @param ammoniaBases2 the value for production_instrument_data.ammonia_bases2
     *
     * @mbggenerated
     */
    public void setAmmoniaBases2(Float ammoniaBases2) {
        this.ammoniaBases2 = ammoniaBases2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.ammonia_water
     *
     * @return the value of production_instrument_data.ammonia_water
     *
     * @mbggenerated
     */
    public Float getAmmoniaWater() {
        return ammoniaWater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.ammonia_water
     *
     * @param ammoniaWater the value for production_instrument_data.ammonia_water
     *
     * @mbggenerated
     */
    public void setAmmoniaWater(Float ammoniaWater) {
        this.ammoniaWater = ammoniaWater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.ammonia_gas
     *
     * @return the value of production_instrument_data.ammonia_gas
     *
     * @mbggenerated
     */
    public Float getAmmoniaGas() {
        return ammoniaGas;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.ammonia_gas
     *
     * @param ammoniaGas the value for production_instrument_data.ammonia_gas
     *
     * @mbggenerated
     */
    public void setAmmoniaGas(Float ammoniaGas) {
        this.ammoniaGas = ammoniaGas;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.solid_containing_content
     *
     * @return the value of production_instrument_data.solid_containing_content
     *
     * @mbggenerated
     */
    public Float getSolidContainingContent() {
        return solidContainingContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.solid_containing_content
     *
     * @param solidContainingContent the value for production_instrument_data.solid_containing_content
     *
     * @mbggenerated
     */
    public void setSolidContainingContent(Float solidContainingContent) {
        this.solidContainingContent = solidContainingContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.transducer_show
     *
     * @return the value of production_instrument_data.transducer_show
     *
     * @mbggenerated
     */
    public Float getTransducerShow() {
        return transducerShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.transducer_show
     *
     * @param transducerShow the value for production_instrument_data.transducer_show
     *
     * @mbggenerated
     */
    public void setTransducerShow(Float transducerShow) {
        this.transducerShow = transducerShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column production_instrument_data.measured_3c
     *
     * @return the value of production_instrument_data.measured_3c
     *
     * @mbggenerated
     */
    public Float getMeasured3c() {
        return measured3c;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column production_instrument_data.measured_3c
     *
     * @param measured3c the value for production_instrument_data.measured_3c
     *
     * @mbggenerated
     */
    public void setMeasured3c(Float measured3c) {
        this.measured3c = measured3c;
    }
}