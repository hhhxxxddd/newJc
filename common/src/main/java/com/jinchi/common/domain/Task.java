package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author：XudongHu
 * @description: 待办事项表(流程管理)
 * @date:21:41 2018/11/18
 */
@ApiModel(description = "待办事项实体")
public class Task {

    @ApiModelProperty(name = "主键")
    private Integer id;

    //CommonBatchNumber 外键
    @ApiModelProperty(name = "批号")
    private Integer batchNumberId;

    //不是外键
    //NOT NULL
    @ApiModelProperty(name = "操作人")
    private Integer operator;

    //NOT NULL
    @ApiModelProperty(name = "操作描述")
    private String operationDescription = "无";


    //1. 标示为检测任务
    //2. 标示为审核任务
    //3. 标示为发布任务
    @ApiModelProperty(name = "任务类型")
    private Integer type;


    @ApiModelProperty(name = "前一个审核人的审核任务")
    private Integer previous;

    @ApiModelProperty(name = "后一个审核人的审核任务")
    private Integer next;

    public Task() {
    }

    public Task(Integer batchNumberId, Integer operator, String operationDescription, Integer type, Integer previous, Integer next) {
        this.batchNumberId = batchNumberId;
        this.operator = operator;
        this.operationDescription = operationDescription;
        this.type = type;
        this.previous = previous;
        this.next = next;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBatchNumberId() {
        return batchNumberId;
    }

    public void setBatchNumberId(Integer batchNumberId) {
        this.batchNumberId = batchNumberId;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }


    public Integer getPrevious() {
        return previous;
    }

    public void setPrevious(Integer previous) {
        this.previous = previous;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", batchNumberId=" + batchNumberId +
                ", operator=" + operator +
                ", operationDescription='" + operationDescription + '\'' +
                ", type=" + type +
                ", previous=" + previous +
                ", next=" + next +
                '}';
    }
}
