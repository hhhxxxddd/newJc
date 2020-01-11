package com.jinchi.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.ProductionProcess;
import com.jinchi.common.exceptions.EnumExceptions;
import com.jinchi.common.exceptions.JcExceptions;
import com.jinchi.common.mapper.ProductionProcessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionProcessServiceImp implements ProductionProcessService {
    @Autowired
    private ProductionProcessMapper productionProcessMapper;

    /**
     * 删除产品工序
     */
    @Override
    public void deleteOne(Integer id) {
        if (productionProcessMapper.findById(id) == null) {
            throw new JcExceptions(EnumExceptions.DELETE_FAILED_NOT_EXISTS);
        }
        productionProcessMapper.deleteById(id);
    }

    /**
     * 根据ids删除多个产品工序
     */
    @Override
    public void deleteMore(List<Integer> id) {
        productionProcessMapper.batchDeleteProductionProcess(id);
    }

    @Override
    public ProductionProcess getById(Integer id) {
        return productionProcessMapper.findById(id);
    }

    /**
     * 查询所有产品工序-分页
     */
    @Override
    public PageInfo findAllByPage(Integer page, Integer size, String fieldName, String orderType) {
        String order = fieldName + " " + orderType;
        PageHelper.startPage(page, size, order);
        List<ProductionProcess> productionProcesses = productionProcessMapper.findAll();
        PageInfo<ProductionProcess> pageInfo = new PageInfo<ProductionProcess>(productionProcesses);
        return pageInfo;
    }

    /**
     * 新增产品工序
     */
    @Override
    public ProductionProcess addOne(ProductionProcess productionProcess) {
        productionProcessMapper.insert(productionProcess);
        return productionProcess;
    }

    /**
     * 更新产品工序
     */
    @Override
    public ProductionProcess updateOne(ProductionProcess productionProcess) {
        ProductionProcess oldValue = productionProcessMapper.findById(productionProcess.getId());
        if (oldValue == null) {
            throw new JcExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXISTS);
        }
        if (productionProcess.getName() == null) {
            productionProcess.setName(oldValue.getName());
        }
        productionProcessMapper.update(productionProcess);
        return productionProcess;
    }

    /**
     * 根据产品工序名称模糊查询所有-分页
     */
    @Override
    public PageInfo findAllByName(String name, Integer page, Integer size, String fieldName, String orderType) {
        String order = fieldName + " " + orderType;
        PageHelper.startPage(page, size, order);
        List<ProductionProcess> productionProcesses = productionProcessMapper.findAllByName(name);
        PageInfo<ProductionProcess> pageInfo = new PageInfo<>(productionProcesses);
        return pageInfo;
    }

    /**
     * 查询所有
     */
    @Override
    public List<ProductionProcess> findAll() {
        return productionProcessMapper.findAll();
    }


}
