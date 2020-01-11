package com.jinchi.common.mapper;

import com.jinchi.common.domain.TechniqueProductStandardRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:TechniqueProductStandardRecordMapper
 * @description: 技术 成品标准
 * @date:16:38 2018/12/28
 */
@Mapper
@Component
public interface TechniqueProductStandardRecordMapper {

    /**
     * 新增
     * @param techniqueProductStandardRecord 技术成品标准实体
     */
    void insert(TechniqueProductStandardRecord techniqueProductStandardRecord);

    /**
     * 更新
     * @param techniqueProductStandardRecord 技术成品标准实体
     */
    void update(TechniqueProductStandardRecord techniqueProductStandardRecord);

    /**
     * 根据批号id查询
     */
    TechniqueProductStandardRecord byBatchNumberId(@Param("batchNumberId") Integer batchNumberId);


    /**
     * 查询所有
     * @param productId 产品id
     * @param classId   型号
     * @return
     */
    List<TechniqueProductStandardRecord> byProductIdAndClassId(@Param("productId") Integer productId, @Param("classId") Integer classId);


    /**
     * 审核通过的最新标准
     * @return
     */
    TechniqueProductStandardRecord lastedStandard(@Param("productId") Integer productId);
}
