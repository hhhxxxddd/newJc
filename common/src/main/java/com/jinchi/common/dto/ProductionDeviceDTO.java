package com.jinchi.common.dto;

public class ProductionDeviceDTO {

    Long deviceCode;

    String deviceName;

    String fixedassets;

    String specification;

    Boolean chosen;

    public Long getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(Long deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getFixedassets() {
        return fixedassets;
    }

    public void setFixedassets(String fixedassets) {
        this.fixedassets = fixedassets;
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
