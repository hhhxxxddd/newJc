package com.jinchi.common.dto.quality.purchase;

import com.jinchi.common.domain.TestItemResultRecord;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author：XudongHu
 * @className:ValidTestRecordDTO
 * @description: 进货/成品中的 检测项目数据
 * @date:13:39 2018/12/29
 */
public class ValidTestRecordDTO {

    @ApiModelProperty("testReportRecordId")
    private Integer Id;

    @ApiModelProperty("编号")
    private String serialNumber;

    @ApiModelProperty("检测项目结果")
    private List<TestItemResultRecord> resultRecordList;

    @ApiModelProperty("此条结果是否合格")
    private Integer decision;

    public Integer getId() {
        return Id;
    }

    public ValidTestRecordDTO setId(Integer id) {
        Id = id;
        return this;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public ValidTestRecordDTO setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public List<TestItemResultRecord> getResultRecordList() {
        return resultRecordList;
    }

    public ValidTestRecordDTO setResultRecordList(List<TestItemResultRecord> resultRecordList) {
        this.resultRecordList = resultRecordList;
        return this;
    }

    public Integer getDecision() {
        return decision;
    }

    public ValidTestRecordDTO setDecision(Integer decision) {
        this.decision = decision;
        return this;
    }

    @Override
    public String toString() {
        return "ValidTestRecordDTO{" +
                "Id=" + Id +
                ", serialNumber='" + serialNumber + '\'' +
                ", resultRecordList=" + resultRecordList +
                ", decision=" + decision +
                '}';
    }
}
