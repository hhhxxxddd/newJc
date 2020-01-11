package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RepairPageData {
    Long repairId;

    String deviceName;

    String fixedassets;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date reportDate;

    Integer emergeFlag;

    String deptName;

    Long deviceId;

    Integer deptId;

    String faultComment;

    Integer repairStatus;

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Integer getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(Integer repairStatus) {
        this.repairStatus = repairStatus;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getFaultComment() {
        return faultComment;
    }

    public void setFaultComment(String faultComment) {
        this.faultComment = faultComment;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Long getRepairId() {
        return repairId;
    }

    public void setRepairId(Long repairId) {
        this.repairId = repairId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getFixedassets() {
        return fixedassets;
    }

    public void setFixedassets(String fixedassets) {
        this.fixedassets = fixedassets;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Integer getEmergeFlag() {
        return emergeFlag;
    }

    public void setEmergeFlag(Integer emergeFlag) {
        this.emergeFlag = emergeFlag;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "RepairPageData{" +
                "repairId=" + repairId +
                ", deviceName='" + deviceName + '\'' +
                ", fixedassets='" + fixedassets + '\'' +
                ", reportDate=" + reportDate +
                ", emergeFlag=" + emergeFlag +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
