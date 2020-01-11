package com.jinchi.common.dto.processParamer;

import com.jinchi.common.domain.ProcessParametersXdDetail;
import com.jinchi.common.domain.ProcessParametersXdDevices;

import java.util.List;

public class Ch {

    private List<ProcessParametersXdDevices> devices;

    private ProcessParametersXdDetail detail;

    public List<ProcessParametersXdDevices> getDevices() {
        return devices;
    }

    public void setDevices(List<ProcessParametersXdDevices> devices) {
        this.devices = devices;
    }

    public ProcessParametersXdDetail getDetail() {
        return detail;
    }

    public void setDetail(ProcessParametersXdDetail detail) {
        this.detail = detail;
    }
}
