package com.jinchi.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.DeliveryFactory;
import com.jinchi.common.exceptions.EnumExceptions;
import com.jinchi.common.exceptions.JcExceptions;
import com.jinchi.common.mapper.DeliveryFactoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryFactoryServiceImp implements DeliveryFactoryService {
    @Autowired
    private DeliveryFactoryMapper deliveryFactoryMapper;

    /**
     * 删除送货工厂
     */
    @Override
    public void deleteOne(Integer id) {
        if (deliveryFactoryMapper.findById(id) == null) {
            throw new JcExceptions(EnumExceptions.DELETE_FAILED_NOT_EXISTS);
        }
        deliveryFactoryMapper.deleteById(id);
    }

    /**
     * 根据ids删除多个送货工厂
     */
    @Override
    public void deleteMore(List<Integer> id) {
        deliveryFactoryMapper.batchDeleteDeliveryFactory(id);
    }

    @Override
    public DeliveryFactory getById(Integer id) {
        return deliveryFactoryMapper.findById(id);
    }

    /**
     * 查询所有送货工厂-分页
     */
    @Override
    public PageInfo findAllByPage(Integer page, Integer size, String fieldName, String orderType) {
        String order = fieldName + " " + orderType;
        PageHelper.startPage(page, size, order);
        List<DeliveryFactory> deliveryFactories = deliveryFactoryMapper.findAll();
        PageInfo<DeliveryFactory> pageInfo = new PageInfo<DeliveryFactory>(deliveryFactories);
        return pageInfo;
    }

    /**
     * 新增送货工厂
     */
    @Override
    public DeliveryFactory addOne(DeliveryFactory deliveryFactory) {
        deliveryFactoryMapper.insert(deliveryFactory);
        return deliveryFactory;
    }

    /**
     * 更新送货工厂
     */
    @Override
    public DeliveryFactory updateOne(DeliveryFactory deliveryFactory) {
        DeliveryFactory oldValue = deliveryFactoryMapper.findById(deliveryFactory.getId());
        if (oldValue == null) {
            throw new JcExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXISTS);
        }
        if (deliveryFactory.getName() == null) {
            deliveryFactory.setName(oldValue.getName());
        }
        deliveryFactoryMapper.update(deliveryFactory);
        return deliveryFactory;
    }

    /**
     * 根据送货工厂名称模糊查询所有-分页
     */
    @Override
    public PageInfo findAllByName(String name, Integer page, Integer size, String fieldName, String orderType) {
        String order = fieldName + " " + orderType;
        PageHelper.startPage(page, size, order);
        List<DeliveryFactory> deliveryFactories = deliveryFactoryMapper.findAllByName(name);
        PageInfo<DeliveryFactory> pageInfo = new PageInfo<>(deliveryFactories);
        return pageInfo;
    }

    /**
     * 查询所有
     */
    @Override
    public List<DeliveryFactory> findAll() {
        return deliveryFactoryMapper.findAll();
    }
}
