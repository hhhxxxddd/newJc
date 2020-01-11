package com.jinchi.common.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 出库申请映射表
 *
 * @author huxudong
 * @see com.jinchi.common.domain.CommonBatchNumber [1:n]
 */
@ApiModel(description = "出库记录表")
public class RepoOutApply {
    @ApiModelProperty("主键")
    private Integer id;             //PK

    @ApiModelProperty("批号id")
    private Integer batchNumberId;  //NN 批号ID

    @ApiModelProperty("编号id")
    private Integer serialNumberId; //NN 序号ID

    @ApiModelProperty("重量")
    private Integer weight;         //NN 重量

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

    public Integer getSerialNumberId() {
        return serialNumberId;
    }

    public void setSerialNumberId(Integer serialNumberId) {
        this.serialNumberId = serialNumberId;
    }


    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "RepoOutApply{" +
                "id=" + id +
                ", batchNumberId=" + batchNumberId +
                ", serialNumberId=" + serialNumberId +
                ", weight=" + weight +
                '}';
    }
}

