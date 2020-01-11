package com.jinchi.common.service;


import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.ProductionProcess;

import java.util.List;

public interface ProductionProcessService {
    void deleteOne(Integer id);//根据id删除一个产品工序

    void deleteMore(List<Integer> id); //根据ids删除多条数据

    ProductionProcess getById(Integer id);//根据id查询产品工序

    PageInfo findAllByPage(Integer page, Integer size, String fieldName, String orderType); //查询所有产品工序-分页

    ProductionProcess addOne(ProductionProcess productionProcess);//增加一个产品工序

    ProductionProcess updateOne(ProductionProcess productionProcess);//更新一个产品工序

    PageInfo findAllByName(String name, Integer page, Integer size, String fieldName, String orderType);

    //根据产品工序名称模糊查询所有-分页
    List<ProductionProcess> findAll(); //查询所有产品工序
}
