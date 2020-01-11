package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-06 14:46
 * @description: 设备点检 点检操作员 点检记录详情 DTO
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceSpotcheckRecordDetailDTO {
    private Long deviceCode;
    private String deviceName;
    private String fixedassetsCode;
    private Long planCode;
    private Long recordId;
    private String planName;

    private String deptName;

    private String deviceStatus;

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    //点检待确认详情还包含点检完成时间和点检人两个参数
    private String spotcheckPeople;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finishTime;

    //点检已确认详情还包含确认时间和确认人两个参数
    private String confirmPeople;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date confirmTime;

    private List<SpotcheckItemDetailDTO> detailDTOList;

    private String spotcheckComment;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date scanTime;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getSpotcheckComment() {
        return spotcheckComment;
    }

    public void setSpotcheckComment(String spotcheckComment) {
        this.spotcheckComment = spotcheckComment;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getFixedassetsCode() {
        return fixedassetsCode;
    }

    public void setFixedassetsCode(String fixedassetsCode) {
        this.fixedassetsCode = fixedassetsCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Long getPlanCode() {
        return planCode;
    }

    public void setPlanCode(Long planCode) {
        this.planCode = planCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<SpotcheckItemDetailDTO> getDetailDTOList() {
        return detailDTOList;
    }

    public void setDetailDTOList(List<SpotcheckItemDetailDTO> detailDTOList) {
        this.detailDTOList = detailDTOList;
    }

    public String getSpotcheckPeople() {
        return spotcheckPeople;
    }

    public void setSpotcheckPeople(String spotcheckPeople) {
        this.spotcheckPeople = spotcheckPeople;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getConfirmPeople() {
        return confirmPeople;
    }

    public void setConfirmPeople(String confirmPeople) {
        this.confirmPeople = confirmPeople;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getScanTime() {
        return scanTime;
    }

    public void setScanTime(Date scanTime) {
        this.scanTime = scanTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Long getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(Long deviceCode) {
        this.deviceCode = deviceCode;
    }
}
