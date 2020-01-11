package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-05 14:46
 * @description: 设备点检模块 点检操作员 点检历史记录 入参dto
 **/

public class DeviceSpotcheckRecordHistoryPostDTO {

    private Integer userId;//用户id

    private Integer statusId;//点检标记位

    private Long deviceCode;//设备编号

    private Short processCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;//起始日期

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;//截止日期

    private Integer page;

    private Integer size;

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

    public Integer getUserId() {
        return userId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Long getDeviceCode() {
        return deviceCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setDeviceCode(Long deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Short getProcessCode() {
        return processCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setProcessCode(Short processCode) {
        this.processCode = processCode;
    }
}
