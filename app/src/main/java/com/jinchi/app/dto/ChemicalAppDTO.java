package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ChemicalAppDTO {
    Integer id;

    String name;

    String commonBatchNumber;

    List audit;

    String deliveryPeople;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date deliveryDate;

    String judgePeole;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date judgeDate;

    Integer isQuality;

    List testItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommonBatchNumber() {
        return commonBatchNumber;
    }

    public void setCommonBatchNumber(String commonBatchNumber) {
        this.commonBatchNumber = commonBatchNumber;
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

    public Integer getIsQuality() {
        return isQuality;
    }

    public void setIsQuality(Integer isQuality) {
        this.isQuality = isQuality;
    }

    public List getTestItems() {
        return testItems;
    }

    public void setTestItems(List testItems) {
        this.testItems = testItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List getAudit() {
        return audit;
    }

    public void setAudit(List audit) {
        this.audit = audit;
    }

    public String getJudgePeole() {
        return judgePeole;
    }

    public void setJudgePeole(String judgePeole) {
        this.judgePeole = judgePeole;
    }

    public Date getJudgeDate() {
        return judgeDate;
    }

    public void setJudgeDate(Date judgeDate) {
        this.judgeDate = judgeDate;
    }
}
