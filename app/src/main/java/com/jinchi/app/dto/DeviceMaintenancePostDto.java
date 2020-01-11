package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-08-26 15:44
 * @description:
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceMaintenancePostDto {


    private Integer userId;
    private Integer statusId;
    private String condition;
    private Integer size;
    private Integer page;
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "DeviceMaintenancePostDto{" +
                "userId='" + userId + '\'' +
                ", statusId='" + statusId + '\'' +
                ", condition='" + condition + '\'' +
                ", size='" + size + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}
