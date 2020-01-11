package com.jinchi.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.ProductLine;
import com.jinchi.common.exceptions.EnumExceptions;
import com.jinchi.common.exceptions.JcExceptions;
import com.jinchi.common.mapper.ProductLineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 说明:产品线服务类的实现
 * <br>
 *
 * @author ZSCDumin
 * <br>
 * 邮箱: 2712220318@qq.com
 * <br>
 * 日期: 2018/11/16
 * <br>
 * 版本: 1.0
 */

@Service
public class ProductLineServiceImp implements ProductLineService {

    private static final Logger logger = LoggerFactory.getLogger(ProductLineServiceImp.class);
    @Autowired
    private ProductLineMapper productLineMapper;

    @Override
    public ProductLine add(ProductLine productLine) {
        productLineMapper.insert(productLine);
        return productLine;
    }

    @Override
    public ProductLine update(ProductLine productLine) {
        productLineMapper.update(productLine);
        return productLine;
    }

    @Override
    public ProductLine getById(Integer id) {
        if (productLineMapper.byId(id) == null) {
            logger.info("此id = " + id + "不存在，查找失败！");
            throw new JcExceptions(EnumExceptions.DELETE_FAILED_NOT_EXISTS);
        } else {
            return productLineMapper.byId(id);
        }
    }

    @Override
    @Transactional
    public void deleteByIds(Integer[] ids) {
        productLineMapper.deleteByIds(ids);
    }

    @Override
    public void deleteById(Integer id) {
        if (productLineMapper.byId(id) == null) {
            logger.info("此id = " + id + "不存在，删除失败！");
            throw new JcExceptions(EnumExceptions.DELETE_FAILED_NOT_EXISTS);
        } else {
            productLineMapper.deleteById(id);
        }
    }

    @Override
    public PageInfo getByNameLikeByPage(String productLineName, Integer page, Integer size, String fieldName, String orderType) {
        String order = fieldName + " " + orderType;
        PageHelper.startPage(page, size, order);
        List<ProductLine> productLines = productLineMapper.getByNameLike(productLineName);
        PageInfo<ProductLine> pageInfo = new PageInfo<>(productLines);
        return pageInfo;
    }

    @Override
    public PageInfo getAllByPage(Integer page, Integer size, String fieldName, String orderType) {
        String order = fieldName + " " + orderType;
        PageHelper.startPage(page, size, order);
        List<ProductLine> productLines = productLineMapper.getAll();
        PageInfo<ProductLine> pageInfo = new PageInfo<>(productLines);
        return pageInfo;
    }

    @Override
    public List<ProductLine> getAll() {
        return productLineMapper.getAll();
    }
}
