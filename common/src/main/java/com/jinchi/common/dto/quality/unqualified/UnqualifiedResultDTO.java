package com.jinchi.common.dto.quality.unqualified;


import com.jinchi.common.domain.UnqualifiedTestItemResultRecord;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author：XudongHu
 * @className:unqualifiedResultDTO
 * @description: 不合格记录表 数据
 * @date:19:47 2018/12/20
 */
public class UnqualifiedResultDTO {
    @ApiModelProperty("主键UnqualifiedTestReportRecord")
    private Integer id;

    @ApiModelProperty("编号")
    private String serialNumber;

    @ApiModelProperty("判定")  //0不合格 1合格
    private Integer decision;

    @ApiModelProperty("数据结果")
    private List<UnqualifiedTestItemResultRecord> testItemResults;


    public Integer getId() {
        return id;
    }

    public UnqualifiedResultDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getDecision() {
        return decision;
    }

    public UnqualifiedResultDTO setDecision(Integer decision) {
        this.decision = decision;
        return this;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public UnqualifiedResultDTO setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public List<UnqualifiedTestItemResultRecord> getTestItemResults() {
        return testItemResults;
    }

    public UnqualifiedResultDTO setTestItemResults(List<UnqualifiedTestItemResultRecord> testItemResults) {
        this.testItemResults = testItemResults;
        return this;
    }

    @Override
    public String toString() {
        return "UnqualifiedResultDTO{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", decision=" + decision +
                ", testItemResults=" + testItemResults +
                '}';
    }
}
