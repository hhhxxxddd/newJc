package com.jinchi.common.dto;

public class ProcessAssignDTO {

    Long deviceCode;

    String fixedassetsCode;

    String deviceName;

    String specification;

    Boolean chosen;

    public Long getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(Long deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getFixedassetsCode() {
        return fixedassetsCode;
    }

    public void setFixedassetsCode(String fixedassetsCode) {
        this.fixedassetsCode = fixedassetsCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Boolean getChosen() {
        return chosen;
    }

    public void setChosen(Boolean chosen) {
        this.chosen = chosen;
    }
}
