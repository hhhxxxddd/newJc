package com.jinchi.app.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 流程任务处理记录表
 * @date 2018/12/7 12:26
 * @see com.jinchi.common.domain.DataTaskRecord 1:n
 */
@ApiModel(description = "流程任务处理记录实体")
public class TaskHandlingRecord {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("处理人") //FK NOT NULL
    private Integer handler;

    @ApiModelProperty("处理时间") //NOT NULL
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date handleTime;

    @ApiModelProperty("处理反馈") //NULL
    private String handleReply;

    @ApiModelProperty("数据任务记录Id") // FK NOT NULL
    private Integer dataTaskRecordId;

    @ApiModelProperty(value = "是否可见", allowableValues = "0或者1")
    private Integer visible;

    public Integer getHandler() {
        return handler;
    }

    public void setHandler(Integer handler) {
        this.handler = handler;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandleReply() {
        return handleReply;
    }

    public void setHandleReply(String handleReply) {
        this.handleReply = handleReply;
    }

    public Integer getDataTaskRecordId() {
        return dataTaskRecordId;
    }

    public void setDataTaskRecordId(Integer dataTaskRecordId) {
        this.dataTaskRecordId = dataTaskRecordId;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TaskHandlingRecord{" +
                "id=" + id +
                ", handler=" + handler +
                ", handleTime=" + handleTime +
                ", handleReply='" + handleReply + '\'' +
                ", dataTaskRecordId=" + dataTaskRecordId +
                ", visible=" + visible +
                '}';
    }
}
