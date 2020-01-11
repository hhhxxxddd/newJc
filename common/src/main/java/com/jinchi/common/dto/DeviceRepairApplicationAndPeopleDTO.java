package com.jinchi.common.dto;

import com.jinchi.common.domain.DeviceRepairApplication;

public class DeviceRepairApplicationAndPeopleDTO {
    DeviceRepairApplication deviceRepairApplication;
    String reportPeople;
    String receivePeople;
    String deptName;


    @Override
    public String toString() {
        return " SecondDeptDTO{" +
                "deviceRepairApplication=" + deviceRepairApplication +
                "reportPeople" + reportPeople +
                "receivePeople" + receivePeople +
                '}';
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public DeviceRepairApplication getDeviceRepairApplication() {
        return deviceRepairApplication;
    }

    public String getReportPeople() {
        return reportPeople;
    }

    public String getReceivePeople() {
        return receivePeople;
    }

    public void setDeviceRepairApplication(DeviceRepairApplication deviceRepairApplication) {
        this.deviceRepairApplication = deviceRepairApplication;
    }

    public void setReportPeople(String reportPeople) {
        this.reportPeople = reportPeople;
    }

    public void setReceivePeople(String receivePeople) {
        this.receivePeople = receivePeople;
    }
}
