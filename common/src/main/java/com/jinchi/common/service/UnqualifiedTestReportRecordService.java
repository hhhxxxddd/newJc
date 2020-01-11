package com.jinchi.common.service;


import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.quality.unqualified.UnqualifiedDetailDTO;

import java.util.Map;

/**
 * @author：XudongHu
 * @className:UnqualifiedTestReportRecordService
 * @description: 不合格评审表
 * @date:15:40 2018/12/20
 */
public interface UnqualifiedTestReportRecordService {

    /**
     * 新增不合格评审表
     * ==>
     * 进货检测
     * ==>
     * 成品检测
     * 需要使用此接口
     *
     * @param batchNumberId 批号id
     * @return
     */
    void generate(Integer batchNumberId, Integer userId);

    /**
     * 更新不合格评审表
     * ==>
     * 进货检测
     * ==>
     * 成品检测
     * 需要使用此接口
     *
     * @return
     */
    void update(UnqualifiedDetailDTO unqualifiedDetailDTO);

    /**
     * 查看详情
     *
     * @param batchNumberId 批号id
     * @return
     */
    Map<Object, Object> findByBatchNumberId(Integer batchNumberId);

    /**
     * 查询所有-分页
     *
     * @return
     */
    PageBean getAllByPage(String createPerson, PageBean pageBean);
}
