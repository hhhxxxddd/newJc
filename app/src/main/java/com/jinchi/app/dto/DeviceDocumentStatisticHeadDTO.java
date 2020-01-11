package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-11 23:39
 * @description:
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceDocumentStatisticHeadDTO {
    private Integer totalDevice;
    private Integer totalInService;
    private Integer totalStandby;
    private Integer totalRecondition;
    private Integer totalScrap;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getTotalDevice() {
        return totalDevice;
    }

    public void setTotalDevice(Integer totalDevice) {
        this.totalDevice = totalDevice;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getTotalInService() {
        return totalInService;
    }

    public void setTotalInService(Integer totalInService) {
        this.totalInService = totalInService;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getTotalStandby() {
        return totalStandby;
    }

    public void setTotalStandby(Integer totalStandby) {
        this.totalStandby = totalStandby;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getTotalRecondition() {
        return totalRecondition;
    }

    public void setTotalRecondition(Integer totalRecondition) {
        this.totalRecondition = totalRecondition;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getTotalScrap() {
        return totalScrap;
    }

    public void setTotalScrap(Integer totalScrap) {
        this.totalScrap = totalScrap;
    }
}
