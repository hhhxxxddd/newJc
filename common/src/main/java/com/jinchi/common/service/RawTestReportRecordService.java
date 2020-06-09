package com.jinchi.common.service;

import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.RawTestReportDTO;

import java.util.List;

/**
 * @author：YuboWu
 */
public interface RawTestReportRecordService {

    /**
     * 查询所有
     */
    List<RawTestReportDTO> findAll();

    /**
     * 详情（根据样品送检id查）
     */
    RawTestReportDTO findDetailsById(Integer id);

    /**
     * 保存 检验结果
     */
    RawTestReportDTO updateResults(RawTestReportDTO rawTestReportDTO);

    /**
     * 根据送样工厂名模糊查询所有-分页
     */
    PageBean<RawTestReportDTO> findByFactoryNameByPage(String factoryName, PageBean pageBean);

    /**
     * 详情（根据批号id查）
     */
    RawTestReportDTO details(Integer id);
}