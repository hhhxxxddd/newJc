package com.jinchi.common.domain;

/**
 * 库存映射表
 *
 * @author lllyyyggg
 */
public class RepoStock {

    private Integer id;             //PK

    private Integer materialType;   //NN    物料类型(原材料/中间品/成品)

    private Integer serialNumberId; //NN    批号

    private Integer weight;         //NN    库存重量

    public Integer getId() {
        return id;
    }

    public RepoStock setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getMaterialType() {
        return materialType;
    }

    public RepoStock setMaterialType(Integer materialType) {
        this.materialType = materialType;
        return this;
    }

    public Integer getSerialNumberId() {
        return serialNumberId;
    }

    public RepoStock setSerialNumberId(Integer serialNumberId) {
        this.serialNumberId = serialNumberId;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public RepoStock setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }
}
