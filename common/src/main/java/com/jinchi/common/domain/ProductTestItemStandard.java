package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 产品检测项目标准
 * @date 2018/12/17 17:31
 */
@ApiModel("产品检测项目标准")
public class ProductTestItemStandard {
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("检测项目Id")
    private Integer testItemId;
    @ApiModelProperty("数值")
    private String value;
    @ApiModelProperty("产品标准ID")
    private Integer productStandardId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestItemId() {
        return testItemId;
    }

    public void setTestItemId(Integer testItemId) {
        this.testItemId = testItemId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getProductStandardId() {
        return productStandardId;
    }

    public void setProductStandardId(Integer productStandardId) {
        this.productStandardId = productStandardId;
    }
}
