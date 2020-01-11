package com.jinchi.common.service;

import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.DeliveryFactory;

import java.util.List;

public interface DeliveryFactoryService {
    void deleteOne(Integer id);//根据id删除一个送货工厂

    void deleteMore(List<Integer> id); //根据ids删除多条数据

    DeliveryFactory getById(Integer id);//根据id查询送货工厂

    PageInfo findAllByPage(Integer page, Integer size, String fieldName, String orderType); //查询所有送货工厂-分页

    DeliveryFactory addOne(DeliveryFactory deliveryFactory);//增加一个送货工厂

    DeliveryFactory updateOne(DeliveryFactory deliveryFactory);//更新一个送货工厂

    PageInfo findAllByName(String name, Integer page, Integer size, String fieldName, String orderType);

    //根据送货工厂名称模糊查询所有-分页
    List<DeliveryFactory> findAll(); //查询所有送货工厂
}
