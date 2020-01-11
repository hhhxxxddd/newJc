package com.jinchi.app.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author：XudongHu
 * @description: 测试项目结果表
 * @date:23:43 2018/11/18
 * @see TestReportRecord
 */

@ApiModel(description = "测试项目结果实体")
public class TestItemResultRecord {

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

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }


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

    public Integer getTestReportRecordId() {
        return testReportRecordId;
    }

    public void setTestReportRecordId(Integer testReportRecordId) {
        this.testReportRecordId = testReportRecordId;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }
}
