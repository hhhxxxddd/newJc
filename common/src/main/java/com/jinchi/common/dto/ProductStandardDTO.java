package com.jinchi.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProductStandardDTO {

    String commonBath;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    Date createTime;

    String createPeople;

    Integer audit;

    Integer commonBatchId;

    public String getCommonBath() {
        return commonBath;
    }

    public void setCommonBathch(String commonBath) {
        this.commonBath = commonBath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePeople() {
        return createPeople;
    }

    public void setCreatePeople(String createPeople) {
        this.createPeople = createPeople;
    }

    public Integer getAudit() {
        return audit;
    }

    public void setAudit(Integer audit) {
        this.audit = audit;
    }

    public Integer getCommonBatchId() {
        return commonBatchId;
    }

    public void setCommonBatchId(Integer commonBatchId) {
        this.commonBatchId = commonBatchId;
    }
}
