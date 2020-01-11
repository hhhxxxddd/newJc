package com.jinchi.common.mapper;

import com.jinchi.common.domain.TechniqueProductTestItemStandard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:TechniqueProductTestItemStandardMapper
 * @description: 技术 成品标准值
 * @date:16:38 2018/12/28
 */
@Mapper
@Component
public interface TechniqueProductTestItemStandardMapper {
    /**
     * 批量新增
     * @param
     */
    void batchInsert(List<TechniqueProductTestItemStandard> itemList);

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
    List<TechniqueProductTestItemStandard> findByRecordId(@Param("recordId") Integer recordId);

    void insert(@Param("item") TechniqueProductTestItemStandard item);

}
