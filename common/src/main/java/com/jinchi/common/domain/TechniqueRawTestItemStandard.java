package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author：XudongHu
 * @className:TechniqueRawTestItemStandard
 * @description:
 * @date:14:58 2018/12/27
 */
@ApiModel(description = "技术原材料项目检测标准")
public class TechniqueRawTestItemStandard {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("检测项目")
    private Integer testItemId;

    @ApiModelProperty("标准值")
    private String value;

    @ApiModelProperty("原材料标准id")
    //@See TechniqueRawStandardRecord
    private Integer rawStandardRecordId;

    public Integer getId() {
        return id;
    }

    public TechniqueRawTestItemStandard setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getTestItemId() {
        return testItemId;
    }

    public TechniqueRawTestItemStandard setTestItemId(Integer testItemId) {
        this.testItemId = testItemId;
        return this;
    }

    public String getValue() {
        return value;
    }

    public TechniqueRawTestItemStandard setValue(String value) {
        this.value = value;
        return this;
    }

    public Integer getRawStandardRecordId() {
        return rawStandardRecordId;
    }

    public TechniqueRawTestItemStandard setRawStandardRecordId(Integer rawStandardRecordId) {
        this.rawStandardRecordId = rawStandardRecordId;
        return this;
    }

    @Override
    public String toString() {
        return "TechniqueRawTestItemStandard{" +
                "id=" + id +
                ", testItemId=" + testItemId +
                ", value='" + value + '\'' +
                ", rawStandardRecordId=" + rawStandardRecordId +
                '}';
    }
}
