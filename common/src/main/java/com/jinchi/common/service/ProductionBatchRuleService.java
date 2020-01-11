package com.jinchi.common.service;


import com.jinchi.common.domain.BatchRuleDTO;
import com.jinchi.common.domain.ProductionBatchInfo;
import com.jinchi.common.domain.ProductionBatchRuleDetail;
import com.jinchi.common.domain.ProductionBatchRuleHead;

import java.util.List;
import java.util.Map;

public interface ProductionBatchRuleService {

    ProductionBatchInfo getInfoByCode(long code);

    List<ProductionBatchRuleDetail> getDetailByRuleCode(Byte code);

    void delDetailByRuleCode(Short code);

    void addOneDetail(ProductionBatchRuleDetail productionBatchRuleDetail);

    Map<String, Object> updateAll(List<ProductionBatchRuleDetail> productionBatchRuleDetails, Byte ruleCode);

    List<ProductionBatchRuleHead> getAll();

    List<BatchRuleDTO> getAllInfos();

    void UpdateState(Byte code, Boolean flag);

//    ProductionBatchRetrospectInfo getRecord(Integer userId, String batch);
}
