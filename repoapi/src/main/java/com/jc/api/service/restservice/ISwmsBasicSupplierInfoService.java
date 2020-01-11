package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicSupplierInfo;

import java.util.List;
import java.util.Set;

/**
 * @author XudongHu
 * @apiNote 物料供应商接口
 * @className ISwmsBasicSupplierInfoService
 * @modifier
 * @since 20.1.11日12:57
 */
public interface ISwmsBasicSupplierInfoService {
    /**
     * 新增
     * @param entity 物料供应商实体
     * @return boolean
     */
    Boolean add(SwmsBasicSupplierInfo entity);

    /**
     * 自动新增
     * 根据供应商代号查询,查到?返回:新增
     * @param entity 物料供应商实体
     * @return 物料供应商实体
     */
    SwmsBasicSupplierInfo autoAdd(SwmsBasicSupplierInfo entity);

    /**
     * 更新
     * @param entity 物料供应商实体
     * @return boolean
     */
    Boolean update(SwmsBasicSupplierInfo entity);

    /**
     * 条件查询
     * @param entity
     * @return list
     */
    List<SwmsBasicSupplierInfo> getAll(SwmsBasicSupplierInfo entity);

    /**
     * 条件查询-分页
     * @param page 分页参数
     * @param entity 物料供应商参数
     * @return page
     */
    IPage<SwmsBasicSupplierInfo> getAllByPage(Page page, SwmsBasicSupplierInfo entity);

    /**
     * 删除
     * @param id 主键
     * @return boolean
     */
    Boolean delete(Integer id);

    /**
     * 批量删除
     * @param ids 主键数组
     * @return
     */
    Boolean batchDelete(Set<String> ids);
}
