package com.jinchi.common.dto;

import com.jinchi.common.domain.TestReportRecord;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 检测报告单DTO
 * @date 2018/12/14 15:23
 */
public class TestReportRecordDTO {

    @ApiModelProperty("检测报告单记录")
    private TestReportRecord testReportRecord;
    @ApiModelProperty("检测项目记录")
    private List<TestItemResultRecordDTO> testItemResultRecordDTOList;
    @ApiModelProperty("检测项目名称")
    private String testItemName;
    @ApiModelProperty("送样记录")
    SampleDeliveringRecordDTO sampleDeliveringRecordDTO;

    public SampleDeliveringRecordDTO getSampleDeliveringRecordDTO() {
        return sampleDeliveringRecordDTO;
    }

    public void setSampleDeliveringRecordDTO(SampleDeliveringRecordDTO sampleDeliveringRecordDTO) {
        this.sampleDeliveringRecordDTO = sampleDeliveringRecordDTO;
    }

    public String getTestItemName() {
        return testItemName;
    }

    public void setTestItemName(String testItemName) {
        this.testItemName = testItemName;
    }

    @ApiModelProperty("检测人")
    private String judegrName;

    public TestReportRecord getTestReportRecord() {
        return testReportRecord;
    }

    public void setTestReportRecord(TestReportRecord testReportRecord) {
        this.testReportRecord = testReportRecord;
    }

    public List<TestItemResultRecordDTO> getTestItemResultRecordDTOList() {
        return testItemResultRecordDTOList;
    }

    public void setTestItemResultRecordDTOList(List<TestItemResultRecordDTO> testItemResultRecordDTOList) {
        this.testItemResultRecordDTOList = testItemResultRecordDTOList;
    }

    public String getJudegrName() {
        return judegrName;
    }

    public void setJudegrName(String judegrName) {
        this.judegrName = judegrName;
    }
}
