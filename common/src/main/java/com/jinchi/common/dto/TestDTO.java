package com.jinchi.common.dto;

import com.jinchi.common.domain.TestItemResultRecord;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author:YuboWu
 * @description:
 * @date:14:40 2018/12/19
 */
public class TestDTO implements Serializable {

    @ApiModelProperty("检测结果")
    private TestItemResultRecord testItemResultRecord;
    @ApiModelProperty("项目名称")
    private String name;
    @ApiModelProperty("单位")
    private String unit;
    @ApiModelProperty("标准值")
    private String value;
    @ApiModelProperty("审核结果")
    private Integer auditResult;

    public TestItemResultRecord getTestItemResultRecord() {
        return testItemResultRecord;
    }

    public TestDTO setTestItemResultRecord(TestItemResultRecord testItemResultRecord) {
        this.testItemResultRecord = testItemResultRecord;
        return this;
    }

    public String getName() {
        return name;
    }

    public TestDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public TestDTO setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getValue() {
        return value;
    }

    public TestDTO setValue(String value) {
        this.value = value;
        return this;
    }

    public Integer getAuditResult() {
        return auditResult;
    }

    public TestDTO setAuditResult(Integer auditResult) {
        this.auditResult = auditResult;
        return this;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
                "testItemResultRecord=" + testItemResultRecord +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", value='" + value + '\'' +
                ", auditResult=" + auditResult +
                '}';
    }
}
