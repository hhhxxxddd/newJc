package com.jinchi.common.domain;

import com.jinchi.common.constant.BatchStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 不合格样品检测纪录
 * @date 2018/11/26 19:02
 * @see UnqualifiedTestItemResultRecord [n:1]
 * @see CommonBatchNumber
 */


@ApiModel(description = "不合格样品检测")  //Swagger描述
public class UnqualifiedTestReportRecord {
    @ApiModelProperty("主键") //swagger注释
    private Integer id;                         //PK    主键

    @ApiModelProperty("样品送检纪录ID")
    private Integer sampleDeliveringRecordId;   //NN    样品送检纪录ID

    @ApiModelProperty("进货检验报告单ID，只有原材料检测才能生成进货检验报告单，其他为NULL")
    private Integer purchaseReportRecordId;     //NULL  进货检验报告单ID，只有原材料检测才能生成进货检验报告单，其他为NULL

    @ApiModelProperty("批号ID")
    private Integer batchNumberId;              //NN    批号ID

    @ApiModelProperty("状态(DEFAULT[已保存]/待审核/审核通过/审核不通过)")
    private Integer status;                     //NN    状态(DEFAULT[已保存]/待审核/审核通过/审核不通过)

    @ApiModelProperty("判定人ID")
    private Integer judger;                     //NULL  判定人ID

    @ApiModelProperty("判定日期")
    private Date judgeDate;                     //NULL  判定日期

    @ApiModelProperty("是否合格")
    private Integer isQualified;                //NULL  是否合格

    @ApiModelProperty("(优等品/普通品/不合格品)，成品检测存在一个择优过程，其他为NULL")
    private Integer qualityLevel;               //NULL  (优等品/普通品/不合格品)，成品检测存在一个择优过程，其他为NULL

    @ApiModelProperty("(Y/N)单条样品检测纪录和标准对比判定结果")
    private Integer decision;                   //NULL  (Y/N)单条样品检测纪录和标准对比判定结果

    public UnqualifiedTestReportRecord() {
    }

    public UnqualifiedTestReportRecord(TestReportRecord testReportRecord) {
        //单条判定
        this.decision = testReportRecord.getDecision();
        //判定日期
        this.judgeDate = testReportRecord.getJudgeDate();
        //判定人
        this.judger = testReportRecord.getJudger();
        //是否合格 整体判定
        this.isQualified = testReportRecord.getIsQualified();
        //成品物料等级
        this.qualityLevel = testReportRecord.getQualityLevel();
        //进货检验id
        this.purchaseReportRecordId = testReportRecord.getPurchaseReportRecordId();
        //审核状态
        this.status = BatchStatusEnum.SAVE.status();
        //样品送检id
        this.sampleDeliveringRecordId = testReportRecord.getSampleDeliveringRecordId();
    }

    public Integer getId() {
        return id;
    }

    public UnqualifiedTestReportRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getSampleDeliveringRecordId() {
        return sampleDeliveringRecordId;
    }

    public UnqualifiedTestReportRecord setSampleDeliveringRecordId(Integer sampleDeliveringRecordId) {
        this.sampleDeliveringRecordId = sampleDeliveringRecordId;
        return this;
    }

    public Integer getPurchaseReportRecordId() {
        return purchaseReportRecordId;
    }

    public UnqualifiedTestReportRecord setPurchaseReportRecordId(Integer purchaseReportRecordId) {
        this.purchaseReportRecordId = purchaseReportRecordId;
        return this;
    }

    public Integer getBatchNumberId() {
        return batchNumberId;
    }

    public UnqualifiedTestReportRecord setBatchNumberId(Integer batchNumberId) {
        this.batchNumberId = batchNumberId;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public UnqualifiedTestReportRecord setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getJudger() {
        return judger;
    }

    public UnqualifiedTestReportRecord setJudger(Integer judger) {
        this.judger = judger;
        return this;
    }

    public Date getJudgeDate() {
        return judgeDate;
    }

    public UnqualifiedTestReportRecord setJudgeDate(Date judgeDate) {
        this.judgeDate = judgeDate;
        return this;
    }

    public Integer getIsQualified() {
        return isQualified;
    }

    public UnqualifiedTestReportRecord setIsQualified(Integer isQualified) {
        this.isQualified = isQualified;
        return this;
    }

    public Integer getQualityLevel() {
        return qualityLevel;
    }

    public UnqualifiedTestReportRecord setQualityLevel(Integer qualityLevel) {
        this.qualityLevel = qualityLevel;
        return this;
    }

    public Integer getDecision() {
        return decision;
    }

    public UnqualifiedTestReportRecord setDecision(Integer decision) {
        this.decision = decision;
        return this;
    }
}
