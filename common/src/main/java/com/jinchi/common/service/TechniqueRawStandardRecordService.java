package com.jinchi.common.service;

import com.jinchi.common.domain.TechniqueBaseRawManufacturer;
import com.jinchi.common.domain.TechniqueBaseRawMaterial;
import com.jinchi.common.domain.TestItem;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.RawStandardDTO;
import com.jinchi.common.dto.technique.TechniqueRawStandardRecordDTO;

import java.util.List;

/**
 * @author：XudongHu
 * @className:TechniqueRawStandardRecordService
 * @description:
 * @date:23:26 2018/12/27
 */
public interface TechniqueRawStandardRecordService {
    /**
     * 查询所有原材料
     * @See name 名称模糊
     * @return
     */
    List<TechniqueBaseRawMaterial> baseRawMaterialNameLike(String name);

    /**
     * 新增一个原材料 唯一名称验证
     * @param serialNumberId 原材料实体
     * @return
     */
    TechniqueBaseRawMaterial addNewRawMaterial(Integer serialNumberId,List<Integer> testItemIds);

    /**
     * 查询所有原料厂家
     * @param name 厂家名称模糊
     * @return
     */
    List<TechniqueBaseRawManufacturer> baseRawManufacturerNameLike(String name);

    /**
     * 新增原料厂家
     * @param newValue 厂家实体
     * @return
     */
    TechniqueBaseRawManufacturer addNewRawManufacturer(TechniqueBaseRawManufacturer newValue);




    /**
     * 新增/迭代 一个原材料标准
     * @param commonBatchNumberDTO 公共批号DTO
     * @return
     */
    CommonBatchNumberDTO addRawStandard(CommonBatchNumberDTO<TechniqueRawStandardRecordDTO> commonBatchNumberDTO);

    /**
     * 更新 一个原材料标准
     * @param commonBatchNumberDTO 公共批号DTO
     * @return
     */
    CommonBatchNumberDTO updateRawStandard(CommonBatchNumberDTO<TechniqueRawStandardRecordDTO> commonBatchNumberDTO);

    /**
     * 查询所有原材料标准
     * @param personName 名称模糊
     * @return
     */
    List<CommonBatchNumberDTO> allRawStandardPersonNameLike(String personName,Integer materialId,Integer factoryId);

    /**
     * 原材料标准详情
     * @param batchNumberId 批号id
     * @return
     */
    CommonBatchNumberDTO rawStandardDetail(Integer batchNumberId);

    /**
     * 原材料主成分
     * @param rawId  原材料主键id
     * @return
     */
    List<TestItem> rawIngredient(Integer rawId);

    /**
     * 查询最新标准
     * @param rawId 原材料编号id
     * @return
     */
    List<String> lastStandard(Integer rawId, String materialName, String manufacturerName);

    TechniqueBaseRawMaterial addRowExtra(String materialName,Integer[] ids);

    List getItemsByRawId(Integer rawId);

    List<RawStandardDTO> getCurrentRawStandard();

}
