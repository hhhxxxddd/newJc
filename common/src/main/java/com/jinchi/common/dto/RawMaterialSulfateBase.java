package com.jinchi.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RawMaterialSulfateBase {

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material_delivery_statistic_nickel_sulfate.code
     *
     * @mbggenerated
     */
    public Long code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material_delivery_statistic_nickel_sulfate.statistic_code
     *
     * @mbggenerated
     */
    public Long statisticCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material_delivery_statistic_nickel_sulfate.material_type_code
     *
     * @mbggenerated
     */
    public Integer materialTypeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material_delivery_statistic_nickel_sulfate.material_code
     *
     * @mbggenerated
     */
    public Integer materialCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material_delivery_statistic_nickel_sulfate.material_name
     *
     * @mbggenerated
     */
    public String materialName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material_delivery_statistic_nickel_sulfate.encoder
     *
     * @mbggenerated
     */
    public String encoder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material_delivery_statistic_nickel_sulfate.delivery_time
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date deliveryTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material_delivery_statistic_nickel_sulfate.weights
     *
     * @mbggenerated
     */
    public Float weights;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material_delivery_statistic_nickel_sulfate.ni_concentration
     *
     * @mbggenerated
     */
    public Float niConcentration;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material_delivery_statistic_nickel_sulfate.co_concentration
     *
     * @mbggenerated
     */
    public Float coConcentration;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material_delivery_statistic_nickel_sulfate.mn_concentration
     *
     * @mbggenerated
     */
    public Float mnConcentration;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material_delivery_statistic_nickel_sulfate.ni_metallicity
     *
     * @mbggenerated
     */
    public Float niMetallicity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material_delivery_statistic_nickel_sulfate.co_metallicity
     *
     * @mbggenerated
     */
    public Float coMetallicity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material_delivery_statistic_nickel_sulfate.mn_metallicity
     *
     * @mbggenerated
     */
    public Float mnMetallicity;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_delivery_statistic_nickel_sulfate
     *
     * @mbggenerated
     */
    public RawMaterialSulfateBase(Long code, Long statisticCode, Integer materialTypeCode, Integer materialCode, String materialName, String encoder, Date deliveryTime, Float weights, Float niConcentration, Float coConcentration, Float mnConcentration, Float niMetallicity, Float coMetallicity, Float mnMetallicity) {
        this.code = code;
        this.statisticCode = statisticCode;
        this.materialTypeCode = materialTypeCode;
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.encoder = encoder;
        this.deliveryTime = deliveryTime;
        this.weights = weights;
        this.niConcentration = niConcentration;
        this.coConcentration = coConcentration;
        this.mnConcentration = mnConcentration;
        this.niMetallicity = niMetallicity;
        this.coMetallicity = coMetallicity;
        this.mnMetallicity = mnMetallicity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_delivery_statistic_nickel_sulfate
     *
     * @mbggenerated
     */
    public RawMaterialSulfateBase() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material_delivery_statistic_nickel_sulfate.code
     *
     * @return the value of material_delivery_statistic_nickel_sulfate.code
     *
     * @mbggenerated
     */
    public Long getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material_delivery_statistic_nickel_sulfate.code
     *
     * @param code the value for material_delivery_statistic_nickel_sulfate.code
     *
     * @mbggenerated
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material_delivery_statistic_nickel_sulfate.statistic_code
     *
     * @return the value of material_delivery_statistic_nickel_sulfate.statistic_code
     *
     * @mbggenerated
     */
    public Long getStatisticCode() {
        return statisticCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material_delivery_statistic_nickel_sulfate.statistic_code
     *
     * @param statisticCode the value for material_delivery_statistic_nickel_sulfate.statistic_code
     *
     * @mbggenerated
     */
    public void setStatisticCode(Long statisticCode) {
        this.statisticCode = statisticCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material_delivery_statistic_nickel_sulfate.material_type_code
     *
     * @return the value of material_delivery_statistic_nickel_sulfate.material_type_code
     *
     * @mbggenerated
     */
    public Integer getMaterialTypeCode() {
        return materialTypeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material_delivery_statistic_nickel_sulfate.material_type_code
     *
     * @param materialTypeCode the value for material_delivery_statistic_nickel_sulfate.material_type_code
     *
     * @mbggenerated
     */
    public void setMaterialTypeCode(Integer materialTypeCode) {
        this.materialTypeCode = materialTypeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material_delivery_statistic_nickel_sulfate.material_code
     *
     * @return the value of material_delivery_statistic_nickel_sulfate.material_code
     *
     * @mbggenerated
     */
    public Integer getMaterialCode() {
        return materialCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material_delivery_statistic_nickel_sulfate.material_code
     *
     * @param materialCode the value for material_delivery_statistic_nickel_sulfate.material_code
     *
     * @mbggenerated
     */
    public void setMaterialCode(Integer materialCode) {
        this.materialCode = materialCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material_delivery_statistic_nickel_sulfate.material_name
     *
     * @return the value of material_delivery_statistic_nickel_sulfate.material_name
     *
     * @mbggenerated
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material_delivery_statistic_nickel_sulfate.material_name
     *
     * @param materialName the value for material_delivery_statistic_nickel_sulfate.material_name
     *
     * @mbggenerated
     */
    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material_delivery_statistic_nickel_sulfate.encoder
     *
     * @return the value of material_delivery_statistic_nickel_sulfate.encoder
     *
     * @mbggenerated
     */
    public String getEncoder() {
        return encoder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material_delivery_statistic_nickel_sulfate.encoder
     *
     * @param encoder the value for material_delivery_statistic_nickel_sulfate.encoder
     *
     * @mbggenerated
     */
    public void setEncoder(String encoder) {
        this.encoder = encoder == null ? null : encoder.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material_delivery_statistic_nickel_sulfate.delivery_time
     *
     * @return the value of material_delivery_statistic_nickel_sulfate.delivery_time
     *
     * @mbggenerated
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material_delivery_statistic_nickel_sulfate.delivery_time
     *
     * @param deliveryTime the value for material_delivery_statistic_nickel_sulfate.delivery_time
     *
     * @mbggenerated
     */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material_delivery_statistic_nickel_sulfate.weights
     *
     * @return the value of material_delivery_statistic_nickel_sulfate.weights
     *
     * @mbggenerated
     */
    public Float getWeights() {
        return weights;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material_delivery_statistic_nickel_sulfate.weights
     *
     * @param weights the value for material_delivery_statistic_nickel_sulfate.weights
     *
     * @mbggenerated
     */
    public void setWeights(Float weights) {
        this.weights = weights;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material_delivery_statistic_nickel_sulfate.ni_concentration
     *
     * @return the value of material_delivery_statistic_nickel_sulfate.ni_concentration
     *
     * @mbggenerated
     */
    public Float getNiConcentration() {
        return niConcentration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material_delivery_statistic_nickel_sulfate.ni_concentration
     *
     * @param niConcentration the value for material_delivery_statistic_nickel_sulfate.ni_concentration
     *
     * @mbggenerated
     */
    public void setNiConcentration(Float niConcentration) {
        this.niConcentration = niConcentration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material_delivery_statistic_nickel_sulfate.co_concentration
     *
     * @return the value of material_delivery_statistic_nickel_sulfate.co_concentration
     *
     * @mbggenerated
     */
    public Float getCoConcentration() {
        return coConcentration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material_delivery_statistic_nickel_sulfate.co_concentration
     *
     * @param coConcentration the value for material_delivery_statistic_nickel_sulfate.co_concentration
     *
     * @mbggenerated
     */
    public void setCoConcentration(Float coConcentration) {
        this.coConcentration = coConcentration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material_delivery_statistic_nickel_sulfate.mn_concentration
     *
     * @return the value of material_delivery_statistic_nickel_sulfate.mn_concentration
     *
     * @mbggenerated
     */
    public Float getMnConcentration() {
        return mnConcentration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material_delivery_statistic_nickel_sulfate.mn_concentration
     *
     * @param mnConcentration the value for material_delivery_statistic_nickel_sulfate.mn_concentration
     *
     * @mbggenerated
     */
    public void setMnConcentration(Float mnConcentration) {
        this.mnConcentration = mnConcentration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material_delivery_statistic_nickel_sulfate.ni_metallicity
     *
     * @return the value of material_delivery_statistic_nickel_sulfate.ni_metallicity
     *
     * @mbggenerated
     */
    public Float getNiMetallicity() {
        return niMetallicity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material_delivery_statistic_nickel_sulfate.ni_metallicity
     *
     * @param niMetallicity the value for material_delivery_statistic_nickel_sulfate.ni_metallicity
     *
     * @mbggenerated
     */
    public void setNiMetallicity(Float niMetallicity) {
        this.niMetallicity = niMetallicity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material_delivery_statistic_nickel_sulfate.co_metallicity
     *
     * @return the value of material_delivery_statistic_nickel_sulfate.co_metallicity
     *
     * @mbggenerated
     */
    public Float getCoMetallicity() {
        return coMetallicity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material_delivery_statistic_nickel_sulfate.co_metallicity
     *
     * @param coMetallicity the value for material_delivery_statistic_nickel_sulfate.co_metallicity
     *
     * @mbggenerated
     */
    public void setCoMetallicity(Float coMetallicity) {
        this.coMetallicity = coMetallicity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material_delivery_statistic_nickel_sulfate.mn_metallicity
     *
     * @return the value of material_delivery_statistic_nickel_sulfate.mn_metallicity
     *
     * @mbggenerated
     */
    public Float getMnMetallicity() {
        return mnMetallicity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material_delivery_statistic_nickel_sulfate.mn_metallicity
     *
     * @param mnMetallicity the value for material_delivery_statistic_nickel_sulfate.mn_metallicity
     *
     * @mbggenerated
     */
    public void setMnMetallicity(Float mnMetallicity) {
        this.mnMetallicity = mnMetallicity;
    }
}
