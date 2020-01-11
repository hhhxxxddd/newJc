package com.jinchi.common.dto;

import com.jinchi.common.domain.DeviceDocumentMain;

public class SpotcheckDeviceDTO {
    String deviceName;
    Integer deptCode;

    @Override
    public String toString() {
        return "SpotcheckDeviceDTO{" +
                "deviceName=" + deviceName +
                ", deptCode=" + deptCode +
                '}';
    }

    public String getDeviceName() {
        return deviceName;
    }

    public Integer getDeptCode() {
        return deptCode;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setDeptCode(Integer deptCode) {
        this.deptCode = deptCode;
    }
}
