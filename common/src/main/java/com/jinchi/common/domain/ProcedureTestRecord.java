package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author：XudongHu
 * @description: 制程检测记录表
 * @date:22:31 2018/11/18
 */
@ApiModel(description = "制程检测记录实体")
public class ProcedureTestRecord {

    //全为非空
    @ApiModelProperty(name = "主键")
    private Integer id;

    @ApiModelProperty(name = "流程管理批号")
    //CommonBatchNumber 外键
    private Integer batchNumberId;

    @ApiModelProperty(name = "送样工厂Id")
    @NotNull(message = "送样工厂id不能为空")
    //ProductLine   外键
    private Integer deliveryFactoryId;

    @ApiModelProperty(name = "工序Id")
    @NotNull(message = "工序id不能为空")
    //Procedure 外键
    private Integer procedureId;

    @ApiModelProperty(name = "受检物料Id")
    @NotNull(message = "受检物料id不能为空")
    //TestItem 外键
    private Integer serialNumberId;

    @ApiModelProperty(name = "采样人Id")
    @NotNull(message = "采样人id不能为空")
    //AuthUser 外键
    private Integer sampler;

    @ApiModelProperty(name = "检测人Id")
    @NotNull(message = "检测人id不能为空")
    //AuthUser 外键
    private Integer tester;

    @ApiModelProperty(name = "样品检测点")
    @NotBlank(message = "样品监测点不能为空")
    private String samplePointName;

    @ApiModelProperty(name = "检测频率")
    @NotBlank(message = "检测频率不能为空")
    private String testFrequency;

    @ApiModelProperty(name = "备注")
    private String comment;

    @ApiModelProperty(name = "是否为迭代数据")
    private Integer isIteration;

    public Integer getIsIteration() {
        return isIteration;
    }

    public void setIsIteration(Integer isIteration) {
        this.isIteration = isIteration;
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


    public Integer getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Integer procedureId) {
        this.procedureId = procedureId;
    }


    public Integer getSampler() {
        return sampler;
    }

    public void setSampler(Integer sampler) {
        this.sampler = sampler;
    }

    public Integer getTester() {
        return tester;
    }

    public void setTester(Integer tester) {
        this.tester = tester;
    }

    public String getSamplePointName() {
        return samplePointName;
    }

    public void setSamplePointName(String samplePointName) {
        this.samplePointName = samplePointName;
    }


    public String getTestFrequency() {
        return testFrequency;
    }

    public void setTestFrequency(String testFrequency) {
        this.testFrequency = testFrequency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getDeliveryFactoryId() {
        return deliveryFactoryId;
    }

    public void setDeliveryFactoryId(Integer deliveryFactoryId) {
        this.deliveryFactoryId = deliveryFactoryId;
    }

    public Integer getSerialNumberId() {
        return serialNumberId;
    }

    public void setSerialNumberId(Integer serialNumberId) {
        this.serialNumberId = serialNumberId;
    }

    @Override
    public String toString() {
        return "ProcedureTestRecord{" +
                "id=" + id +
                ", batchNumberId=" + batchNumberId +
                ", deliveryFactoryId=" + deliveryFactoryId +
                ", procedureId=" + procedureId +
                ", serialNumberId=" + serialNumberId +
                ", sampler=" + sampler +
                ", tester=" + tester +
                ", samplePointName='" + samplePointName + '\'' +
                ", testFrequency='" + testFrequency + '\'' +
                ", comment='" + comment + '\'' +
                ", isIteration=" + isIteration +
                '}';
    }
}
