package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceRepairDetail {
    Long repaidId;

    String deptName;

    String fixedassets;

    String deviceName;

    String faultComment;

    String timeConsuming;

    Integer emergeStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date reportTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date reveiveTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date finishiTime;

    String repairPeople;

    String phone;

    String faultReason;

    String partners;

    List<DeviceRepairAccDTO> accs;

    List<EvaluItem> eis;

    String evaluResult;

    Integer repairStatuts;

    String[] unitContains = {"个", "台", "套", "米"};

    Integer isCancel;

    public String getTimeConsuming() {
        return timeConsuming;
    }

    public void setTimeConsuming(String timeConsuming) {
        this.timeConsuming = timeConsuming;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    public String[] getUnitContains() {
        return unitContains;
    }

    public void setUnitContains(String[] unitContains) {
        this.unitContains = unitContains;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Integer getRepairStatuts() {
        return repairStatuts;
    }

    public void setRepairStatuts(Integer repairStatuts) {
        this.repairStatuts = repairStatuts;
    }

    public List<EvaluItem> getEis() {
        return eis;
    }

    public void setEis(List<EvaluItem> eis) {
        this.eis = eis;
    }

    public String getEvaluResult() {
        return evaluResult;
    }

    public void setEvaluResult(String evaluResult) {
        this.evaluResult = evaluResult;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Long getRepaidId() {
        return repaidId;
    }

    public void setRepaidId(Long repaidId) {
        this.repaidId = repaidId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getFixedassets() {
        return fixedassets;
    }

    public void setFixedassets(String fixedassets) {
        this.fixedassets = fixedassets;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getFaultComment() {
        return faultComment;
    }

    public void setFaultComment(String faultComment) {
        this.faultComment = faultComment;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Integer getEmergeStatus() {
        return emergeStatus;
    }

    public void setEmergeStatus(Integer emergeStatus) {
        this.emergeStatus = emergeStatus;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getFaultReason() {
        return faultReason;
    }

    public void setFaultReason(String faultReason) {
        this.faultReason = faultReason;
    }

    public List<DeviceRepairAccDTO> getAccs() {
        return accs;
    }

    public void setAccs(List<DeviceRepairAccDTO> accs) {
        this.accs = accs;
    }

    public Date getReveiveTime() {
        return reveiveTime;
    }

    public void setReveiveTime(Date reveiveTime) {
        this.reveiveTime = reveiveTime;
    }

    public Date getFinishiTime() {
        return finishiTime;
    }

    public void setFinishiTime(Date finishiTime) {
        this.finishiTime = finishiTime;
    }

    public String getRepairPeople() {
        return repairPeople;
    }

    public void setRepairPeople(String repairPeople) {
        this.repairPeople = repairPeople;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPartners() {
        return partners;
    }

    public void setPartners(String partners) {
        this.partners = partners;
    }
}
