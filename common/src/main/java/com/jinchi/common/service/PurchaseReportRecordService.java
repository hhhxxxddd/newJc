package com.jinchi.common.service;

import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.quality.purchase.PurchaseRecordHeadDTO;

import java.util.List;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:PurchaseReportRecordService2
 * @description: 进货检测
 * @date:13:51 2018/12/29
 */
public interface PurchaseReportRecordService {

    /**
     * 查询所有原材料数据 -创建人名字模糊
     * -已生成未生成
     * @param personName 创建人名称
     * @param isGenerate 是否已生成
     * @param pageBean  分页实体
     * @return
     */
    PageBean allPurchaseRaw(String personName, Integer isGenerate, PageBean pageBean);

    /**
     * 根据多个批号生成进货检验报告单
     * 初次确认
     * @param batchNumberIds 批号ids
     * @return
     */
    Map<Object, Object> previewPurchase(List<Integer> batchNumberIds);

    /**
     * 根据多个批号生成进货检验报告单
     * 二次确认
     * @param batchNumberIds 批号ids
     * @param createPersonId 创建人id
     */
    String generatePurchase(List<Integer> batchNumberIds,Integer createPersonId);

    /**
     * 查询所有进货检验报告单数据
     * @param materialName 原材料名称
     * @param pageBean 分页实体
     * @return
     */
    PageBean allPurchase(String materialName,PageBean pageBean);

    /**
     * 查看详情
     * @param batchNumberId 批号id
     * @return
     */
    PurchaseRecordHeadDTO byBatchNumberId(Integer batchNumberId);


    /**
     * 编辑 进货报告单
     * @param purchaseRecordHeadDTO 报告单DTO
     */
    Integer updatePurchase(PurchaseRecordHeadDTO purchaseRecordHeadDTO);

    /**
     * 查询所有待发布的进货检验报告单数据
     * @param createPerson 创建人名称
     * @param pageBean 分页实体
     * @return
     */
    PageBean allRelease(String createPerson,PageBean pageBean);

    /**
     * 发布
     * @param batchNumberId 批号id
     * @return
     */
    String release(Integer batchNumberId);

    /**
     * 根据批号id删除
     * @param batchNumberId 批号id
     */
    void deleteByBatchNumberId(Integer batchNumberId);
}
