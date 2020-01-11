package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-07 14:56
 * @description: 设备点检 点检操作员 提交或暂存 入参DTO
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceSpotcheckUpdateDTO {

    private Long deviceCode;

    private Long planCode;

    private Integer userId;

    private String scanIdCode;

    private Integer statusCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date scanTime;

    private String spotcheckComment;//点检备注

    List<SpotcheckItemDetailDTO> itemDTOs;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getScanIdCode() {
        return scanIdCode;
    }

    public void setScanIdCode(String scanIdCode) {
        this.scanIdCode = scanIdCode;
    }

    public Date getScanTime() {
        return scanTime;
    }

    public void setScanTime(Date scanTime) {
        this.scanTime = scanTime;
    }

    public String getSpotcheckComment() {
        return spotcheckComment;
    }

    public void setSpotcheckComment(String spotcheckComment) {
        this.spotcheckComment = spotcheckComment;
    }

    public List<SpotcheckItemDetailDTO> getItemDTOs() {
        return itemDTOs;
    }

    public void setItemDTOs(List<SpotcheckItemDetailDTO> itemDTOs) {
        this.itemDTOs = itemDTOs;
    }

    public Long getPlanCode() {
        return planCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setPlanCode(Long planCode) {
        this.planCode = planCode;
    }

    public Integer getUserId() {
        return userId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getDeviceCode() {
        return deviceCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setDeviceCode(Long deviceCode) {
        this.deviceCode = deviceCode;
    }
}
