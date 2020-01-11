package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-03 13:21
 * @description: 设备点检模块 点检操作员 点检计划 入参dto
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceSpotcheckPlansPostDTO {
    private Integer userId;

    private String condition;

    private Integer size;

    private Integer page;


    public Integer getUserId() {
        return userId;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getSize() {
        return size;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setPage(Integer page) {
        this.page = page;
    }
}
