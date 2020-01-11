package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-12 14:34
 * @description:
 **/

public class DeviceSpotcheckStatisticDetailDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date statisticTime;

    private Integer spotcheckNum;

    private Integer anomalyNum;


    public Date getStatisticTime() {
        return statisticTime;
    }

    public void setStatisticTime(Date statisticTime) {
        this.statisticTime = statisticTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getSpotcheckNum() {
        return spotcheckNum;
    }

    public void setSpotcheckNum(Integer spotcheckNum) {
        this.spotcheckNum = spotcheckNum;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getAnomalyNum() {
        return anomalyNum;
    }

    public void setAnomalyNum(Integer anomalyNum) {
        this.anomalyNum = anomalyNum;
    }
}
