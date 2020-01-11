package com.jinchi.common.mapper;

import com.jinchi.common.domain.TechniqueRawTestItemStandard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:TechniqueRawTestItemStandardMapper
 * @description:
 * @date:0:51 2018/12/28
 */
@Mapper
@Component
public interface TechniqueRawTestItemStandardMapper {
    /**
     * 批量新增
     * @param itemStandardList
     */
    void batchInsert(List<TechniqueRawTestItemStandard> itemStandardList);

    /**
     * 根据标准表id删除
     * @param recordId
     */
    void deleteByRecordId(@Param("recordId") Integer recordId);

    /**
     * 根据标准表id查询
     * @param recordId 标准表id
     * @return
     */
    List<TechniqueRawTestItemStandard> findByRecordId(@Param("recordId") Integer recordId);


    String getValueByStandardIdByTestItemsId(@Param("standardId") Integer standardId, @Param("testItemsId") Integer testItemsId);
}
