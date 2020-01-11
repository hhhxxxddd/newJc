package com.jinchi.common.mapper;


import com.jinchi.common.domain.UnqualifiedTracingRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


/**
 * 说明:
 * <br>
 *
 * @author ZSCDumin
 * <br>
 * 邮箱: 2712220318@qq.com
 * <br>
 * 日期: 2018/11/19
 * <br>
 * 版本: 1.0
 */
@Mapper
@Component
public interface UnqualifiedTracingRecordMapper {

    /**
     * 新增
     *
     * @param tracingRecord 新增不合格追踪
     */
    void insert(UnqualifiedTracingRecord tracingRecord);

    /**
     * 根据批号id查询
     *
     * @param batchNumberId 批号id
     * @return
     */
    UnqualifiedTracingRecord findByBatchNumberId(@Param("batchNumberId") Integer batchNumberId);

    /**
     * 更新
     * @param unqualifiedTracingRecord 不合格跟踪实体
     */
    void update(UnqualifiedTracingRecord unqualifiedTracingRecord);
}
