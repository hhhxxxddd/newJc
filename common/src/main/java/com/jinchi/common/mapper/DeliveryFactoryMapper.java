package com.jinchi.common.mapper;


import com.jinchi.common.domain.DeliveryFactory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DeliveryFactoryMapper {
    void deleteById(Integer id); //根据id删除一个送货工厂

    DeliveryFactory findById(Integer id);//根据id查询送货工厂

    List<DeliveryFactory> findAll();//查询所有送货工厂

    void insert(DeliveryFactory deliveryFactory);//增加一个送货工厂

    void update(DeliveryFactory deliveryFactory);//更新一个送货工厂

    List<DeliveryFactory> findAllByName(@Param(value = "name") String name); //根据送货工厂名称模糊查询所有

    void batchDeleteDeliveryFactory(List<Integer> id); //根据ids删除多条数据

    DeliveryFactory byFullName(@Param("name") String name);
}
