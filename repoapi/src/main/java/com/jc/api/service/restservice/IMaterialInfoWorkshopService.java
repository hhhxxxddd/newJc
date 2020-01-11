package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.param.MaterialInfoWorkshopQueryParam;
import com.jc.api.entity.po.MaterialInfoWorkshop;

import java.util.List;

/**
 * @author XudongHu
 * @apiNote 物料供货车间service
 * @className IMaterialInfoWorkshopService
 * @modifier
 * @since 19.12.9日3:00
 */
@Deprecated
public interface IMaterialInfoWorkshopService {
    /**
     * 新增
     * @param materialInfoWorkshop 物料供应车间po
     * @return boolean
     */
    Boolean add(MaterialInfoWorkshop materialInfoWorkshop);

    /**
     * 自动新增
     * 根据供应商代号查询,查到?返回:新增
     * @param materialInfoWorkshop 物料供应车间po
     * @return 物料供应车间po
     */
    MaterialInfoWorkshop autoAdd(MaterialInfoWorkshop materialInfoWorkshop);

    /**
     * 更新
     * @param materialInfoWorkshop 物料供应车间po
     * @return boolean
     */
    Boolean update(MaterialInfoWorkshop materialInfoWorkshop);

    /**
     * 条件查询
     * @param materialInfoWorkshopQueryParam 物料供应车间参数
     * @return list
     */
    List<MaterialInfoWorkshop> getAll(MaterialInfoWorkshopQueryParam materialInfoWorkshopQueryParam);

    /**
     * 条件查询-分页
     * @param page 分页参数
     * @param materialInfoWorkshopQueryParam 物料供应车间参数
     * @return page
     */
    IPage<MaterialInfoWorkshop> getAllByPage(Page page, MaterialInfoWorkshopQueryParam materialInfoWorkshopQueryParam);

    /**
     * 删除
     * @param id 主键
     * @return boolean
     */
    Boolean delete(Integer id);
}
