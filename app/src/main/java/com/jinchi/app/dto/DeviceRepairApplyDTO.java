package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceRepairApplyDTO {
    Long repairId;
    Integer deptCode;
    String deptName;
    Long deviceCode;
    String deviceName;
    String fixedassets;
    String faultComment;
    Integer emergeStatus;
    //flag=1,提交，flag=0，暂存
    Integer flag;
    Integer userId;

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Long getRepairId() {
        return repairId;
    }

    public void setRepairId(Long repairId) {
        this.repairId = repairId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(Integer deptCode) {
        this.deptCode = deptCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Long getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(Long deviceCode) {
        this.deviceCode = deviceCode;
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

    public String getFaultComment() {
        return faultComment;
    }

    public void setFaultComment(String faultComment) {
        this.faultComment = faultComment;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getEmergeStatus() {
        return emergeStatus;
    }

    public void setEmergeStatus(Integer emergeStatus) {
        this.emergeStatus = emergeStatus;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}

