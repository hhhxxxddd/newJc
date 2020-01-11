package com.jinchi.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author：XudongHu
 * @description: 审核任务 操作人
 * @date:15:58 2018/11/19
 */
@ApiModel(description = "流程详情DTO")
public class TaskDTO implements Serializable {
    @ApiModelProperty("操作人Id")
    private Integer userId;

    @ApiModelProperty("操作人名称")
    private String personName;

    @ApiModelProperty("操作人指责")
    private String responsibility;

    @ApiModelProperty("任务类型")
    private Integer taskType;

    //外键 taskHandlingRecord
    @ApiModelProperty("是否可见")
    private Integer visible;


    public Integer getUserId() {
        return userId;
    }

    public TaskDTO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getPersonName() {
        return personName;
    }

    public TaskDTO setPersonName(String personName) {
        this.personName = personName;
        return this;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public TaskDTO setResponsibility(String responsibility) {
        this.responsibility = responsibility;
        return this;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public TaskDTO setTaskType(Integer taskType) {
        this.taskType = taskType;
        return this;
    }

    public Integer getVisible() {
        return visible;
    }

    public TaskDTO setVisible(Integer visible) {
        this.visible = visible;
        return this;
    }
}
