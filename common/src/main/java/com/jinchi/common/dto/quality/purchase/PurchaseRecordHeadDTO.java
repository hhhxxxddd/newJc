package com.jinchi.common.dto.quality.purchase;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinchi.common.domain.PurchaseReportRecord;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author：XudongHu
 * @className:PurchaseRecordDTO
 * @description: 进货检验报告单
 * @date:10:42 2018/12/29
 */
public class PurchaseRecordHeadDTO {

    @ApiModelProperty("材料名称")
    private String materialName;

    @ApiModelProperty("生产厂家")
    private String manufactureName;

    @ApiModelProperty("到货日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date receiveDate;

    @ApiModelProperty("检验人名称")
    private String tester;

    @ApiModelProperty("进货报告单实体")
    @NotNull(message = "进货报告单详情不能为空")
    private PurchaseReportRecord purchaseReportRecord;

    @ApiModelProperty("检测项目值")
    @NotEmpty(message = "检测项目判断不能为空")
    private List<ValidTestRecordDTO> validTestRecords;

    @ApiModelProperty("原材料标准")
    private List<String> standards;

    public String getMaterialName() {
        return materialName;
    }

    public PurchaseRecordHeadDTO setMaterialName(String materialName) {
        this.materialName = materialName;
        return this;
    }

    public String getManufactureName() {
        return manufactureName;
    }

    public PurchaseRecordHeadDTO setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
        return this;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public PurchaseRecordHeadDTO setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
        return this;
    }

    public String getTester() {
        return tester;
    }

    public PurchaseRecordHeadDTO setTester(String tester) {
        this.tester = tester;
        return this;
    }

    public PurchaseReportRecord getPurchaseReportRecord() {
        return purchaseReportRecord;
    }

    public PurchaseRecordHeadDTO setPurchaseReportRecord(PurchaseReportRecord purchaseReportRecord) {
        this.purchaseReportRecord = purchaseReportRecord;
        return this;
    }

    public List<ValidTestRecordDTO> getValidTestRecords() {
        return validTestRecords;
    }

    public PurchaseRecordHeadDTO setValidTestRecords(List<ValidTestRecordDTO> validTestRecords) {
        this.validTestRecords = validTestRecords;
        return this;
    }

    public List<String> getStandards() {
        return standards;
    }

    public PurchaseRecordHeadDTO setStandards(List<String> standards) {
        this.standards = standards;
        return this;
    }

    @Override
    public String toString() {
        return "PurchaseRecordHeadDTO{" +
                "materialName='" + materialName + '\'' +
                ", manufactureName='" + manufactureName + '\'' +
                ", receiveDate=" + receiveDate +
                ", tester='" + tester + '\'' +
                ", purchaseReportRecord=" + purchaseReportRecord +
                ", validTestRecords=" + validTestRecords +
                ", standards='" + standards + '\'' +
                '}';
    }
}
