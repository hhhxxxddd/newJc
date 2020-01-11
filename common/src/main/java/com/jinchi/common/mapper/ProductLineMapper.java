package com.jinchi.common.mapper;

import com.jinchi.common.domain.ProductLine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 说明:产品线接口映射类
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
@Mapper
@Component
public interface ProductLineMapper {
    /**
     * 添加一个新产品线
     *
     * @param productLine 产品线
     */
    void insert(ProductLine productLine);

    /**
     * 更新一个产品线
     *
     * @param productLine 产品线
     */
    void update(ProductLine productLine);

    /**
     * 根据ID删除产品线
     *
     * @param id 产品线ID
     */
    void deleteById(Integer id);

    /**
     * 根据IDS删除产品线
     *
     * @param ids 产品线ID数组
     */
    void deleteByIds(Integer[] ids);

    /**
     * 根据ID查找产品线
     *
     * @param id 产品线ID
     * @return ProductLine
     */
    ProductLine byId(Integer id);

    /**
     * 查询所有的产品线
     *
     * @return List<ProductLine>
     */
    List<ProductLine> getAll();

    /**
     * 通过产品线名称模糊查询
     *
     * @param productLineName 产品线名称
     * @return List<ProductLine>
     */
    List<ProductLine> getByNameLike(String productLineName);

}
