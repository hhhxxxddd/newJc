package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author：XudongHu
 * @className:TechniqueProductTestItemStandard
 * @description:
 * @date:14:57 2018/12/27
 */
@ApiModel(description = "技术成品检测项目标准")
public class TechniqueProductTestItemStandard {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("检测项目id")
    private Integer testItemId;

    @ApiModelProperty("标准值")
    private String value="无";

    @ApiModelProperty("产品型号id")
    //@See TechniqueBaseProductClass
    private Integer productStandardId;

    public Integer getId() {
        return id;
    }

    public TechniqueProductTestItemStandard setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getTestItemId() {
        return testItemId;
    }

    public TechniqueProductTestItemStandard setTestItemId(Integer testItemId) {
        this.testItemId = testItemId;
        return this;
    }

    public String getValue() {
        return value;
    }

    public TechniqueProductTestItemStandard setValue(String value) {
        this.value = value;
        return this;
    }

    public Integer getProductStandardId() {
        return productStandardId;
    }

    public TechniqueProductTestItemStandard setProductStandardId(Integer productStandardId) {
        this.productStandardId = productStandardId;
        return this;
    }

    @Override
    public String toString() {
        return "TechniqueProductTestItemStandard{" +
                "id=" + id +
                ", testItemId=" + testItemId +
                ", value='" + value + '\'' +
                ", productStandardId=" + productStandardId +
                '}';
    }
}
