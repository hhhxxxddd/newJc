package com.jinchi.common.dto;

import com.jinchi.common.domain.ProductionBatchInfo;

public class ProductionBatchInfoDTO {

    private ProductionBatchInfo productionBatchInfo;
    private String modify_people;
   //找出修订人




    public ProductionBatchInfoDTO() {

    }

    public ProductionBatchInfoDTO(ProductionBatchInfo productionBatchInfo, String modify_people) {
        this.productionBatchInfo = productionBatchInfo;
        this.modify_people = modify_people;
    }

    public ProductionBatchInfo getProductionBatchInfo() {
        return productionBatchInfo;
    }

    public void setProductionBatchInfo(ProductionBatchInfo productionBatchInfo) {
        this.productionBatchInfo = productionBatchInfo;
    }

    public String getModify_people() {
        return modify_people;
    }

    public void setModify_people(String modify_people) {
        this.modify_people = modify_people;
    }
}