package com.jinchi.common.service;


import com.jinchi.common.domain.ProductionBatchInfo;
import com.jinchi.common.domain.ProductionBatchRetrospectInfo;
import com.jinchi.common.domain.ProductionBatchRuleDetail;
import com.jinchi.common.domain.ProductionInstrumentDataTj;
import com.jinchi.common.dto.*;

import java.util.List;

public interface ProductionBatchInfoService {

    BatchDataMapDTO getAllRecordsById(Long batchId);

    ProductionInstrumentDataTj getById(Long id);

    List getByCondition(String cellNum, String startTime, String endTime);

    Integer save(ProductionBatchInfoDateTostring productionBatchInfoDateTostring);

    Page getAllInfo(Integer page, Integer size);

    void delOneByCode(long code);

    void delSomeByCodes(List<Long> codes);

    void update(BatchDTO batchDTO);

    ProductionBatchInfoDTO getDetailByCode(long code);

    Page getAllInfoByCondition(String condition, Integer page, Integer size);

    ProductionBatchRuleDetailsDTO GetAllBatchRuleData();

    ProductionBatchRuleDetail getByCode(Integer code);

    ProductionBatchRetrospectInfo getRecord(String creater, String batch);

    ProductionBatchInfo getInfo(String batch);

    List compareByBatch(String batch);

    void genNewRecord(ProductionBatchInfo productionBatchInfo);

    ProductionBatchInfo genSameRecord(ProductionBatchInfo productionBatchInfo);

    Integer getJumpBatchNumber(String process);
}
