package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-03 10:54
 * @description: 设备点检模块 点检操作员 点检计划 返参dto
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceSpotcheckPlansGetDTO {
    private Long deviceCode;
    private String deviceName;
    private String fixedassetsCode;

    private Integer deptCode;
    private String deptName;

    private Long planCode;
    private String planName;//点检名称

    private String effFlag;

    private Short processCode;//所属工序id
    private String processName;//工序名称

    private Integer num;

    public Short getProcessCode() {
        return processCode;
    }

    public void setProcessCode(Short processCode) {
        this.processCode = processCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public String getFixedassetsCode() {
        return fixedassetsCode;
    }

    public void setFixedassetsCode(String fixedassetsCode) {
        this.fixedassetsCode = fixedassetsCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(Integer deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Long getPlanCode() {
        return planCode;
    }

    public void setPlanCode(Long planCode) {
        this.planCode = planCode;
    }

    public String getEffFlag() {
        return effFlag;
    }

    public void setEffFlag(String effFlag) {
        this.effFlag = effFlag;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }
}
