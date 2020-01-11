package com.jinchi.common.service;

import com.jinchi.common.domain.TechniqueBaseProductClass;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.technique.TechniqueProductStandardRecordDTO;

import java.util.List;

/**
 * @author：XudongHu
 * @className:TechniqueProductStandardRecordService
 * @description:
 * @date:15:31 2018/12/28
 */
public interface TechniqueProductStandardRecordService {
    /**
     * 新增型号
     * @param techniqueBaseProductClass
     * @return
     */
    TechniqueBaseProductClass newClass(TechniqueBaseProductClass techniqueBaseProductClass);

    /**
     * 查询所有型号
     * @param name 型号名称
     * @param parentId 父id -1为最上层
     * @return
     */
    List<TechniqueBaseProductClass> findAllClass(String name,Integer parentId);

    /**
     * 新增/迭代
     * @param commonBatchNumberDTO 批号dto
     * @return
     */
    CommonBatchNumberDTO newProductStandard(CommonBatchNumberDTO<TechniqueProductStandardRecordDTO> commonBatchNumberDTO);

    /**
     * 更新
     * @param commonBatchNumberDTO 批号dto
     * @return
     */
    CommonBatchNumberDTO updateProductStandard(CommonBatchNumberDTO<TechniqueProductStandardRecordDTO> commonBatchNumberDTO);

    /**
     * 根据批号id查询
     * @param batchNumberId
     * @return
     */
    CommonBatchNumberDTO byBatchNumberId(Integer batchNumberId);

    /**
     * 查询所有  根据创建人名称模糊 和产品id 和型号id
     */
    List<CommonBatchNumberDTO> byNameLikeAndMaterialIdAndClassId(String name,Integer productId,Integer classId);

    /**
     * 查询最新标准
     * @param productId 产品id
     * @param testItemIds 需要查询的项目标准
     * @return
     */
    List<String> lastStandard(Integer productId,List<Integer> testItemIds);
}
