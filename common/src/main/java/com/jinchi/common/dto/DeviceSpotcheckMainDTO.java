package com.jinchi.common.dto;

import com.jinchi.common.domain.BasicInfoDeviceStatus;
import com.jinchi.common.domain.DeviceDocumentMain;
import com.jinchi.common.domain.DeviceMainManual;

import java.util.Arrays;
import java.util.List;

public class DeviceSpotcheckMainDTO {
    DeviceDocumentMain deviceDocumentMain;
   int flag;

    @Override
    public String toString() {
        return "DeviceSpotcheckMainDTO{" +
                "deviceDocumentMain=" + deviceDocumentMain +
                ", flag=" + flag +
                '}';
    }

    public DeviceDocumentMain getDeviceDocumentMain() {
        return deviceDocumentMain;
    }

    public void setDeviceDocumentMain(DeviceDocumentMain deviceDocumentMain) {
        this.deviceDocumentMain = deviceDocumentMain;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
