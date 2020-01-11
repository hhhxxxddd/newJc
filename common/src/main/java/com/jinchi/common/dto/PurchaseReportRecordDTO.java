package com.jinchi.common.dto;

import com.jinchi.common.domain.PurchaseReportRecord;
import com.jinchi.common.domain.TechniqueBaseRawMaterial;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 原材料进货单
 * @date 2018/12/3 12:39
 */
public class PurchaseReportRecordDTO {

    @ApiModelProperty("进货检验记录")
    private PurchaseReportRecord purchaseReportRecord;
    @ApiModelProperty("原材料名称")
    private TechniqueBaseRawMaterial techniqueBaseRawMaterial;

    @ApiModelProperty("公共批号")
    private CommonBatchNumberDTO commonBatchNumberDTO;

    @ApiModelProperty("原材料检测记录")
    private TestReportRecordDTO testReportRecordDTO;

    @ApiModelProperty("材料名称")
    private String materialName;
    @ApiModelProperty("生产厂家")
    private String manufacture;
    @ApiModelProperty("到货日期")
    private Date receiveDate;

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public TechniqueBaseRawMaterial getTechniqueBaseRawMaterial() {
        return techniqueBaseRawMaterial;
    }

    public void setTechniqueBaseRawMaterial(TechniqueBaseRawMaterial techniqueBaseRawMaterial) {
        this.techniqueBaseRawMaterial = techniqueBaseRawMaterial;
    }

    @ApiModelProperty("原材料检测记录列表")
    private List<TestReportRecordDTO> testReportRecordDTOList;

    public PurchaseReportRecord getPurchaseReportRecord() {
        return purchaseReportRecord;
    }

    public void setPurchaseReportRecord(PurchaseReportRecord purchaseReportRecord) {
        this.purchaseReportRecord = purchaseReportRecord;
    }


    public CommonBatchNumberDTO getCommonBatchNumberDTO() {
        return commonBatchNumberDTO;
    }

    public void setCommonBatchNumberDTO(CommonBatchNumberDTO commonBatchNumberDTO) {
        this.commonBatchNumberDTO = commonBatchNumberDTO;
    }

    public TestReportRecordDTO getTestReportRecordDTO() {
        return testReportRecordDTO;
    }

    public void setTestReportRecordDTO(TestReportRecordDTO testReportRecordDTO) {
        this.testReportRecordDTO = testReportRecordDTO;
    }

    public List<TestReportRecordDTO> getTestReportRecordDTOList() {
        return testReportRecordDTOList;
    }

    public void setTestReportRecordDTOList(List<TestReportRecordDTO> testReportRecordDTOList) {
        this.testReportRecordDTOList = testReportRecordDTOList;
    }
}
