package com.jinchi.common.dto;

import com.jinchi.common.domain.BasicInfoDept;


import java.util.List;

public class DeviceSpotcheckModelsDTO {

   private BasicInfoDept basicInfoDept;
   private List<String> deviceName;

    @Override
    public String toString() {
        return "DeviceSpotcheckModelsDTO{" +
                "basicInfoDept=" + basicInfoDept +
                ", deviceName=" + deviceName +
                '}';
    }

    public BasicInfoDept getBasicInfoDept() {
        return basicInfoDept;
    }

    public List<String> getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(List<String> deviceName) {
        this.deviceName = deviceName;
    }

    public void setBasicInfoDept(BasicInfoDept basicInfoDept) {
        this.basicInfoDept = basicInfoDept;
    }


}
