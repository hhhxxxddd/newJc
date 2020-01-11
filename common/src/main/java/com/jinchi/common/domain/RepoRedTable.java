package com.jinchi.common.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 红单映射表
 * @date 2018/11/27 15:27
 */
@ApiModel(description = "红单映射表")
public class RepoRedTable {
    @ApiModelProperty("主键")
    private Integer id;             //PK
    @ApiModelProperty("批号ID")
    private Integer batchNumberId;  //NN    批号ID
    @ApiModelProperty("序号ID")
    private Integer serialNumberId; //NN    序号ID
    @ApiModelProperty("损失重量")
    private Integer weightLoss;           //NN    重量损失
    @ApiModelProperty("意外损失文字说明")
    private String note;            //NULL  意外损失文字说明

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

    public Integer getWeightLoss() {
        return weightLoss;
    }

    public void setWeightLoss(Integer weightLoss) {
        this.weightLoss = weightLoss;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
