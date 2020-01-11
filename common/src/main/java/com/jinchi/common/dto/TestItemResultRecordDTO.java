package com.jinchi.common.dto;

import com.jinchi.common.domain.ProductTestItemStandard;
import com.jinchi.common.domain.RawTestItemStandard;
import com.jinchi.common.domain.TestItem;
import com.jinchi.common.domain.TestItemResultRecord;

import java.util.List;

/**
 * @author:YuboWu
 * @description:
 * @date:14:28 2018/11/25
 */
public class TestItemResultRecordDTO {
    private Integer id;
    private String name;
    private String testResult;
    private TestItemResultRecord testItemResultRecord;
    private RawTestItemStandard rawTestItemStandard;
    private List<ProductTestItemStandard> productTestItemStandardList;
    private TestItem testItem;

    public List<ProductTestItemStandard> getProductTestItemStandardList() {
        return productTestItemStandardList;
    }

    public void setProductTestItemStandardList(List<ProductTestItemStandard> productTestItemStandardList) {
        this.productTestItemStandardList = productTestItemStandardList;
    }

    public RawTestItemStandard getRawTestItemStandard() {
        return rawTestItemStandard;
    }

    public void setRawTestItemStandard(RawTestItemStandard rawTestItemStandard) {
        this.rawTestItemStandard = rawTestItemStandard;
    }

    public TestItem getTestItem() {
        return testItem;
    }

    public void setTestItem(TestItem testItem) {
        this.testItem = testItem;
    }

    public TestItemResultRecord getTestItemResultRecord() {
        return testItemResultRecord;
    }

    public void setTestItemResultRecord(TestItemResultRecord testItemResultRecord) {
        this.testItemResultRecord = testItemResultRecord;
    }

    public TestItemResultRecordDTO() {
    }

    public TestItemResultRecordDTO(TestItemResultRecord record) {
        this.testResult = record.getTestResult();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }
}
