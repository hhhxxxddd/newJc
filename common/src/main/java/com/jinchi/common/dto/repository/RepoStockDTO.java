package com.jinchi.common.dto.repository;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author:YuboWu
 * @description:库存管理
 * @date:14:17 2018/11/29
 */
public class RepoStockDTO {

    @ApiModelProperty("库存主键")
    private Integer id;
    @ApiModelProperty("货物名称")
    private String materialName;//货物名称
    @ApiModelProperty("货物型号")
    private Integer materialClass;//货物型号
    @ApiModelProperty("编号")
    private String serialNumber;//编号
    @ApiModelProperty("重量")
    private Integer weight;//重量

    @ApiModelProperty("实际重量")
    private Integer realWeight;

    public Integer getRealWeight() {
        return realWeight;
    }

    public RepoStockDTO setRealWeight(Integer realWeight) {
        this.realWeight = realWeight;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getMaterialClass() {
        return materialClass;
    }

    public void setMaterialClass(Integer materialClass) {
        this.materialClass = materialClass;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "RepoStockDTO{" +
                "id=" + id +
                ", materialName='" + materialName + '\'' +
                ", materialClass=" + materialClass +
                ", serialNumber='" + serialNumber + '\'' +
                ", weight=" + weight +
                '}';
    }
}