package com.jinchi.common.mapper;

import com.jinchi.common.domain.CommonBatchNumberChild;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author XudongHu
 * @className CommonBatchNumberChildMapper
 * @apiNote TODO
 * @modifer
 * @since 2019/10/23 0:47
 */
@Mapper
@Component
public interface CommonBatchNumberChildMapper {
    /**
     * 新增
     * @param commonBatchNumberChild
     * @return
     */
    int insert(CommonBatchNumberChild commonBatchNumberChild);

    /**
     * 查询
     * @param commonBatchNumberChild
     * @return
     */
    CommonBatchNumberChild selectByExample(CommonBatchNumberChild commonBatchNumberChild);

    /**
     * 根据批号id查询
     * @param batchNumberId
     * @return
     */
    CommonBatchNumberChild selectByBatchNumberId(@Param("batchNumberId") Integer batchNumberId);

    /**
     * 根据子批号id查询
     * @param childId
     * @return
     */
    CommonBatchNumberChild selectByChildId(@Param("childId") Integer childId);

    /**
     * 删除
     * @param batchNumberId
     * @return
     */
    int deleteByBatchNumberId(@Param("batchNumberId") Integer batchNumberId);

    /**
     * 删除
     * @param childId
     * @return
     */
    int deleteByChildId(@Param("childId") Integer childId);
}
