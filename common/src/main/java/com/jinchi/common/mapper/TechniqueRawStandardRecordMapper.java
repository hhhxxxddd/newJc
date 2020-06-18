package com.jinchi.common.mapper;

import com.jinchi.common.domain.TechniqueRawStandardRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:TechniqueRawStandardRecordMapper
 * @description: 原材料标准表
 * @date:0:14 2018/12/28
 */
@Mapper
@Component
public interface TechniqueRawStandardRecordMapper {

    /**
     * 新增
     *
     * @param techniqueRawStandardRecord 原材料标准实体
     * @return
     */
    void insert(TechniqueRawStandardRecord techniqueRawStandardRecord);

    /**
     * 更新 仅时间
     *
     * @param techniqueRawStandardRecord 原材料标准实体
     */
    void update(TechniqueRawStandardRecord techniqueRawStandardRecord);

    /**
     * 查询
     *
     * @param id 主键
     * @return
     */
    TechniqueRawStandardRecord findById(@Param("id") Integer id);

    /**
     * 查询
     *
     * @param batchNumberId 批号id
     * @return
     */
    TechniqueRawStandardRecord findByBatchNumberId(@Param("batchNumberId") Integer batchNumberId);

    /**
     * 根据工厂id和原料id查询
     *
     * @param materialId
     * @param factoryId
     * @return
     */
    List<TechniqueRawStandardRecord> byMaterialIdAndFactoryId(@Param("materialId") Integer materialId, @Param("factoryId") Integer factoryId);

    //byFactoryId

    /**
     * 根据工厂id查询
     *
     * @param factoryId
     * @return
     */
    List<TechniqueRawStandardRecord> byFactoryId(@Param("factoryId") Integer factoryId);

    /**
     * 查询最新标准
     *
     * @param materialId 原料id
     * @param factoryId  工厂id
     * @return
     */
    TechniqueRawStandardRecord lastedStandard(@Param("materialId") Integer materialId, @Param("factoryId") Integer factoryId);


    List<TechniqueRawStandardRecord> getAll();
}
