package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-08-26 16:14
 * @description:
 **/
public class DeviceMaintenanceAccessoryDto {

    @JsonProperty(value = "accessoryId")
    private String code;
    private String name;
    private String specification;
    private String counts;
    @JsonProperty(value = "recordId")
    private String planCode;
    private String units;

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public DeviceMaintenanceAccessoryDto(){
        super();
    }

    public DeviceMaintenanceAccessoryDto(String code, String name, String specification, String counts, String planCode, String units) {
        this.code = code;
        this.name = name;
        this.specification = specification;
        this.counts = counts;
        this.planCode = planCode;
        this.units = units;
    }
}
