package com.jinchi.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.UnqualifiedTestItemResultRecord;
import com.jinchi.common.exceptions.EnumExceptions;
import com.jinchi.common.exceptions.JcExceptions;
import com.jinchi.common.mapper.UnqualifiedTestItemResultRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 不合格检测项目结果纪录接口实现类
 * @date 2018/11/26 20:28
 */
@Service
@Component
public class UnqualifiedTestItemResultRecordServiceImp implements UnqualifiedTestItemResultRecordService {
    private static final Logger logger = LoggerFactory.getLogger(UnqualifiedTestItemResultRecordServiceImp.class);
    @Autowired
    private UnqualifiedTestItemResultRecordMapper unqualifiedTestItemResultRecordMapper;

    /**
     * 添加一个不合格检测项目结果纪录
     *
     * @param unqualifiedTestItemResultRecord 不合格检测项目结果纪录
     */
    @Override
    public UnqualifiedTestItemResultRecord insert(UnqualifiedTestItemResultRecord unqualifiedTestItemResultRecord) {
        unqualifiedTestItemResultRecordMapper.insert(unqualifiedTestItemResultRecord);
        return unqualifiedTestItemResultRecord;
    }

    /**
     * 更新一个不合格检测项目结果纪录
     *
     * @param unqualifiedTestItemResultRecord 不合格检测项目结果纪录
     */
    @Override
    public UnqualifiedTestItemResultRecord update(UnqualifiedTestItemResultRecord unqualifiedTestItemResultRecord) {
        if (null == unqualifiedTestItemResultRecordMapper.getById(unqualifiedTestItemResultRecord.getId())) {
            logger.info("此id = " + unqualifiedTestItemResultRecord.getId() + "不存在，更新失败！");
            throw new JcExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXISTS);
        } else {
            unqualifiedTestItemResultRecordMapper.update(unqualifiedTestItemResultRecord);
        }
        return unqualifiedTestItemResultRecord;
    }

    /**
     * 根据ID删除不合格检测项目结果纪录
     *
     * @param id 不合格检测项目结果纪录ID
     */
    @Override
    public void deleteById(Integer id) {
        if (unqualifiedTestItemResultRecordMapper.getById(id) == null) {
            logger.info("此id = " + id + "不存在，删除失败！");
            throw new JcExceptions(EnumExceptions.DELETE_FAILED_NOT_EXISTS);
        } else {
            unqualifiedTestItemResultRecordMapper.deleteById(id);
        }

    }

    /**
     * 根据IDS删除不合格检测项目结果纪录
     *
     * @param ids 不合格检测项目结果纪录ID数组
     */
    @Override
    @Transactional
    public void deleteByIds(Integer[] ids) {
        unqualifiedTestItemResultRecordMapper.deleteByIds(ids);
    }

    /**
     * 根据ID查找不合格检测项目结果纪录
     *
     * @param id 不合格检测项目结果纪录ID
     * @return UnqualifiedTestItemResultRecord
     */
    @Override
    public UnqualifiedTestItemResultRecord getById(Integer id) {
        if (unqualifiedTestItemResultRecordMapper.getById(id) == null) {
            logger.info("此id = " + id + "不存在，查找失败！");
            throw new JcExceptions(EnumExceptions.DELETE_FAILED_NOT_EXISTS);
        } else {
            return unqualifiedTestItemResultRecordMapper.getById(id);
        }
    }

    /**
     * 查询所有的不合格检测项目结果纪录
     *
     * @param page
     * @param size
     * @param fieldName
     * @param orderType
     * @return List<UnqualifiedTestItemResultRecord>
     */
    @Override
    public PageInfo getAllByPage(Integer page, Integer size, String fieldName, String orderType) {
        String order = fieldName + " " + orderType;
        PageHelper.startPage(page, size, order);
        List<UnqualifiedTestItemResultRecord> unqualifiedTestItemResultRecordList = unqualifiedTestItemResultRecordMapper.getAllByPage();
        PageInfo<UnqualifiedTestItemResultRecord> pageInfo = new PageInfo<>(unqualifiedTestItemResultRecordList);
        return pageInfo;
    }

    /**
     * 批量插入
     *
     * @param unqualifiedTestItemResultRecords
     */
    @Override
    public void batchInsert(List<UnqualifiedTestItemResultRecord> unqualifiedTestItemResultRecords) {
        unqualifiedTestItemResultRecordMapper.batchInsert(unqualifiedTestItemResultRecords);
    }

    /**
     * 批量更新
     *
     * @param unqualifiedTestItemResultRecords
     */
    @Override
    public void batchUpdate(List<UnqualifiedTestItemResultRecord> unqualifiedTestItemResultRecords) {
        unqualifiedTestItemResultRecordMapper.batchUpdate(unqualifiedTestItemResultRecords);
    }
}
