package com.jinchi.common.dto;

import com.jinchi.common.domain.DeviceSpotcheckRecordDetails;
import com.jinchi.common.domain.DeviceSpotcheckRecordHead;

import java.util.List;

public class DeviceSpotCheckRecordHeadDTO {
    DeviceSpotcheckRecordHead deviceSpotcheckRecordHead;
    List<DeviceSpotcheckRecordDetails> deviceSpotcheckRecordDetailsList;
    String spotPeople;
    String confirmPeople;

    @Override
    public String toString() {
        return "DeviceSpotCheckRecordHeadDTO{" +
                "deviceSpotcheckRecordHead=" + deviceSpotcheckRecordHead +
                ", deviceSpotcheckRecordDetailsList=" + deviceSpotcheckRecordDetailsList +
                ", spotPeople='" + spotPeople + '\'' +
                ", confirmPeople='" + confirmPeople + '\'' +
                '}';
    }

    public DeviceSpotcheckRecordHead getDeviceSpotcheckRecordHead() {
        return deviceSpotcheckRecordHead;
    }

    public void setDeviceSpotcheckRecordHead(DeviceSpotcheckRecordHead deviceSpotcheckRecordHead) {
        this.deviceSpotcheckRecordHead = deviceSpotcheckRecordHead;
    }

    public List<DeviceSpotcheckRecordDetails> getDeviceSpotcheckRecordDetailsList() {
        return deviceSpotcheckRecordDetailsList;
    }

    public void setDeviceSpotcheckRecordDetailsList(List<DeviceSpotcheckRecordDetails> deviceSpotcheckRecordDetailsList) {
        this.deviceSpotcheckRecordDetailsList = deviceSpotcheckRecordDetailsList;
    }

    public String getSpotPeople() {
        return spotPeople;
    }

    public void setSpotPeople(String spotPeople) {
        this.spotPeople = spotPeople;
    }

    public String getConfirmPeople() {
        return confirmPeople;
    }

    public void setConfirmPeople(String confirmPeople) {
        this.confirmPeople = confirmPeople;
    }
}
