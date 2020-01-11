package com.jinchi.common.dto.quality.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinchi.common.domain.RepoBaseSerialNumber;
import com.jinchi.common.domain.TestReportRecord;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author：XudongHu
 * @className:productReportDTO
 * @description: 成品检测DTO
 * @date:16:06 2019/1/8
 */
public class ProductReportHeadDTO {
    @ApiModelProperty("批号id")
    @NotNull(message = "更新需要传入批号id")
    private Integer batchNumberId;

    @ApiModelProperty("送样日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliveringDate;

    @ApiModelProperty("送样人")
    private String deliver;

    @ApiModelProperty("检验人")
    private String tester;

    @ApiModelProperty("送检工厂")
    private String deliverFactory;

    @ApiModelProperty("编号")
    private RepoBaseSerialNumber repoBaseSerialNumber;

    @ApiModelProperty("检测项目")
    private String testItemString;

    @ApiModelProperty("异常备注")
    private String exception;

    @ApiModelProperty("发布状态")
    private Integer isPublished;

    @ApiModelProperty("申请状态")
    private Integer status;

    @ApiModelProperty("详情")
    private List<TestResultDTO> testResultDTOList;

    @ApiModelProperty("更多信息")
    private TestReportRecord testReportRecord;

    public Integer getBatchNumberId() {
        return batchNumberId;
    }

    public ProductReportHeadDTO setBatchNumberId(Integer batchNumberId) {
        this.batchNumberId = batchNumberId;
        return this;
    }

    public Date getDeliveringDate() {
        return deliveringDate;
    }

    public ProductReportHeadDTO setDeliveringDate(Date deliveringDate) {
        this.deliveringDate = deliveringDate;
        return this;
    }

    public String getDeliver() {
        return deliver;
    }

    public ProductReportHeadDTO setDeliver(String deliver) {
        this.deliver = deliver;
        return this;
    }

    public String getTester() {
        return tester;
    }

    public ProductReportHeadDTO setTester(String tester) {
        this.tester = tester;
        return this;
    }

    public String getDeliverFactory() {
        return deliverFactory;
    }

    public ProductReportHeadDTO setDeliverFactory(String deliverFactory) {
        this.deliverFactory = deliverFactory;
        return this;
    }

    public RepoBaseSerialNumber getRepoBaseSerialNumber() {
        return repoBaseSerialNumber;
    }

    public ProductReportHeadDTO setRepoBaseSerialNumber(RepoBaseSerialNumber repoBaseSerialNumber) {
        this.repoBaseSerialNumber = repoBaseSerialNumber;
        return this;
    }

    public String getTestItemString() {
        return testItemString;
    }

    public ProductReportHeadDTO setTestItemString(String testItemString) {
        this.testItemString = testItemString;
        return this;
    }

    public String getException() {
        return exception;
    }

    public ProductReportHeadDTO setException(String exception) {
        this.exception = exception;
        return this;
    }

    public Integer getIsPublished() {
        return isPublished;
    }

    public ProductReportHeadDTO setIsPublished(Integer isPublished) {
        this.isPublished = isPublished;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ProductReportHeadDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public List<TestResultDTO> getTestResultDTOList() {
        return testResultDTOList;
    }

    public ProductReportHeadDTO setTestResultDTOList(List<TestResultDTO> testResultDTOList) {
        this.testResultDTOList = testResultDTOList;
        return this;
    }

    public TestReportRecord getTestReportRecord() {
        return testReportRecord;
    }

    public ProductReportHeadDTO setTestReportRecord(TestReportRecord testReportRecord) {
        this.testReportRecord = testReportRecord;
        return this;
    }

    @Override
    public String toString() {
        return "ProductReportHeadDTO{" +
                "batchNumberId=" + batchNumberId +
                ", deliveringDate=" + deliveringDate +
                ", deliver='" + deliver + '\'' +
                ", tester='" + tester + '\'' +
                ", deliverFactory='" + deliverFactory + '\'' +
                ", repoBaseSerialNumber=" + repoBaseSerialNumber +
                ", testItemString='" + testItemString + '\'' +
                ", exception='" + exception + '\'' +
                ", isPublished=" + isPublished +
                ", status=" + status +
                ", testResultDTOList=" + testResultDTOList +
                ", testReportRecord=" + testReportRecord +
                '}';
    }
}
