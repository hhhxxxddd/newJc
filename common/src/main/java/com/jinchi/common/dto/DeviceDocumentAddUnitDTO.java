package com.jinchi.common.dto;

import com.jinchi.common.domain.BasicInfoDept;
import com.jinchi.common.domain.DeviceDocumentUnit;

import java.util.List;


public class DeviceDocumentAddUnitDTO {
    DeviceDocumentUnit deviceDocumentUnit;
    List<String> arrName;
    List<String> arrValue;

    @Override
    public String toString() {
        return "DeviceDocumentAddUnitDTO{" +
                "deviceDocumentUnit=" + deviceDocumentUnit +
                ", arrName=" + arrName +
                ", arrValue=" + arrValue +
                '}';
    }

    public List<String> getArrName() {
        return arrName;
    }

    public void setArrName(List<String> arrName) {
        this.arrName = arrName;
    }

    public List<String> getArrValue() {
        return arrValue;
    }

    public void setArrValue(List<String> arrValue) {
        this.arrValue = arrValue;
    }

    public void setDeviceDocumentUnit(DeviceDocumentUnit deviceDocumentUnit) {
        this.deviceDocumentUnit = deviceDocumentUnit;
    }

    public DeviceDocumentUnit getDeviceDocumentUnit() {
        return deviceDocumentUnit;
    }
}
