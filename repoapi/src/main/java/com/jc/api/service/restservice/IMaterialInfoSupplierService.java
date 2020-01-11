package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.param.MaterialInfoSupplierQueryParam;
import com.jc.api.entity.po.MaterialInfoSupplier;

import java.util.List;

/**
 * @author XudongHu
 * @apiNote 物料供应商service
 * @className IMaterialInfoSupplierService
 * @modifier
 * @since 19.12.8日1:50
 */
public interface IMaterialInfoSupplierService {
    /**
     * 新增
     * @param materialInfoSupplier 物料供应商po
     * @return boolean
     */
    Boolean add(MaterialInfoSupplier materialInfoSupplier);

    /**
     * 自动新增
     * 根据供应商代号查询,查到?返回:新增
     * @param materialInfoSupplier 物料供应商po
     * @return 物料供应商po
     */
    MaterialInfoSupplier autoAdd(MaterialInfoSupplier materialInfoSupplier);

    /**
     * 更新
     * @param materialInfoSupplier 物料供应商po
     * @return boolean
     */
    Boolean update(MaterialInfoSupplier materialInfoSupplier);

    /**
     * 条件查询
     * @param materialInfoSupplierQueryParam 物料供应商参数
     * @return list
     */
    List<MaterialInfoSupplier> getAll(MaterialInfoSupplierQueryParam materialInfoSupplierQueryParam);

    /**
     * 条件查询-分页
     * @param page 分页参数
     * @param supplierParam 物料供应商参数
     * @return page
     */
    IPage<MaterialInfoSupplier> getAllByPage(Page page, MaterialInfoSupplierQueryParam supplierParam);

    /**
     * 删除
     * @param id 主键
     * @return boolean
     */
    Boolean delete(Integer id);
}
