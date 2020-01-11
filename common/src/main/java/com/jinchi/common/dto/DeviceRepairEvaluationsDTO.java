package com.jinchi.common.dto;

import com.jinchi.common.domain.DeviceRepairEvaluations;

import java.util.List;

public class DeviceRepairEvaluationsDTO {
   List<DeviceRepairEvaluations>  deviceRepairEvaluations;
   String evaluationsResult;


    @Override
    public String toString() {
        return " DeviceRepairEvaluationsDTO {" +
                "deviceRepairEvaluations=" + deviceRepairEvaluations +
                ",evaluationsResult=" + evaluationsResult +
                '}';
    }

    public List<DeviceRepairEvaluations> getDeviceRepairEvaluations() {
        return deviceRepairEvaluations;
    }

    public String getEvaluationsResult() {
        return evaluationsResult;
    }

    public void setDeviceRepairEvaluations(List<DeviceRepairEvaluations> deviceRepairEvaluations) {
        this.deviceRepairEvaluations = deviceRepairEvaluations;
    }

    public void setEvaluationsResult(String evaluationsResult) {
        this.evaluationsResult = evaluationsResult;
    }
}
