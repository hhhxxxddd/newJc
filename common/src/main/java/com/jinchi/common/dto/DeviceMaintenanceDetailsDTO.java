package com.jinchi.common.dto;

import com.jinchi.common.domain.BasicInfoDept;
import com.jinchi.common.domain.DeviceMaintenanceAccessory;
import com.jinchi.common.domain.DeviceMaintenanceRecordDetails;
import com.jinchi.common.domain.DeviceMaintenanceRecordHead;

import java.util.List;

public class DeviceMaintenanceDetailsDTO {

   private DeviceMaintenanceRecordHead deviceMaintenanceRecordHead;
   private List<DeviceMaintenanceRecordDetails> deviceMaintenanceRecordDetails;
   private  BasicInfoDept basicInfoDept;

   private  List<DeviceMaintenanceAccessory> deviceMaintenanceAccessory;
   private String setPeople;


    @Override
    public String toString() {
        return "DeviceMaintenanceDetailsDTO{" +
                "deviceMaintenanceRecordHead=" + deviceMaintenanceRecordHead +
                ", deviceMaintenanceRecordDetails=" + deviceMaintenanceRecordDetails +
                ", basicInfoDept=" + basicInfoDept +
                ", deviceMaintenanceAccessory=" + deviceMaintenanceAccessory +
                ", setPeople='" + setPeople + '\'' +
                '}';
    }

    public String getSetPeople() {
        return setPeople;
    }

    public void setSetPeople(String setPeople) {
        this.setPeople = setPeople;
    }

    public List<DeviceMaintenanceAccessory> getDeviceMaintenanceAccessory() {
        return deviceMaintenanceAccessory;
    }

    public void setDeviceMaintenanceAccessory(List<DeviceMaintenanceAccessory> deviceMaintenanceAccessory) {
        this.deviceMaintenanceAccessory = deviceMaintenanceAccessory;
    }

    public List<DeviceMaintenanceRecordDetails> getDeviceMaintenanceRecordDetails() {
        return deviceMaintenanceRecordDetails;
    }

    public void setDeviceMaintenanceRecordDetails(List<DeviceMaintenanceRecordDetails> deviceMaintenanceRecordDetails) {
        this.deviceMaintenanceRecordDetails = deviceMaintenanceRecordDetails;
    }

    public DeviceMaintenanceRecordHead getDeviceMaintenanceRecordHead() {
        return deviceMaintenanceRecordHead;
    }

    public void setDeviceMaintenanceRecordHead(DeviceMaintenanceRecordHead deviceMaintenanceRecordHead) {
        this.deviceMaintenanceRecordHead = deviceMaintenanceRecordHead;
    }


    public BasicInfoDept getBasicInfoDept() {
        return basicInfoDept;
    }

    public void setBasicInfoDept(BasicInfoDept basicInfoDept) {
        this.basicInfoDept = basicInfoDept;
    }
}
