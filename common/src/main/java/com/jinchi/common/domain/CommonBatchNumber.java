package com.jinchi.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author：XudongHu
 * @description: 公共批号表
 * @date:14:55 2018/11/18
 */
@ApiModel(description = "公共批号实体")
public class CommonBatchNumber implements Serializable {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("批号")
    private String batchNumber;

    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("创建人")
    @NotNull(message = "创建人Id不能为空")
    private Integer createPersonId;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("数据类型")
    private Integer dataType;

    @ApiModelProperty("是否紧急")
    private Integer isUrgent;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("制程检测备注")
    //制程检测专用备注,其余为空
    private String memo;

    @ApiModelProperty("是否已发布")
    private Integer isPublished;

    @ApiModelProperty("审核通过时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date passTime;

    public Integer getId() {
        return id;
    }

    public CommonBatchNumber setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public CommonBatchNumber setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public CommonBatchNumber setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getCreatePersonId() {
        return createPersonId;
    }

    public CommonBatchNumber setCreatePersonId(Integer createPersonId) {
        this.createPersonId = createPersonId;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public CommonBatchNumber setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getDataType() {
        return dataType;
    }

    public CommonBatchNumber setDataType(Integer dataType) {
        this.dataType = dataType;
        return this;
    }

    public Integer getIsUrgent() {
        return isUrgent;
    }

    public CommonBatchNumber setIsUrgent(Integer isUrgent) {
        this.isUrgent = isUrgent;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CommonBatchNumber setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public CommonBatchNumber setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public Integer getIsPublished() {
        return isPublished;
    }

    public CommonBatchNumber setIsPublished(Integer isPublished) {
        this.isPublished = isPublished;
        return this;
    }

    public Date getPassTime() {
        return passTime;
    }

    public CommonBatchNumber setPassTime(Date passTime) {
        this.passTime = passTime;
        return this;
    }

    @Override
    public String toString() {
        //language=JSON
        String a = "";
        return "CommonBatchNumber{" +
                "id=" + id +
                ", batchNumber='" + batchNumber + '\'' +
                ", createTime=" + createTime +
                ", createPersonId=" + createPersonId +
                ", status=" + status +
                ", dataType=" + dataType +
                ", isUrgent=" + isUrgent +
                ", description='" + description + '\'' +
                ", memo='" + memo + '\'' +
                ", isPublished=" + isPublished +
                ", passTime=" + passTime +
                '}';
    }
}
