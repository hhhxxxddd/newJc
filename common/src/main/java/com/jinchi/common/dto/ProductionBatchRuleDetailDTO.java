package com.jinchi.common.dto;


import com.jinchi.common.domain.ProductionBatchRuleDetail;


import java.util.List;

public class ProductionBatchRuleDetailDTO {


    List<ProductionBatchRuleDetail> productionBatchRuleDetails ;
    Byte ruleCode;

    public ProductionBatchRuleDetailDTO() {

    }
    public ProductionBatchRuleDetailDTO(List<ProductionBatchRuleDetail> productionBatchRuleDetails, Byte ruleCode) {
        this.productionBatchRuleDetails = productionBatchRuleDetails;
        this.ruleCode = ruleCode;
    }

    public List<ProductionBatchRuleDetail> getProductionBatchRuleDetails() {
        return productionBatchRuleDetails;
    }

    public void setProductionBatchRuleDetails(List<ProductionBatchRuleDetail> productionBatchRuleDetails) {
        this.productionBatchRuleDetails = productionBatchRuleDetails;
    }

    public Byte getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(Byte ruleCode) {
        this.ruleCode = ruleCode;
    }
}