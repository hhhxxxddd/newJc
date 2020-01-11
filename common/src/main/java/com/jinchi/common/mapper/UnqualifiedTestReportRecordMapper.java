package com.jinchi.common.mapper;

import com.jinchi.common.domain.UnqualifiedTestReportRecord;
import com.jinchi.common.dto.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：FangLe
 * @className:UnqualifiedTestReportRecordMapper
 * @description: 不合格审评表
 * @date:19:37 2018/12/20
 */
@Component
@Mapper
public interface UnqualifiedTestReportRecordMapper {
    /**
     * 新增
     */
    void insert(UnqualifiedTestReportRecord unqualifiedTestReportRecord);

    /**
     * 更新
     */
    void update(UnqualifiedTestReportRecord unqualifiedTestReportRecord);

    /**
     * 根据批号id查询
     */
    List<UnqualifiedTestReportRecord> findByBatchNumberId(@Param("batchNumberId") Integer batchNumberId);

    /**
     * 查询所有 pageBean
     */
    List<UnqualifiedTestReportRecord> getAll(@Param("createPerson") String createPerson,@Param("pageBean") PageBean pageBean);

    /**
     * 根据Id查询
     *
     * @param id
     * @return
     */
    UnqualifiedTestReportRecord findById(@Param("id") Integer id);

    /**
     * 计算total
     * @param createPerson
     * @return
     */
    Integer countSum(@Param("createPerson") String createPerson);

}
