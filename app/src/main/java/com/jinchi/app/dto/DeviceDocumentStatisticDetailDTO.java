package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-12 00:17
 * @description:
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceDocumentStatisticDetailDTO {
    private String deviceName;
    private Integer deviceNum;
    private Integer inServiceNum;
    private Integer standbyNum;
    private Integer reconditionNum;
    private Integer scrapNum;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(Integer deviceNum) {
        this.deviceNum = deviceNum;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getInServiceNum() {
        return inServiceNum;
    }

    public void setInServiceNum(Integer inServiceNum) {
        this.inServiceNum = inServiceNum;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getStandbyNum() {
        return standbyNum;
    }

    public void setStandbyNum(Integer standbyNum) {
        this.standbyNum = standbyNum;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getReconditionNum() {
        return reconditionNum;
    }

    public void setReconditionNum(Integer reconditionNum) {
        this.reconditionNum = reconditionNum;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getScrapNum() {
        return scrapNum;
    }

    public void setScrapNum(Integer scrapNum) {
        this.scrapNum = scrapNum;
    }
}
