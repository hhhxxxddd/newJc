package com.jinchi.common.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 原材料检测项目标准
 * @date 2018/12/16 10:58
 */
public class RawTestItemStandard {
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("检测项目Id")
    private Integer testItemId;
    @ApiModelProperty("数值")
    private String value;
    @ApiModelProperty("原材料标准记录")
    private Integer rawStandardRecordId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestItemID() {
        return testItemId;
    }

    public void setTestItemID(Integer testItemId) {
        this.testItemId = testItemId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getRawStandardRecordId() {
        return rawStandardRecordId;
    }

    public void setRawStandardRecordId(Integer rawStandardRecordId) {
        this.rawStandardRecordId = rawStandardRecordId;
    }
}
