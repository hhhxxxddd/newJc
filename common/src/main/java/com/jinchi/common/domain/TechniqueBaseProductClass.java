package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author：XudongHu
 * @className:TechniqueBaseProductClass
 * @description: 技术基本产品型号
 * @date:14:56 2018/12/27
 */
@ApiModel(description = "技术基本产品型号")
public class TechniqueBaseProductClass {
    @ApiModelProperty("主键")
    private Integer id;      //NN

    @ApiModelProperty("型号名称")
    @NotBlank(message = "型号名称不能为空")
    private String name;       //NN

    @ApiModelProperty("父型号id")
    private Integer parent;

    @ApiModelProperty("是否为子节点")
    private Integer isLeaf;      //NN

    public Integer getId() {
        return id;
    }

    public TechniqueBaseProductClass setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TechniqueBaseProductClass setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getParent() {
        return parent;
    }

    public TechniqueBaseProductClass setParent(Integer parent) {
        this.parent = parent;
        return this;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public TechniqueBaseProductClass setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
        return this;
    }

    @Override
    public String toString() {
        return "TechniqueBaseProductClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", isLeaf=" + isLeaf +
                '}';
    }
}
