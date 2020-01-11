package com.jinchi.common.dto;


import com.jinchi.common.domain.CommonBatchNumber;
import com.jinchi.common.domain.SampleDeliveringRecord;
import com.jinchi.common.domain.TestReportRecord;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author：YuboWu
 * @description: 原材料检验
 */
public class RawTestReportDTO implements Serializable {
    @ApiModelProperty("样品送检记录")
    private SampleDeliveringRecord sampleDeliveringRecord;

    @ApiModelProperty("检测结果审批")
    private TestReportRecord testReportRecord;

    @ApiModelProperty("批号表")
    private CommonBatchNumber commonBatchNumber;

    @ApiModelProperty("检测结果")
    private List<TestDTO> testDTOS;

    @ApiModelProperty("送样人")
    private String deliverer;

    @ApiModelProperty("检验人")
    private String tester;

    @ApiModelProperty("原材料名称")
    private String materialName;

    @ApiModelProperty("送样工厂")
    private String deliveryFactoryName;

    @ApiModelProperty("项目名称")
    private String testItemString;

    @ApiModelProperty("编号")
    private String serialNumber;

    @ApiModelProperty("是否完全通过")
    private Integer isFullAudit;

    @ApiModelProperty("批号")
    private String batch;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Integer getIsFullAudit() {
        return isFullAudit;
    }

    public RawTestReportDTO setIsFullAudit(Integer isFullAudit) {
        this.isFullAudit = isFullAudit;
        return this;
    }

    public SampleDeliveringRecord getSampleDeliveringRecord() {
        return sampleDeliveringRecord;
    }

    public RawTestReportDTO setSampleDeliveringRecord(SampleDeliveringRecord sampleDeliveringRecord) {
        this.sampleDeliveringRecord = sampleDeliveringRecord;
        return this;
    }

    public TestReportRecord getTestReportRecord() {
        return testReportRecord;
    }

    public RawTestReportDTO setTestReportRecord(TestReportRecord testReportRecord) {
        this.testReportRecord = testReportRecord;
        return this;
    }

    public CommonBatchNumber getCommonBatchNumber() {
        return commonBatchNumber;
    }

    public RawTestReportDTO setCommonBatchNumber(CommonBatchNumber commonBatchNumber) {
        this.commonBatchNumber = commonBatchNumber;
        return this;
    }

    public List<TestDTO> getTestDTOS() {
        return testDTOS;
    }

    public RawTestReportDTO setTestDTOS(List<TestDTO> testDTOS) {
        this.testDTOS = testDTOS;
        return this;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public RawTestReportDTO setDeliverer(String deliverer) {
        this.deliverer = deliverer;
        return this;
    }

    public String getTester() {
        return tester;
    }

    public RawTestReportDTO setTester(String tester) {
        this.tester = tester;
        return this;
    }

    public String getMaterialName() {
        return materialName;
    }

    public RawTestReportDTO setMaterialName(String materialName) {
        this.materialName = materialName;
        return this;
    }

    public String getDeliveryFactoryName() {
        return deliveryFactoryName;
    }

    public RawTestReportDTO setDeliveryFactoryName(String deliveryFactoryName) {
        this.deliveryFactoryName = deliveryFactoryName;
        return this;
    }

    public String getTestItemString() {
        return testItemString;
    }

    public RawTestReportDTO setTestItemString(String testItemString) {
        this.testItemString = testItemString;
        return this;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public RawTestReportDTO setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    @Override
    public String toString() {
        return "RawTestReportDTO{" +
                "sampleDeliveringRecord=" + sampleDeliveringRecord +
                ", testReportRecord=" + testReportRecord +
                ", commonBatchNumber=" + commonBatchNumber +
                ", testDTOS=" + testDTOS +
                ", deliverer='" + deliverer + '\'' +
                ", tester='" + tester + '\'' +
                ", materialName='" + materialName + '\'' +
                ", deliveryFactoryName='" + deliveryFactoryName + '\'' +
                ", testItemString='" + testItemString + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}