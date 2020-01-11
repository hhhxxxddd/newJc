package com.jinchi.common.dto.repository;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author：XudongHu
 * @className:RepoInRecordDTO
 * @description: 入库DTO
 * @date:15:06 2018/11/29
 */
public class RepoOutHeadDTO implements Serializable {
    @ApiModelProperty("审批id 即BatchNumberId")
    private Integer applicationFormId;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @ApiModelProperty("操作人")
    private Integer createdPersonId;
    private String createdPerson;

    @ApiModelProperty(name = "出库单号")
    private String stockOutRecordHeadCode;

    @ApiModelProperty(name = "产线id")
    private Integer productionLineId;
    private String productionLine;

    @ApiModelProperty(name = "出库点id")
    private Integer endPositionId;
    private String endPosition;

    public Integer getApplicationFormId() {
        return applicationFormId;
    }

    public RepoOutHeadDTO setApplicationFormId(Integer applicationFormId) {
        this.applicationFormId = applicationFormId;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public RepoOutHeadDTO setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public Integer getCreatedPersonId() {
        return createdPersonId;
    }

    public RepoOutHeadDTO setCreatedPersonId(Integer createdPersonId) {
        this.createdPersonId = createdPersonId;
        return this;
    }

    public String getCreatedPerson() {
        return createdPerson;
    }

    public RepoOutHeadDTO setCreatedPerson(String createdPerson) {
        this.createdPerson = createdPerson;
        return this;
    }

    public String getStockOutRecordHeadCode() {
        return stockOutRecordHeadCode;
    }

    public RepoOutHeadDTO setStockOutRecordHeadCode(String stockOutRecordHeadCode) {
        this.stockOutRecordHeadCode = stockOutRecordHeadCode;
        return this;
    }

    public Integer getProductionLineId() {
        return productionLineId;
    }

    public RepoOutHeadDTO setProductionLineId(Integer productionLineId) {
        this.productionLineId = productionLineId;
        return this;
    }

    public String getProductionLine() {
        return productionLine;
    }

    public RepoOutHeadDTO setProductionLine(String productionLine) {
        this.productionLine = productionLine;
        return this;
    }

    public Integer getEndPositionId() {
        return endPositionId;
    }

    public RepoOutHeadDTO setEndPositionId(Integer endPositionId) {
        this.endPositionId = endPositionId;
        return this;
    }

    public String getEndPosition() {
        return endPosition;
    }

    public RepoOutHeadDTO setEndPosition(String endPosition) {
        this.endPosition = endPosition;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RepoOutHeadDTO)) return false;

        RepoOutHeadDTO that = (RepoOutHeadDTO) o;

        if (getApplicationFormId() != null ? !getApplicationFormId().equals(that.getApplicationFormId()) : that.getApplicationFormId() != null)
            return false;
        if (getCreatedTime() != null ? !getCreatedTime().equals(that.getCreatedTime()) : that.getCreatedTime() != null)
            return false;
        if (getCreatedPersonId() != null ? !getCreatedPersonId().equals(that.getCreatedPersonId()) : that.getCreatedPersonId() != null)
            return false;
        if (getCreatedPerson() != null ? !getCreatedPerson().equals(that.getCreatedPerson()) : that.getCreatedPerson() != null)
            return false;
        if (getStockOutRecordHeadCode() != null ? !getStockOutRecordHeadCode().equals(that.getStockOutRecordHeadCode()) : that.getStockOutRecordHeadCode() != null)
            return false;
        if (getProductionLineId() != null ? !getProductionLineId().equals(that.getProductionLineId()) : that.getProductionLineId() != null)
            return false;
        if (getProductionLine() != null ? !getProductionLine().equals(that.getProductionLine()) : that.getProductionLine() != null)
            return false;
        if (getEndPositionId() != null ? !getEndPositionId().equals(that.getEndPositionId()) : that.getEndPositionId() != null)
            return false;
        return getEndPosition() != null ? getEndPosition().equals(that.getEndPosition()) : that.getEndPosition() == null;
    }

    @Override
    public int hashCode() {
        int result = getApplicationFormId() != null ? getApplicationFormId().hashCode() : 0;
        result = 31 * result + (getCreatedTime() != null ? getCreatedTime().hashCode() : 0);
        result = 31 * result + (getCreatedPersonId() != null ? getCreatedPersonId().hashCode() : 0);
        result = 31 * result + (getCreatedPerson() != null ? getCreatedPerson().hashCode() : 0);
        result = 31 * result + (getStockOutRecordHeadCode() != null ? getStockOutRecordHeadCode().hashCode() : 0);
        result = 31 * result + (getProductionLineId() != null ? getProductionLineId().hashCode() : 0);
        result = 31 * result + (getProductionLine() != null ? getProductionLine().hashCode() : 0);
        result = 31 * result + (getEndPositionId() != null ? getEndPositionId().hashCode() : 0);
        result = 31 * result + (getEndPosition() != null ? getEndPosition().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RepoOutHeadDTO{" +
                "applicationFormId=" + applicationFormId +
                ", createdTime=" + createdTime +
                ", createdPersonId=" + createdPersonId +
                ", createdPerson='" + createdPerson + '\'' +
                ", stockOutRecordHeadCode='" + stockOutRecordHeadCode + '\'' +
                ", productionLineId=" + productionLineId +
                ", productionLine='" + productionLine + '\'' +
                ", endPositionId=" + endPositionId +
                ", endPosition='" + endPosition + '\'' +
                '}';
    }
}