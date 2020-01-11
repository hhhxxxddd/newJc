package com.jinchi.common.service;


import com.jinchi.common.domain.UnqualifiedTracingRecord;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.PageBean;

/**
 * 说明:不合格品追踪服务接口
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
public interface UnqualifiedTracingRecordService {
    /**
     * 新增不合格追踪
     *
     * @param batchNumberId 批号id
     */
    void generate(Integer batchNumberId, Integer userId);

    /**
     * 查询详情
     *
     * @param batchNumberId 批号id
     * @return
     */
    CommonBatchNumberDTO byBatchNumberId(Integer batchNumberId);

    /**
     * 更新
     *
     * @return
     */
    CommonBatchNumberDTO update(CommonBatchNumberDTO<UnqualifiedTracingRecord> batchNumberDTO);

    /**
     * 查询所有-分页
     *
     * @param personName
     * @param pageBean
     * @return
     */
    PageBean byPage(String personName, PageBean pageBean);

}
