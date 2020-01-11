package com.jinchi.common.dto;

import com.jinchi.common.domain.*;

import java.util.List;

public class DeviceSpotcheckModelsHeadDTO {
    DeviceSpotcheckModelsHead deviceSpotcheckModelsHead;
    List<DeviceSpotcheckModelsDetails> deviceSpotcheckModelsDetails;
    String peopleName;


    @Override
    public String toString() {
        return "DeviceSpotcheckModelsHeadDTO{" +
                "DeviceSpotcheckModelsHead=" + deviceSpotcheckModelsHead +
                ", DeviceSpotcheckModelsdetails=" + deviceSpotcheckModelsDetails +
                '}';
    }

    public DeviceSpotcheckModelsHead getDeviceSpotcheckModelsHead() {
        return deviceSpotcheckModelsHead;
    }

    public List<DeviceSpotcheckModelsDetails> getDeviceSpotcheckModelsDetails() {
        return deviceSpotcheckModelsDetails;
    }

    public void setDeviceSpotcheckModelsHead(DeviceSpotcheckModelsHead deviceSpotcheckModelsHead) {
        this.deviceSpotcheckModelsHead = deviceSpotcheckModelsHead;
    }

    public void setDeviceSpotcheckModelsDetails(List<DeviceSpotcheckModelsDetails> deviceSpotcheckModelsDetails) {
        this.deviceSpotcheckModelsDetails = deviceSpotcheckModelsDetails;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }
}
