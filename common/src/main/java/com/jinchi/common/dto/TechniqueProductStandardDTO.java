package com.jinchi.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jinchi.common.domain.CommonBatchNumber;
import com.jinchi.common.domain.TechniqueProductNewStandardRecord;
import com.jinchi.common.domain.TestItem;
import com.jinchi.common.domain.TestItemsPlus;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TechniqueProductStandardDTO {

    CommonBatchNumber commonBatchNumber;

    String createPeople;

    String meterialClass;

    String productName;

    TechniqueProductNewStandardRecord techniqueProductNewStandardRecord;

    List<TestItemsPlus> items;

    public CommonBatchNumber getCommonBatchNumber() {
        return commonBatchNumber;
    }

    public void setCommonBatchNumber(CommonBatchNumber commonBatchNumber) {
        this.commonBatchNumber = commonBatchNumber;
    }

    public String getCreatePeople() {
        return createPeople;
    }

    public void setCreatePeople(String createPeople) {
        this.createPeople = createPeople;
    }

    public String getMeterialClass() {
        return meterialClass;
    }

    public void setMeterialClass(String meterialClass) {
        this.meterialClass = meterialClass;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public TechniqueProductNewStandardRecord getTechniqueProductNewStandardRecord() {
        return techniqueProductNewStandardRecord;
    }

    public void setTechniqueProductNewStandardRecord(TechniqueProductNewStandardRecord techniqueProductNewStandardRecord) {
        this.techniqueProductNewStandardRecord = techniqueProductNewStandardRecord;
    }

    public List<TestItemsPlus> getItems() {
        return items;
    }

    public void setItems(List<TestItemsPlus> items) {
        this.items = items;
    }
}
