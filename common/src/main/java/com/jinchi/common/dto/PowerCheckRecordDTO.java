package com.jinchi.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jinchi.common.domain.PowerCheckRecordDetail;
import com.jinchi.common.domain.PowerCheckRecordHead;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-22 13:34
 * @description:
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PowerCheckRecordDTO {

    PowerCheckRecordHead head;

    List<PowerCheckRecordDetail> details;

    String siteName;

    Integer flag;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date effectiveDate;

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public PowerCheckRecordHead getHead() {
        return head;
    }

    public void setHead(PowerCheckRecordHead head) {
        this.head = head;
    }

    public List<PowerCheckRecordDetail> getDetails() {
        return details;
    }

    public void setDetails(List<PowerCheckRecordDetail> details) {
        this.details = details;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
