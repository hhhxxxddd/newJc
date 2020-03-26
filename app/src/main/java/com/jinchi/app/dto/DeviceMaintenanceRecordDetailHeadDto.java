package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-08-30 16:15
 * @description:
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceMaintenanceRecordDetailHeadDto {

    private Long headId;


    private Long planCode;


    private Long deviceCode;


    private String fixedassetsCode;


    private String deviceName;


    private Integer deptCode;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date planDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date receiveDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date finishiDate;

    private Integer maintPeople;

    private String abnormalcontent;

    private Integer editFlag;

    private String[] units = new String[]{"个","台","套","米"};

    public DeviceMaintenanceRecordDetailHeadDto() {
    }

    public DeviceMaintenanceRecordDetailHeadDto(Long headId, Long planCode, Long deviceCode, String fixedassetsCode, String deviceName, Integer deptCode, Date planDate, Date receiveDate, Date finishiDate, Integer maintPeople, String abnormalcontent, Integer editFlag, String[] units) {
        this.headId = headId;
        this.planCode = planCode;
        this.deviceCode = deviceCode;
        this.fixedassetsCode = fixedassetsCode;
        this.deviceName = deviceName;
        this.deptCode = deptCode;
        this.planDate = planDate;
        this.receiveDate = receiveDate;
        this.finishiDate = finishiDate;
        this.maintPeople = maintPeople;
        this.abnormalcontent = abnormalcontent;
        this.editFlag = editFlag;
        this.units = units;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Long getHeadId() {
        return headId;
    }

    public void setHeadId(Long headId) {
        this.headId = headId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Long getPlanCode() {
        return planCode;
    }

    public void setPlanCode(Long planCode) {
        this.planCode = planCode;
    }

    public Long getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(Long deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getFixedassetsCode() {
        return fixedassetsCode;
    }

    public void setFixedassetsCode(String fixedassetsCode) {
        this.fixedassetsCode = fixedassetsCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(Integer deptCode) {
        this.deptCode = deptCode;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getFinishiDate() {
        return finishiDate;
    }

    public void setFinishiDate(Date finishiDate) {
        this.finishiDate = finishiDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getMaintPeople() {
        return maintPeople;
    }

    public void setMaintPeople(Integer maintPeople) {
        this.maintPeople = maintPeople;
    }

    public String getAbnormalcontent() {
        return abnormalcontent;
    }

    public void setAbnormalcontent(String abnormalcontent) {
        this.abnormalcontent = abnormalcontent;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(Integer editFlag) {
        this.editFlag = editFlag;
    }

    public String[] getUnits() {
        return units;
    }

    public void setUnits(String[] units) {
        this.units = units;
    }
}
