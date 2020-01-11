package com.jinchi.common.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 该类用来表示样品检测纪录。
 *
 * @author lllyyyggg
 * @see SampleDeliveringRecord  [1:1, SampleDeliveringRecord -> TestReportRecord]
 * @see TestItemResultRecord    [1:n]
 */

public class TestReportRecord implements Serializable {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("样品送检id")              //PK    主键
    private Integer sampleDeliveringRecordId;   //NN    样品送检纪录ID

    @ApiModelProperty("批号id")
    private Integer batchNumberId;              //NN    批号ID

    @ApiModelProperty("判定人Id")
    private Integer judger;                     //NULL  判定人ID

    @ApiModelProperty("判定日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date judgeDate;                     //NULL  判定日期

    @ApiModelProperty("是否合格")
    private Integer isQualified;                //NULL  是否合格

    @ApiModelProperty("进货检验报告单id")
    private Integer purchaseReportRecordId;     //NULL  进货检验报告单ID，只有原材料检测才能生成进货检验报告单，其他为NULL

    @ApiModelProperty("产品质量等级")
    private Integer qualityLevel;               //NULL  (优等品/普通品/不合格品)，成品检测存在一个择优过程，其他为NULL

    @ApiModelProperty("判定结果")
    private Integer decision;                   //NULL  (Y/N)单条样品检测纪录和标准对比判定结果

    @ApiModelProperty("择优人")
    private Integer ratePersonId;

    @ApiModelProperty("择优时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date rateDate;

    public Integer getId() {
        return id;
    }

    public TestReportRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getSampleDeliveringRecordId() {
        return sampleDeliveringRecordId;
    }

    public TestReportRecord setSampleDeliveringRecordId(Integer sampleDeliveringRecordId) {
        this.sampleDeliveringRecordId = sampleDeliveringRecordId;
        return this;
    }

    public Integer getBatchNumberId() {
        return batchNumberId;
    }

    public TestReportRecord setBatchNumberId(Integer batchNumberId) {
        this.batchNumberId = batchNumberId;
        return this;
    }

    public Integer getJudger() {
        return judger;
    }

    public TestReportRecord setJudger(Integer judger) {
        this.judger = judger;
        return this;
    }

    public Date getJudgeDate() {
        return judgeDate;
    }

    public TestReportRecord setJudgeDate(Date judgeDate) {
        this.judgeDate = judgeDate;
        return this;
    }

    public Integer getIsQualified() {
        return isQualified;
    }

    public TestReportRecord setIsQualified(Integer isQualified) {
        this.isQualified = isQualified;
        return this;
    }

    public Integer getPurchaseReportRecordId() {
        return purchaseReportRecordId;
    }

    public TestReportRecord setPurchaseReportRecordId(Integer purchaseReportRecordId) {
        this.purchaseReportRecordId = purchaseReportRecordId;
        return this;
    }

    public Integer getQualityLevel() {
        return qualityLevel;
    }

    public TestReportRecord setQualityLevel(Integer qualityLevel) {
        this.qualityLevel = qualityLevel;
        return this;
    }

    public Integer getDecision() {
        return decision;
    }

    public TestReportRecord setDecision(Integer decision) {
        this.decision = decision;
        return this;
    }

    public Integer getRatePersonId() {
        return ratePersonId;
    }

    public TestReportRecord setRatePersonId(Integer ratePersonId) {
        this.ratePersonId = ratePersonId;
        return this;
    }

    public Date getRateDate() {
        return rateDate;
    }

    public TestReportRecord setRateDate(Date rateDate) {
        this.rateDate = rateDate;
        return this;
    }

    @Override
    public String toString() {
        return "TestReportRecord{" +
                "id=" + id +
                ", sampleDeliveringRecordId=" + sampleDeliveringRecordId +
                ", batchNumberId=" + batchNumberId +
                ", judger=" + judger +
                ", judgeDate=" + judgeDate +
                ", isQualified=" + isQualified +
                ", purchaseReportRecordId=" + purchaseReportRecordId +
                ", qualityLevel=" + qualityLevel +
                ", decision=" + decision +
                ", ratePersonId=" + ratePersonId +
                ", rateDate=" + rateDate +
                '}';
    }
}
