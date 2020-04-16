package com.jinchi.common.dto;

import com.jinchi.common.domain.DeviceRepairAccessory;
import com.jinchi.common.domain.DeviceRepairApplication;

import java.util.List;

public class DeviceRepairApplicationDTO {
    DeviceRepairApplication deviceRepairApplication;
    List<DeviceRepairAccessory> deviceRepairAccessory ;
    String reportPeople;
    String receivePeople;
    String helpPeoples;


    public DeviceRepairApplication getDeviceRepairApplication() {
        return deviceRepairApplication;
    }

    public List<DeviceRepairAccessory> getDeviceRepairAccessory() {
        return deviceRepairAccessory;
    }

    public void setDeviceRepairApplication(DeviceRepairApplication deviceRepairApplication) {
        this.deviceRepairApplication = deviceRepairApplication;
    }

    public void setDeviceRepairAccessory(List<DeviceRepairAccessory> deviceRepairAccessory) {
        this.deviceRepairAccessory = deviceRepairAccessory;
    }

    public String getReportPeople() {
        return reportPeople;
    }

    public String getReceivePeople() {
        return receivePeople;
    }

    public void setReportPeople(String reportPeople) {
        this.reportPeople = reportPeople;
    }

    public void setReceivePeople(String receivePeople) {
        this.receivePeople = receivePeople;
    }

    @Override
    public String toString() {
        return "DeviceRepairApplicationDTO{" +
                "deviceRepairApplication=" + deviceRepairApplication +
                ", deviceRepairAccessory=" + deviceRepairAccessory +
                ", reportPeople='" + reportPeople + '\'' +
                ", receivePeople='" + receivePeople + '\'' +
                ", helpPeoples='" + helpPeoples + '\'' +
                '}';
    }

    public String getHelpPeoples() {
        return helpPeoples;
    }

    public void setHelpPeoples(String helpPeoples) {
        this.helpPeoples = helpPeoples;
    }
}
