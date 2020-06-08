package com.jinchi.common.dto.quality.purchase;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author：XudongHu
 * @className:PurchaseRawDTO
 * @description:
 * @date:14:00 2018/12/29
 */
public class PurchaseRawDTO {

    @ApiModelProperty("批号id")
    private Integer batchNumberId;

    @ApiModelProperty("送检日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deliveringDate;

    @ApiModelProperty("送样人名称")
    private String deliverName;

    @ApiModelProperty("送样工厂名称")
    private String manufacturerName;

    @ApiModelProperty("编号")
    private String serialNumber;

    @ApiModelProperty("检测项目")
    private String testItemString;

    @ApiModelProperty("异常备注")
    private String exceptionHandle;

    @ApiModelProperty("是否生成")
    private Integer isGenerate;

    @ApiModelProperty("状态")
    private Integer status;

    public Integer getBatchNumberId() {
        return batchNumberId;
    }

    public PurchaseRawDTO setBatchNumberId(Integer batchNumberId) {
        this.batchNumberId = batchNumberId;
        return this;
    }

    public Date getDeliveringDate() {
        return deliveringDate;
    }

    public PurchaseRawDTO setDeliveringDate(Date deliveringDate) {
        this.deliveringDate = deliveringDate;
        return this;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public PurchaseRawDTO setDeliverName(String deliverName) {
        this.deliverName = deliverName;
        return this;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public PurchaseRawDTO setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
        return this;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public PurchaseRawDTO setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public String getTestItemString() {
        return testItemString;
    }

    public PurchaseRawDTO setTestItemString(String testItemString) {
        this.testItemString = testItemString;
        return this;
    }

    public String getExceptionHandle() {
        return exceptionHandle;
    }

    public PurchaseRawDTO setExceptionHandle(String exceptionHandle) {
        this.exceptionHandle = exceptionHandle;
        return this;
    }

    public Integer getIsGenerate() {
        return isGenerate;
    }

    public PurchaseRawDTO setIsGenerate(Integer isGenerate) {
        this.isGenerate = isGenerate;
        return this;
    }

    @Override
    public String toString() {
        return "PurchaseRawDTO{" +
                "batchNumberId=" + batchNumberId +
                ", deliveringDate=" + deliveringDate +
                ", deliverName='" + deliverName + '\'' +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", testItemString='" + testItemString + '\'' +
                ", exceptionHandle='" + exceptionHandle + '\'' +
                ", isGenerate=" + isGenerate +
                ", status=" + status +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
