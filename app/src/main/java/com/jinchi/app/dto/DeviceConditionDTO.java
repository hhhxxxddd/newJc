package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-14 11:03
 * @description:
 **/

public class DeviceConditionDTO {
    private Short processCode;
    private Integer deptCode;
    private Integer page;
    private Integer size;
    private Long deviceCode;
    private String idNum;

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Long getDeviceCode() {
        return deviceCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setDeviceCode(Long deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Short getProcessCode() {
        return processCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setProcessCode(Short processCode) {
        this.processCode = processCode;
    }

    public Integer getDeptCode() {
        return deptCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setDeptCode(Integer deptCode) {
        this.deptCode = deptCode;
    }

    public Integer getPage() {
        return page;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setSize(Integer size) {
        this.size = size;
    }
}
