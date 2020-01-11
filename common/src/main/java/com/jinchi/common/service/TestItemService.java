package com.jinchi.common.service;

import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.TestItem;

import java.util.List;

/**
 * Created by Administrator on 2018/11/19.
 */
public interface TestItemService {
    /**
     * 添加新检测对象
     *
     * @param testItem 检测对象
     * @return testItem
     */
    TestItem add(TestItem testItem);

    /**
     * 更新检测项目
     *
     * @param testItem 检测对象
     * @return testItem
     */
    TestItem update(TestItem testItem);

    /**
     * 查找检测项目
     *
     * @param id 检测项目id
     * @return TestItem
     */
    TestItem getById(Integer id);

    /**
     * 根据ids删除项目
     *
     * @param ids 检测项目数组
     */
    void deleteByIds(Integer[] ids);

    /**
     * 根据ID删除检测项目
     *
     * @param id 检测项目id
     */
    void deleteById(Integer id);

    /**
     * 根据检测项目名称模糊查询所有产品线-分页
     *
     * @param testItemName 检测项目名
     * @param page         第几页
     * @param size         每页的数目
     * @param fieldName    字段名
     * @param orderType    排序类型
     * @return PageInfo
     */
    PageInfo getByNameLikeByPage(String testItemName, Integer page, Integer size, String fieldName, String orderType);

    /**
     * 查询所有检测项目-分页
     *
     * @param page      第几页
     * @param size      每页的数目
     * @param fieldName 字段名
     * @param orderType 排序类型
     * @return PageInfo
     */
    PageInfo getAllByPage(Integer page, Integer size, String fieldName, String orderType);

    /**
     * 查询所有的检测项目
     *
     * @return List
     */
    List<TestItem> getAll();
}
