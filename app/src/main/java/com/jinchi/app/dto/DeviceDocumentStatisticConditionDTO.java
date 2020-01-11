package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-12 10:35
 * @description:
 **/

public class DeviceDocumentStatisticConditionDTO {
    private Integer deptId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date statisticalTime;

    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    private Date spotcheckStatisticalTime;

    public Integer getDeptId() {
        return deptId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Date getStatisticalTime() {
        return statisticalTime;
    }

    public void setStatisticalTime(Date statisticalTime) {
        this.statisticalTime = statisticalTime;
    }

    public Date getSpotcheckStatisticalTime() {
        return spotcheckStatisticalTime;
    }

    public void setSpotcheckStatisticalTime(Date spotcheckStatisticalTime) {
        this.spotcheckStatisticalTime = spotcheckStatisticalTime;
    }
}
