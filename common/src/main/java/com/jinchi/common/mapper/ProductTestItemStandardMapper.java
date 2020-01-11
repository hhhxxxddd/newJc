package com.jinchi.common.mapper;

import com.jinchi.common.domain.ProductTestItemStandard;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: TODO
 * @date 2018/12/17 17:37
 */
@Mapper
@Component
public interface ProductTestItemStandardMapper {
    /**
     * 添加新产品检测标准
     *
     * @param productTestItemStandard 产品检测标准对象
     * @return ProductTestItemStandard
     */
    ProductTestItemStandard add(ProductTestItemStandard productTestItemStandard);

    /**
     * 更新产品检测标准
     *
     * @param productTestItemStandard 产品检测标准对象
     * @return ProductTestItemStandard
     */
    ProductTestItemStandard update(ProductTestItemStandard productTestItemStandard);

    /**
     * 查找产品检测标准
     *
     * @param id 产品检测标准id
     * @return ProductTestItemStandard
     */
    ProductTestItemStandard getById(Integer id);

    /**
     * @param testItemId
     * @return
     */
    List<ProductTestItemStandard> getByTestItemId(Integer testItemId);

    /**
     * 根据ids删除产品检测标准
     *
     * @param ids 产品检测标准数组
     */
    void deleteByIds(Integer[] ids);

    /**
     * 根据ID删除产品检测标准
     *
     * @param id 产品检测标准id
     */
    void deleteById(Integer id);

    /**
     * 查询所有产品检测标准-分页
     *
     * @return PageInfo
     */
    List<ProductTestItemStandard> getAll();
}
