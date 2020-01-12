package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicMeasureUnit;

import java.util.List;
import java.util.Set;

/**
 * @author XudongHu
 * @apiNote 计量单位服务
 * @className ISwmsBasicMeasureUnitService
 * @modifier
 * @since 20.1.12日14:36
 */
public interface ISwmsBasicMeasureUnitService {

    /**
     * 新增
     * @param entity
     * @return
     */
    Boolean add(SwmsBasicMeasureUnit entity);

    /**
     * 自动新增
     * @param entity
     * @return
     */
    SwmsBasicMeasureUnit autoAdd(SwmsBasicMeasureUnit entity);

    /**
     * 更新
     * @param entity
     * @return
     */
    Boolean update(SwmsBasicMeasureUnit entity);

    /**
     * 查询所有
     * @param measureUnitDesc
     * @return
     */
    List<SwmsBasicMeasureUnit> getAll(String measureUnitDesc);

    /**
     * 查询所有-分页/名称模糊
     * @param page
     * @param measureUnitDesc
     * @return
     */
    IPage<SwmsBasicMeasureUnit> getAllByPage(Page page, String measureUnitDesc);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    Boolean delete(String id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Boolean batchDelete(Set<String> ids);
}
