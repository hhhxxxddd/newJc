package com.jinchi.common.dto;

import com.jinchi.common.domain.DevicePatrolModelsHead;
import com.jinchi.common.domain.DevicePatrolModelsItemDetails;
import com.jinchi.common.domain.DevicePatrolModelsLocationDetails;

import java.util.List;

public class DevicePatrolModelDTO {
    DevicePatrolModelsHead devicePatrolModelsHead;
    List<DevicePatrolModelsItemDetails> devicePatrolModelsItemDetailsList;
    List<DevicePatrolModelsLocationDetails> devicePatrolModelsLocationDetails;
    String setPeople;

    public DevicePatrolModelsHead getDevicePatrolModelsHead() {
        return devicePatrolModelsHead;
    }

    public void setDevicePatrolModelsHead(DevicePatrolModelsHead devicePatrolModelsHead) {
        this.devicePatrolModelsHead = devicePatrolModelsHead;
    }

    public List<DevicePatrolModelsItemDetails> getDevicePatrolModelsItemDetailsList() {
        return devicePatrolModelsItemDetailsList;
    }

    public void setDevicePatrolModelsItemDetailsList(List<DevicePatrolModelsItemDetails> devicePatrolModelsItemDetailsList) {
        this.devicePatrolModelsItemDetailsList = devicePatrolModelsItemDetailsList;
    }

    public List<DevicePatrolModelsLocationDetails> getDevicePatrolModelsLocationDetails() {
        return devicePatrolModelsLocationDetails;
    }

    public void setDevicePatrolModelsLocationDetails(List<DevicePatrolModelsLocationDetails> devicePatrolModelsLocationDetails) {
        this.devicePatrolModelsLocationDetails = devicePatrolModelsLocationDetails;
    }

    public String getSetPeople() {
        return setPeople;
    }

    public void setSetPeople(String setPeople) {
        this.setPeople = setPeople;
    }

    @Override
    public String toString() {
        return "DevicePatrolModelDTO{" +
                "devicePatrolModelsHead=" + devicePatrolModelsHead +
                ", devicePatrolModelsItemDetailsList=" + devicePatrolModelsItemDetailsList +
                ", devicePatrolModelsLocationDetails=" + devicePatrolModelsLocationDetails +
                ", setPeople='" + setPeople + '\'' +
                '}';
    }
}
