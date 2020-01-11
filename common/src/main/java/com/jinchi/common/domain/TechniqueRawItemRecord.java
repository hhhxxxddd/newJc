package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author：XudongHu
 * @className:TechniqueRawItemRecord
 * @description: 原材料主成分对应表
 * @date:17:10 2019/1/7
 */
@ApiModel(description = "原材料主成分对应表")
public class TechniqueRawItemRecord {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("技术原材料主键")
    private Integer rawMaterialId;

    @ApiModelProperty("检测项目主键")
    private Integer testItemId;

    public Integer getId() {
        return id;
    }

    public TechniqueRawItemRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getRawMaterialId() {
        return rawMaterialId;
    }

    public TechniqueRawItemRecord setRawMaterialId(Integer rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
        return this;
    }

    public Integer getTestItemId() {
        return testItemId;
    }

    public TechniqueRawItemRecord setTestItemId(Integer testItemId) {
        this.testItemId = testItemId;
        return this;
    }

    @Override
    public String toString() {
        return "TechniqueRawItemRecord{" +
                "id=" + id +
                ", rawMaterialId=" + rawMaterialId +
                ", testItemId=" + testItemId +
                '}';
    }
}
