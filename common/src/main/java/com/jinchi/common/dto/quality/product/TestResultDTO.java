package com.jinchi.common.dto.quality.product;

import com.jinchi.common.domain.TestItem;
import com.jinchi.common.domain.TestItemResultRecord;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author：XudongHu
 * @className:TestResultDTO
 * @description: 检测结果记录DTO
 * @date:14:52 2019/1/10
 */
public class TestResultDTO {
    @ApiModelProperty("检测项目")
    private TestItem testItem;

    @ApiModelProperty("检测项目结果")
    private TestItemResultRecord testItemResultRecord;

    @ApiModelProperty("行业标准")
    private String standardValue;

    public TestItem getTestItem() {
        return testItem;
    }

    public TestResultDTO setTestItem(TestItem testItem) {
        this.testItem = testItem;
        return this;
    }

    public TestItemResultRecord getTestItemResultRecord() {
        return testItemResultRecord;
    }

    public TestResultDTO setTestItemResultRecord(TestItemResultRecord testItemResultRecord) {
        this.testItemResultRecord = testItemResultRecord;
        return this;
    }

    public String getStandardValue() {
        return standardValue;
    }

    public TestResultDTO setStandardValue(String standardValue) {
        this.standardValue = standardValue;
        return this;
    }

    @Override
    public String toString() {
        return "TestResultDTO{" +
                "testItem=" + testItem +
                ", testItemResultRecord=" + testItemResultRecord +
                ", standardValue='" + standardValue + '\'' +
                '}';
    }
}
