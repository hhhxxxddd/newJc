package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceDTO {

    Integer deptId;

    String deptName;

    Long deviceId;

    String deviceName;

    String fixedassets;

    String IdCard;

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String idCard) {
        IdCard = idCard;
    }

    public String getFixedassets() {
        return fixedassets;
    }

    public void setFixedassets(String fixedassets) {
        this.fixedassets = fixedassets;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
