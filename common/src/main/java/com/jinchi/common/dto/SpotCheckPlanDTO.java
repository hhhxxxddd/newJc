package com.jinchi.common.dto;

import com.jinchi.common.domain.DeviceSpotcheckPlans;

public class SpotCheckPlanDTO {
    DeviceSpotcheckPlans deviceSpotcheckPlans;
    int detailNum;

    @Override
    public String toString() {
        return "SpotCheckPlanDTO{" +
                "deviceSpotcheckPlans=" + deviceSpotcheckPlans +
                ", detailNum=" + detailNum +
                '}';
    }

    public DeviceSpotcheckPlans getDeviceSpotcheckPlans() {
        return deviceSpotcheckPlans;
    }

    public void setDeviceSpotcheckPlans(DeviceSpotcheckPlans deviceSpotcheckPlans) {
        this.deviceSpotcheckPlans = deviceSpotcheckPlans;
    }

    public int getDetailNum() {
        return detailNum;
    }

    public void setDetailNum(int detailNum) {
        this.detailNum = detailNum;
    }
}
