package com.jinchi.common.mapper;

import com.jinchi.common.domain.RawTestItemStandard;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: TODO
 * @date 2018/12/16 11:16
 */
@Mapper
@Component
public interface RawTestItemStandardMapper {

    /**
     * 添加一个新检测项标准记录
     *
     * @param rawTestItemStandard 检测项标准记录
     */
    void insert(RawTestItemStandard rawTestItemStandard);

    /**
     * 更新一个检测项标准记录
     *
     * @param rawTestItemStandard 检测项标准记录
     */
    void update(RawTestItemStandard rawTestItemStandard);

    /**
     * 根据ID删除检测项标准记录
     *
     * @param id 检测项标准记录ID
     */
    void deleteById(Integer id);

    /**
     * 根据IDS删除检测项标准记录
     *
     * @param ids 检测项标准记录ID数组
     */
    void deleteByIds(Integer[] ids);

    /**
     * 根据ID查找检测项标准记录
     *
     * @param id 检测项标准记录ID
     * @return RawTestItemStandard
     */
    RawTestItemStandard getById(Integer id);

    /**
     * 根据ID查找检测项标准记录
     *
     * @param testItemId 检测项标准记录ID
     * @return RawTestItemStandard
     */
    List<RawTestItemStandard> getByTestItemId(Integer testItemId);

    /**
     * 查询所有的检测项标准记录
     *
     * @return List<RawTestItemStandard>
     */
    List<RawTestItemStandard> getAll();
}
