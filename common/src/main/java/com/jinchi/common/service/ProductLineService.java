package com.jinchi.common.service;

import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.ProductLine;

import java.util.List;

/**
 * 说明: 产品线服务类
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
public interface ProductLineService {

    /**
     * 添加新产品线
     *
     * @param productLine 产品线对象
     * @return ProductLine
     */
    ProductLine add(ProductLine productLine);

    /**
     * 更新产品线
     *
     * @param productLine 产品线对象
     * @return ProductLine
     */
    ProductLine update(ProductLine productLine);

    /**
     * 查找产品线
     *
     * @param id 产品线id
     * @return ProductLine
     */
    ProductLine getById(Integer id);

    /**
     * 根据ids删除产品线
     *
     * @param ids 产品线数组
     */
    void deleteByIds(Integer[] ids);

    /**
     * 根据ID删除产品线
     *
     * @param id 产品线id
     */
    void deleteById(Integer id);

    /**
     * 根据产品线名称模糊查询所有产品线-分页
     *
     * @param productLineName 产品线名
     * @param page            第几页
     * @param size            每页的数目
     * @param fieldName       字段名
     * @param orderType       排序类型
     * @return PageInfo
     */
    PageInfo getByNameLikeByPage(String productLineName, Integer page, Integer size, String fieldName, String orderType);

    /**
     * 查询所有产品线-分页
     *
     * @param page      第几页
     * @param size      每页的数目
     * @param fieldName 字段名
     * @param orderType 排序类型
     * @return PageInfo
     */
    PageInfo getAllByPage(Integer page, Integer size, String fieldName, String orderType);

    /**
     * 查询所有的产品线
     *
     * @return List
     */
    List<ProductLine> getAll();
}
