package com.jinchi.common.mapper;

import com.jinchi.common.domain.TestItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2018/11/19.
 */
@Mapper
@Component
public interface TestItemMapper {
    /**
     * 新增一个检测项目
     *
     * @param testItem
     */
    void insert(TestItem testItem);

    /**
     * 删除一个检测项目
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据id查询
     *
     * @param id
     */
    TestItem find(@Param("id") Integer id);

    List<TestItem> byIds(List<Integer> ids);
    /**
     * 更新一个检测项目
     *
     * @param testItem
     */
    void update(TestItem testItem);

    /**
     * 根据名称查询检测项目
     *
     * @param name
     * @return
     */
    List<TestItem> findByName(String name);

    /**
     * 查询所有
     */
    List<TestItem> findAll();

    TestItem byName(@Param("name") String name);
}
