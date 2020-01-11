package com.jinchi.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-07 12:38
 * @description:
 **/

public class MaterialDeliveryStatisticHeadDTO {
    private Long code;
    private Integer periodCode;
    private Integer lineName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    private Byte flag;

    private String name;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Integer getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(Integer periodCode) {
        this.periodCode = periodCode;
    }

    public Integer getLineName() {
        return lineName;
    }

    public void setLineName(Integer lineName) {
        this.lineName = lineName;
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

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MaterialDeliveryStatisticHeadDTO(Long code, Integer periodCode, Integer lineName, Date startTime, Date endTime, Byte flag, String name) {
        this.code = code;
        this.periodCode = periodCode;
        this.lineName = lineName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.flag = flag;
        this.name = name;
    }
}
