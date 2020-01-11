package com.jinchi.common.dto;

import com.jinchi.common.domain.BasicInfoDeviceStatus;
import com.jinchi.common.domain.DeviceDocumentMain;
import com.jinchi.common.domain.DeviceMainManual;

import java.util.Arrays;
import java.util.List;

public class DeviceDocumentMainDTO {
    DeviceDocumentMain deviceDocumentMain;
    BasicInfoDeviceStatus basicInfoDeviceStatus;
    List<String> arrName;
    List<String> arrValue;
    String deviceStatus;
    String deptName;
    List<DeviceMainManual> manuals;
    Long[] adds;
    Long[] dels;

    @Override
    public String toString() {
        return "DeviceDocumentMainDTO{" +
                "deviceDocumentMain=" + deviceDocumentMain +
                ", basicInfoDeviceStatus=" + basicInfoDeviceStatus +
                ", arrName=" + arrName +
                ", arrValue=" + arrValue +
                ", deviceStatus='" + deviceStatus + '\'' +
                ", deptName='" + deptName + '\'' +
                ", manuals=" + manuals +
                ", adds=" + Arrays.toString(adds) +
                ", dels=" + Arrays.toString(dels) +
                '}';
    }

    public BasicInfoDeviceStatus getBasicInfoDeviceStatus() {
        return basicInfoDeviceStatus;
    }

    public void setBasicInfoDeviceStatus(BasicInfoDeviceStatus basicInfoDeviceStatus) {
        this.basicInfoDeviceStatus = basicInfoDeviceStatus;
    }

    public Long[] getAdds() {
        return adds;
    }

    public void setAdds(Long[] adds) {
        this.adds = adds;
    }

    public Long[] getDels() {
        return dels;
    }

    public void setDels(Long[] dels) {
        this.dels = dels;
    }

    public List<DeviceMainManual> getManuals() {
        return manuals;
    }

    public void setManuals(List<DeviceMainManual> manuals) {
        this.manuals = manuals;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public DeviceDocumentMain getDeviceDocumentMain() {
        return deviceDocumentMain;
    }

    public void setDeviceDocumentMain(DeviceDocumentMain deviceDocumentMain) {
        this.deviceDocumentMain = deviceDocumentMain;
    }

    public List<String> getArrName() {
        return arrName;
    }

    public void setArrName(List<String> arrName) {
        this.arrName = arrName;
    }

    public List<String> getArrValue() {
        return arrValue;
    }

    public void setArrValue(List<String> arrValue) {
        this.arrValue = arrValue;
    }


}
