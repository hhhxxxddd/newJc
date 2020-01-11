package com.jinchi.common.mapper;

import com.jinchi.common.domain.UnqualifiedTracingTestRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:UnqualifiedTracingTestRecordMapper
 * @description: 不合格追踪 关系表
 * @date:22:34 2019/1/24
 * @Modifer:
 */
@Component
@Mapper
public interface UnqualifiedTracingTestRecordMapper {

    /**
     * 根据追踪表id查询
     * @param unqualifiedTracingRecordId 追踪表id
     * @return
     */
    List<UnqualifiedTracingTestRecord> byTraceId(Integer unqualifiedTracingRecordId);

    /**
     * 批量新增
     * @param unqualifiedTracingTestRecords
     */
    void batchSave(List<UnqualifiedTracingTestRecord> unqualifiedTracingTestRecords);
}
