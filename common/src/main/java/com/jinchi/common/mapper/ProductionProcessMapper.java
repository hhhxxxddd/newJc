package com.jinchi.common.mapper;

import com.jinchi.common.domain.ProductionProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProductionProcessMapper {
    void deleteById(Integer id); //根据id删除一个产品工序

    ProductionProcess findById(Integer id);//根据id查询产品工序

    List<ProductionProcess> findAll();//查询所有产品工序

    void insert(ProductionProcess productionProcess);//增加一个产品工序

    void update(ProductionProcess productionProcess);//更新一个产品工序

    List<ProductionProcess> findAllByName(@Param(value = "name") String name); //根据产品工序名称模糊查询所有

    void batchDeleteProductionProcess(List<Integer> id); //根据ids删除多条数据
}
