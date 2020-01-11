package com.jinchi.common.dto;

import com.jinchi.common.domain.BasicInfoDept;
import com.jinchi.common.domain.DeviceUnitAccessory;
import com.jinchi.common.domain.DeviceDocumentUnit;

import java.util.List;


public class DeviceDocumentUnitDTO {
    DeviceDocumentMainDTO deviceDocumentMainDTO;
    BasicInfoDept  basicInfoDept;
    DeviceDocumentUnit deviceDocumentUnit;
    List<String> arrName;
    List<String> arrValue;


    @Override
    public String toString() {
        return "DeviceDocumentUnitDTO{" +
                "deviceDocumentMainDTO=" + deviceDocumentMainDTO +
                ", basicInfoDept=" + basicInfoDept +
                ", deviceDocumentUnit=" + deviceDocumentUnit +
                ", arrName=" + arrName +
                ", arrValue=" + arrValue +
                '}';
    }

    public DeviceDocumentMainDTO getDeviceDocumentMainDTO() {
        return deviceDocumentMainDTO;
    }

    public void setDeviceDocumentMainDTO(DeviceDocumentMainDTO deviceDocumentMainDTO) {
        this.deviceDocumentMainDTO = deviceDocumentMainDTO;
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


    public void setBasicInfoDept(BasicInfoDept basicInfoDept) {
        this.basicInfoDept = basicInfoDept;
    }

    public void setDeviceDocumentUnit(DeviceDocumentUnit deviceDocumentUnit) {
        this.deviceDocumentUnit = deviceDocumentUnit;
    }


    public BasicInfoDept getBasicInfoDept() {
        return basicInfoDept;
    }

    public DeviceDocumentUnit getDeviceDocumentUnit() {
        return deviceDocumentUnit;
    }
}
