package com.jinchi.common.dto;

import com.jinchi.common.domain.DeviceMaintenanceItems;
import com.jinchi.common.domain.DeviceMaintenancePlansDetails;
import com.jinchi.common.domain.DeviceMaintenancePlansHead;

import java.util.List;

public class DeviceMaintenancePlansHeadDTO {
    DeviceMaintenancePlansHead deviceMaintenancePlansHead;
    List<DeviceMaintenanceItems> deviceMaintenanceItems;
    List<DeviceMaintenancePlansDetails> deviceMaintenancePlansDetails;
    String setPeopleName;
    int detailNum;
    boolean generateMaint;

    @Override
    public String toString() {
        return "DeviceMaintenancePlansHeadDTO{" +
                "deviceMaintenancePlansHead=" + deviceMaintenancePlansHead +
                ", deviceMaintenanceItems=" + deviceMaintenanceItems +
                ", deviceMaintenancePlansDetails=" + deviceMaintenancePlansDetails +
                ", setPeopleName='" + setPeopleName + '\'' +
                ", detailNum=" + detailNum +
                ", generateMaint=" + generateMaint +
                '}';
    }

    public String getSetPeopleName() {
        return setPeopleName;
    }

    public void setSetPeopleName(String setPeopleName) {
        this.setPeopleName = setPeopleName;
    }

    public boolean isGenerateMaint() {
        return generateMaint;
    }

    public void setGenerateMaint(boolean generateMaint) {
        this.generateMaint = generateMaint;
    }

    public int getDetailNum() {
        return detailNum;
    }

    public void setDetailNum(int detailNum) {
        this.detailNum = detailNum;
    }

    public List<DeviceMaintenancePlansDetails> getDeviceMaintenancePlansDetails() {
        return deviceMaintenancePlansDetails;
    }

    public void setDeviceMaintenancePlansDetails(List<DeviceMaintenancePlansDetails> deviceMaintenancePlansDetails) {
        this.deviceMaintenancePlansDetails = deviceMaintenancePlansDetails;
    }

    public DeviceMaintenancePlansHead getDeviceMaintenancePlansHead() {
        return deviceMaintenancePlansHead;
    }

    public void setDeviceMaintenancePlansHead(DeviceMaintenancePlansHead deviceMaintenancePlansHead) {
        this.deviceMaintenancePlansHead = deviceMaintenancePlansHead;
    }

    public List<DeviceMaintenanceItems> getDeviceMaintenanceItems() {
        return deviceMaintenanceItems;
    }

    public void setDeviceMaintenanceItems(List<DeviceMaintenanceItems> deviceMaintenanceItems) {
        this.deviceMaintenanceItems = deviceMaintenanceItems;
    }

}
