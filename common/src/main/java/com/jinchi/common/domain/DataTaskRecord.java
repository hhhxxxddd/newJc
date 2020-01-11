package com.jinchi.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author：XudongHu
 * @description: 审核数据源
 * @date:13:31 2018/11/19
 */
@ApiModel(description = "审核数据源")
public class DataTaskRecord {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("流程对应的批号Id")
    private Integer taskBatchNumberId;

    @ApiModelProperty("流程对应的批号Id")
    private Integer dataBatchNumberId;

    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("创建人")
    private Integer createPerson;

    public Integer getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Integer createPerson) {
        this.createPerson = createPerson;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskBatchNumberId() {
        return taskBatchNumberId;
    }

    public void setTaskBatchNumberId(Integer taskBatchNumberId) {
        this.taskBatchNumberId = taskBatchNumberId;
    }

    public Integer getDataBatchNumberId() {
        return dataBatchNumberId;
    }

    public void setDataBatchNumberId(Integer dataBatchNumberId) {
        this.dataBatchNumberId = dataBatchNumberId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DataTaskRecord{" +
                "id=" + id +
                ", taskBatchNumberId=" + taskBatchNumberId +
                ", dataBatchNumberId=" + dataBatchNumberId +
                ", createTime=" + createTime +
                ", createPerson='" + createPerson + '\'' +
                '}';
    }
}
