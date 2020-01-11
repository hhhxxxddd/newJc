package com.jinchi.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-10 16:46
 * @description:
 **/

public class ProductionBatchAssayMapDTO {
    private Integer sampleCode;

    private String batch;

    private Integer assayTypeId;

    private String assayType;

    private String deliveryPeople;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deliveryDate;

    private String deliveryFactoty;

    private String testItems;

    public Integer getSampleCode() {
        return sampleCode;
    }

    public void setSampleCode(Integer sampleCode) {
        this.sampleCode = sampleCode;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getAssayType() {
        return assayType;
    }

    public void setAssayType(String assayType) {
        this.assayType = assayType;
    }

    public String getDeliveryPeople() {
        return deliveryPeople;
    }

    public void setDeliveryPeople(String deliveryPeople) {
        this.deliveryPeople = deliveryPeople;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryFactoty() {
        return deliveryFactoty;
    }

    public void setDeliveryFactoty(String deliveryFactoty) {
        this.deliveryFactoty = deliveryFactoty;
    }

    public String getTestItems() {
        return testItems;
    }

    public void setTestItems(String testItems) {
        this.testItems = testItems;
    }

    public Integer getAssayTypeId() {
        return assayTypeId;
    }

    public void setAssayTypeId(Integer assayTypeId) {
        this.assayTypeId = assayTypeId;
    }
}
