package com.jc.api.modules.base.auto.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.modules.base.auto.entity.param.BasicSupplierInfoQueryParam;
import com.jc.api.modules.base.auto.entity.BasicSupplierInfo;

import java.util.List;
import java.util.Set;

/**
 * @author XudongHu
 * @apiNote 物料供应商接口
 * @className IBasicSupplierInfoService
 * @modifier
 * @since 20.1.9日14:23
 */
public interface IBasicSupplierInfoService {
    /**
     * 新增
     * @param basicSupplierInfo po
     * @return boolean
     */
    Boolean add(BasicSupplierInfo basicSupplierInfo);

    /**
     * 自动新增
     * @param basicSupplierInfo po
     * @return boolean
     */
    BasicSupplierInfo autoAdd(BasicSupplierInfo basicSupplierInfo);

    /**
     * 删除
     * @param id 主键
     * @return boolean
     */
    Boolean delete(String id);

    /**
     * 批量删除
     * @param ids 主键集合
     * @return boolean
     */
    Boolean batchDelete(Set<String> ids);

    /**
     * 更新
     * @param basicSupplierInfo po
     * @return boolean
     */
    Boolean update(BasicSupplierInfo basicSupplierInfo);

    /**
     * 查询所有
     * @param basicSupplierInfoQueryParam 查询参数
     * @return list
     */
    List<BasicSupplierInfo> getAll(BasicSupplierInfoQueryParam basicSupplierInfoQueryParam);

    /**
     * 查询所有-分页
     * @param page 分页参数
     * @param basicSupplierInfoQueryParam 查询参数
     * @return page
     */
    Page<BasicSupplierInfo> getAllByPage(Page page, BasicSupplierInfoQueryParam basicSupplierInfoQueryParam);
}
