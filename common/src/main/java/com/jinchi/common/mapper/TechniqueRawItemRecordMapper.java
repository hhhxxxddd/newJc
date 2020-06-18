package com.jinchi.common.mapper;

import com.jinchi.common.domain.TechniqueRawItemRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:TechniqueRawItemRecordMapper
 * @description: 物料主成分对应表
 * @date:17:23 2019/1/7
 */
@Mapper
@Component
public interface TechniqueRawItemRecordMapper {

    /**
     * 批量新增
     *
     * @param techniqueRawItemRecords 物料主成分对应表
     */
    void batchInsert(List<TechniqueRawItemRecord> techniqueRawItemRecords);

    /**
     * 根据物料id查询
     */
    List<TechniqueRawItemRecord> byRawId(@Param("rawId") Integer rawId);

    void deleteByMaterialId(@Param("rawId") Integer rawId);
}
