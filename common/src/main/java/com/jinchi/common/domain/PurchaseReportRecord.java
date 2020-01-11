package com.jinchi.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 原料进货单记录实体
 * @date 2018/11/26 18:40
 * @see TestReportRecord [1:1, TestReportRecord -> PurchaseTestReportRecord]
 * @see CommonBatchNumber [1:1]
 */
@ApiModel(description = "原料进货单记录实体")
public class PurchaseReportRecord {
    @ApiModelProperty("主键")
    private Integer id;             //PK    主键

    @ApiModelProperty("批号ID")
    private Integer batchNumberId;  //NN    批号ID

    @ApiModelProperty("受检物料ID")
    private Integer testMaterialId; //NN    受检物料ID

    @ApiModelProperty("生产厂家ID")
    private Integer manufacturerId; //NN    生产厂家ID

    @ApiModelProperty("数量")
    private String quantity;        //NN    数量

    @ApiModelProperty("重量")
    private String weight;          //NN    重量

    @ApiModelProperty("规格")
    private String norm;            //NN    规格

    @ApiModelProperty("判定人ID")
    private Integer judger;         //NULL  判定人ID

    @ApiModelProperty("判定结果")
    private Integer judgement;       //NULL  判定结果

    @ApiModelProperty("判定日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date judgeDate;         //NULL  判定日期

    @ApiModelProperty("研发部意见人")
    private Integer devJudger;      //NULL  研发部意见人

    @ApiModelProperty("研发部意见")
    private String devJudgement;    //NULL  研发部意见

    @ApiModelProperty("研发部发布意见日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date devJudgeDate;      //NULL  研发部发布意见日期

    @ApiModelProperty("到货日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date receiveDate;

    public Integer getId() {
        return id;
    }

    public PurchaseReportRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getBatchNumberId() {
        return batchNumberId;
    }

    public PurchaseReportRecord setBatchNumberId(Integer batchNumberId) {
        this.batchNumberId = batchNumberId;
        return this;
    }

    public Integer getTestMaterialId() {
        return testMaterialId;
    }

    public PurchaseReportRecord setTestMaterialId(Integer testMaterialId) {
        this.testMaterialId = testMaterialId;
        return this;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public PurchaseReportRecord setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public PurchaseReportRecord setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public PurchaseReportRecord setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getNorm() {
        return norm;
    }

    public PurchaseReportRecord setNorm(String norm) {
        this.norm = norm;
        return this;
    }

    public Integer getJudger() {
        return judger;
    }

    public PurchaseReportRecord setJudger(Integer judger) {
        this.judger = judger;
        return this;
    }

    public Integer getJudgement() {
        return judgement;
    }

    public PurchaseReportRecord setJudgement(Integer judgement) {
        this.judgement = judgement;
        return this;
    }

    public Date getJudgeDate() {
        return judgeDate;
    }

    public PurchaseReportRecord setJudgeDate(Date judgeDate) {
        this.judgeDate = judgeDate;
        return this;
    }

    public Integer getDevJudger() {
        return devJudger;
    }

    public PurchaseReportRecord setDevJudger(Integer devJudger) {
        this.devJudger = devJudger;
        return this;
    }

    public String getDevJudgement() {
        return devJudgement;
    }

    public PurchaseReportRecord setDevJudgement(String devJudgement) {
        this.devJudgement = devJudgement;
        return this;
    }

    public Date getDevJudgeDate() {
        return devJudgeDate;
    }

    public PurchaseReportRecord setDevJudgeDate(Date devJudgeDate) {
        this.devJudgeDate = devJudgeDate;
        return this;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public PurchaseReportRecord setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
        return this;
    }

    @Override
    public String toString() {
        return "PurchaseReportRecord{" +
                "id=" + id +
                ", batchNumberId=" + batchNumberId +
                ", testMaterialId=" + testMaterialId +
                ", manufacturerId=" + manufacturerId +
                ", quantity='" + quantity + '\'' +
                ", weight='" + weight + '\'' +
                ", norm='" + norm + '\'' +
                ", judger=" + judger +
                ", judgement=" + judgement +
                ", judgeDate=" + judgeDate +
                ", devJudger=" + devJudger +
                ", devJudgement='" + devJudgement + '\'' +
                ", devJudgeDate=" + devJudgeDate +
                ", receiveDate=" + receiveDate +
                '}';
    }
}
