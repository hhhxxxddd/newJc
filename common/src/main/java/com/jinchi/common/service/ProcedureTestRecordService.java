package com.jinchi.common.service;


import com.github.pagehelper.PageInfo;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.ProcedureTestRecordDTO;

import java.util.List;

/**
 * @author：XudongHu
 * @className:ProcedureTestRecordService
 * @description:
 * @date:12:35 2018/11/23
 */

public interface ProcedureTestRecordService {
    /**
     * 新增
     */
    CommonBatchNumberDTO add(CommonBatchNumberDTO<List<ProcedureTestRecordDTO>> procedureBatchNumber);

    /**
     * 更新
     */
    CommonBatchNumberDTO update(CommonBatchNumberDTO<List<ProcedureTestRecordDTO>> procedureBatchNumber);

    /**
     * 迭代
     */
    CommonBatchNumberDTO iteration(CommonBatchNumberDTO<List<ProcedureTestRecordDTO>> procedureBatchNumber);

    /**
     * 根据批号id查询
     */
    CommonBatchNumberDTO findByBatchNumberId(Integer batchNumberId);

    /**
     * 根据批号id删除
     */
    void deleteByBatchNumberId(Integer batchNumberId);

    /**
     * 根据批号ids删除
     */
    void deleteByBatchNumberIds(List<Integer> batchNumberIds);

    /**
     * 查询所有
     */
    List<CommonBatchNumberDTO> findAll();

    /**
     * 创建人名称模糊查询所有-分页
     */
    PageInfo findAllByPage(String personName, Integer page, Integer size, String fieldName, String orderType);


    /**
     * 通过送样工厂+工序 查询所有 制程检验 的取样点+检测项目
     * 都不选,返回所有制程检测下的送样工厂
     * 选送样工厂,返回其下工序
     * 选送样工厂和工序,返回 取样点 和检测项目的ids
     *
     * @param deliveryId
     * @param procedureId
     * @return
     */
    Object queryTestItems(Integer deliveryId, Integer procedureId, String samplePointName);

    void setIteration(Integer batchNumberId);
}
