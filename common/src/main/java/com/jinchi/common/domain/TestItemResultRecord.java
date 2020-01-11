package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author：XudongHu
 * @description: 测试项目结果表
 * @date:23:43 2018/11/18
 * @see TestReportRecord
 */

@ApiModel(description = "测试项目结果实体")
public class TestItemResultRecord implements Serializable {

    @ApiModelProperty("主键")
    private Integer id;                 //PK    主键

    @ApiModelProperty("检测项目id")
    private Integer testItemId;         //NN    检测项目ID

    @ApiModelProperty("检测报告记录id")
    private Integer testReportRecordId; //NN    检测报告纪录ID

    @ApiModelProperty("检测结果")
    private String testResult;          //NULL  检测结果

    @ApiModelProperty("是否符合标准")
    private Integer isValid;          //NULL  是否符合标准

    @ApiModelProperty("是否通过审核")
    private Integer isAudit;          //Null 是否通过审核

    public Integer getId() {
        return id;
    }

    public TestItemResultRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getTestItemId() {
        return testItemId;
    }

    public TestItemResultRecord setTestItemId(Integer testItemId) {
        this.testItemId = testItemId;
        return this;
    }

    public Integer getTestReportRecordId() {
        return testReportRecordId;
    }

    public TestItemResultRecord setTestReportRecordId(Integer testReportRecordId) {
        this.testReportRecordId = testReportRecordId;
        return this;
    }

    public String getTestResult() {
        return testResult;
    }

    public TestItemResultRecord setTestResult(String testResult) {
        this.testResult = testResult;
        return this;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public TestItemResultRecord setIsValid(Integer isValid) {
        this.isValid = isValid;
        return this;
    }

    public Integer getIsAudit() {
        return isAudit;
    }

    public TestItemResultRecord setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
        return this;
    }

    @Override
    public String toString() {
        return "TestItemResultRecord{" +
                "id=" + id +
                ", testItemId=" + testItemId +
                ", testReportRecordId=" + testReportRecordId +
                ", testResult='" + testResult + '\'' +
                ", isValid=" + isValid +
                ", isAudit=" + isAudit +
                '}';
    }
}
