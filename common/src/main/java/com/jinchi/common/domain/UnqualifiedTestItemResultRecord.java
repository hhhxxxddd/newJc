package com.jinchi.common.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 不合格检测项目结果纪录
 * @date 2018/11/26 18:55
 * @see UnqualifiedTestReportRecord
 */
public class UnqualifiedTestItemResultRecord {
    @ApiModelProperty("主键")
    private Integer id;                             //PK
    @ApiModelProperty("检测项目ID")
    private Integer testItemId;                     //NN 检测项目ID
    @ApiModelProperty("不合格样品检测ID")
    private Integer unqualifiedTestReportRecordId;  //NN 不合格样品检测ID
    @ApiModelProperty("检测结果")
    private String testResult;                      //NN 检测结果
    @ApiModelProperty("是否符合标准")
    private Integer isValid;                        //NN 是否符合标准

    public UnqualifiedTestItemResultRecord() {
    }

    public UnqualifiedTestItemResultRecord(TestItemResultRecord testItemResultRecord) {
        this.isValid = testItemResultRecord.getIsValid();
        this.testItemId = testItemResultRecord.getTestItemId();
        this.testResult = testItemResultRecord.getTestResult();
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

    public Integer getUnqualifiedTestReportRecordId() {
        return unqualifiedTestReportRecordId;
    }

    public void setUnqualifiedTestReportRecordId(Integer unqualifiedTestReportRecordId) {
        this.unqualifiedTestReportRecordId = unqualifiedTestReportRecordId;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "UnqualifiedTestItemResultRecord{" +
                "id=" + id +
                ", testItemId=" + testItemId +
                ", unqualifiedTestReportRecordId=" + unqualifiedTestReportRecordId +
                ", testResult='" + testResult + '\'' +
                ", isValid=" + isValid +
                '}';
    }
}
