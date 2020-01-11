package com.jinchi.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-09 16:37
 * @description:
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceRepairDetailDTO {

    private Long repairCode;

    private Long maintCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date reportTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date planDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date finishDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date receiveTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finishTime;

    private String reportPeople;

    private String receivePeople;

    private String maintPeople;

    private Integer emergeStatus;

    public Long getRepairCode() {
        return repairCode;
    }

    public void setRepairCode(Long repairCode) {
        this.repairCode = repairCode;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getReportPeople() {
        return reportPeople;
    }

    public void setReportPeople(String reportPeople) {
        this.reportPeople = reportPeople;
    }

    public String getReceivePeople() {
        return receivePeople;
    }

    public void setReceivePeople(String receivePeople) {
        this.receivePeople = receivePeople;
    }

    public Integer getEmergeStatus() {
        return emergeStatus;
    }

    public void setEmergeStatus(Integer emergeStatus) {
        this.emergeStatus = emergeStatus;
    }

    public Long getMaintCode() {
        return maintCode;
    }

    public void setMaintCode(Long maintCode) {
        this.maintCode = maintCode;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getMaintPeople() {
        return maintPeople;
    }

    public void setMaintPeople(String maintPeople) {
        this.maintPeople = maintPeople;
    }
}
