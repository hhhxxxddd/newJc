package com.jinchi.common.service;

import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.UnqualifiedTestItemResultRecord;

import java.util.List;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 不合格检测项目结果纪录接口
 * @date 2018/11/26 20:28
 */
public interface UnqualifiedTestItemResultRecordService {

    /**
     * 添加一个不合格检测项目结果纪录
     *
     * @param unqualifiedTestItemResultRecord 不合格检测项目结果纪录
     */
    UnqualifiedTestItemResultRecord insert(UnqualifiedTestItemResultRecord unqualifiedTestItemResultRecord);

    /**
     * 更新一个不合格检测项目结果纪录
     *
     * @param unqualifiedTestItemResultRecord 不合格检测项目结果纪录
     */
    UnqualifiedTestItemResultRecord update(UnqualifiedTestItemResultRecord unqualifiedTestItemResultRecord);

    /**
     * 根据ID删除不合格检测项目结果纪录
     *
     * @param id 不合格检测项目结果纪录ID
     */
    void deleteById(Integer id);

    /**
     * 根据IDS删除不合格检测项目结果纪录
     *
     * @param ids 不合格检测项目结果纪录ID数组
     */
    void deleteByIds(Integer[] ids);

    /**
     * 根据ID查找不合格检测项目结果纪录
     *
     * @param id 不合格检测项目结果纪录ID
     * @return UnqualifiedTestItemResultRecord
     */
    UnqualifiedTestItemResultRecord getById(Integer id);

    /**
     * 查询所有的不合格检测项目结果纪录
     *
     * @param page
     * @param size
     * @param fieldName
     * @param orderType
     * @return List<UnqualifiedTestItemResultRecord>
     */
    PageInfo getAllByPage(Integer page, Integer size, String fieldName, String orderType);

    /**
     * 批量插入
     *
     * @param unqualifiedTestItemResultRecords
     */
    void batchInsert(List<UnqualifiedTestItemResultRecord> unqualifiedTestItemResultRecords);

    /**
     * 批量插入
     *
     * @param unqualifiedTestItemResultRecords
     */
    void batchUpdate(List<UnqualifiedTestItemResultRecord> unqualifiedTestItemResultRecords);
}
