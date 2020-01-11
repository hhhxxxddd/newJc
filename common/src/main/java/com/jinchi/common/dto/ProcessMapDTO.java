package com.jinchi.common.dto;

import com.jinchi.common.domain.ProductionProcessDeviceMap;

public class ProcessMapDTO {

    ProductionProcessDeviceMap productionProcessDeviceMap;

    String deptName;

    String status;

    String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductionProcessDeviceMap getProductionProcessDeviceMap() {
        return productionProcessDeviceMap;
    }

    public void setProductionProcessDeviceMap(ProductionProcessDeviceMap productionProcessDeviceMap) {
        this.productionProcessDeviceMap = productionProcessDeviceMap;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
