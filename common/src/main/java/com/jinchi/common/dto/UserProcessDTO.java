package com.jinchi.common.dto;

import com.jinchi.common.domain.BasicInfoDeviceProcess;
import com.jinchi.common.domain.BasicInfoUserDeviceProcessMap;

import java.util.List;

public class UserProcessDTO {

    AuthUserDTO user;

    List<BasicInfoDeviceProcess> processes;

    public AuthUserDTO getUser() {
        return user;
    }

    public void setUser(AuthUserDTO user) {
        this.user = user;
    }

    public List<BasicInfoDeviceProcess> getProcesses() {
        return processes;
    }

    public void setProcesses(List<BasicInfoDeviceProcess> processes) {
        this.processes = processes;
    }
}
