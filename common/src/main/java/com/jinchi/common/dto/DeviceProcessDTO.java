package com.jinchi.common.dto;

import com.jinchi.common.domain.BasicInfoDeviceStatus;
import com.jinchi.common.domain.ProductionProcessDeviceMap;

public class DeviceProcessDTO {

    ProductionProcessDeviceMap deviceMap;

    BasicInfoDeviceStatus status;

    public ProductionProcessDeviceMap getDeviceMap() {
        return deviceMap;
    }

    public void setDeviceMap(ProductionProcessDeviceMap deviceMap) {
        this.deviceMap = deviceMap;
    }

    public BasicInfoDeviceStatus getStatus() {
        return status;
    }

    public void setStatus(BasicInfoDeviceStatus status) {
        this.status = status;
    }
}
