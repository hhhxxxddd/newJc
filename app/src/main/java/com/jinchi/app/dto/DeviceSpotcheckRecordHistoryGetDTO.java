package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-05 14:55
 * @description: 设备点检模块 点检操作员 点检历史记录 反参dto
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceSpotcheckRecordHistoryGetDTO {
    private Long recordId;

    private String deviceName;

    private String fixedassetsCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finishTime; //点检完成时间

    private String spotcheckPeople;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date confirmTime; //确认时间

    private String confirmPeople;

    private Integer flag;

    private Integer errorNum;

    private Short processCode;

    private String processName;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Short getProcessCode() {
        return processCode;
    }

    public void setProcessCode(Short processCode) {
        this.processCode = processCode;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(Integer errorNum) {
        this.errorNum = errorNum;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getSpotcheckPeople() {
        return spotcheckPeople;
    }

    public void setSpotcheckPeople(String spotcheckPeople) {
        this.spotcheckPeople = spotcheckPeople;
    }
}
