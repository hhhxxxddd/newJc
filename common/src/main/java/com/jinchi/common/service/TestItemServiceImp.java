package com.jinchi.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.TestItem;
import com.jinchi.common.exceptions.EnumExceptions;
import com.jinchi.common.exceptions.JcExceptions;
import com.jinchi.common.mapper.TestItemMapper;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/11/19.
 */
@Service
public class TestItemServiceImp implements TestItemService {
    private static final Logger logger = LoggerFactory.getLogger(ProductLineServiceImp.class);
    @Autowired
    private TestItemMapper testItemMapper;


    @Override
    public TestItem add(TestItem testItem) {
        Assert.isNull(testItemMapper.byName(testItem.getName()),"新增失败,"+testItem.getName()+"名称重复");
        testItemMapper.insert(testItem);
        return testItem;
    }

    @Override
    public TestItem update(TestItem testItem) {
        TestItem oldValue = testItemMapper.byName(testItem.getName());
        if(oldValue!=null) Assert.isTrue(oldValue.getId().equals(testItem.getId()),"更新失败,"+testItem.getName()+"名称重复");
        testItemMapper.update(testItem);
        return testItem;
    }

    @Override
    public TestItem getById(Integer id) {
        return testItemMapper.find(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids)
            deleteById(id);
    }

    @Override
    public void deleteById(Integer id) {
        if (testItemMapper.find(id) == null) {
            logger.info("此id = " + id + "不存在，查找失败！");
            throw new JcExceptions(EnumExceptions.DELETE_FAILED_NOT_EXISTS);
        } else {
            testItemMapper.delete(id);
        }
    }

    @Override
    public PageInfo getByNameLikeByPage(String testItemName, Integer page, Integer size, String fieldName, String orderType) {
        String order = fieldName + " " + orderType;
        PageHelper.startPage(page, size, order);
        List<TestItem> testItems = testItemMapper.findByName(testItemName);
        PageInfo<TestItem> pageInfo = new PageInfo<>(testItems);
        return pageInfo;
    }

    @Override
    public PageInfo getAllByPage(Integer page, Integer size, String fieldName, String orderType) {
        String order = fieldName + " " + orderType;
        PageHelper.startPage(page, size, order);
        List<TestItem> testItems = testItemMapper.findAll();
        PageInfo<TestItem> pageInfo = new PageInfo<>(testItems);
        return pageInfo;
    }

    @Override
    public List<TestItem> getAll() {
        return testItemMapper.findAll();
    }

}
