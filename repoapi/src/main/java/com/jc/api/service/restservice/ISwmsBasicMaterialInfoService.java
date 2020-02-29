package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicMaterialInfo;

import java.util.List;
import java.util.Set;

/**
 * @author XudongHu
 * @apiNote 物料信息服务
 * @className ISwmsBasicMaterialInfoService
 * @modifier
 * @since 20.1.12日2:53
 */
public interface ISwmsBasicMaterialInfoService {
    /**
     * 新增
     * @param entity
     * @return
     */
    Boolean add(SwmsBasicMaterialInfo entity,String[] supIds);

    /**
     * 自动新增
     * @param entity
     * @return
     */
    SwmsBasicMaterialInfo autoAdd(SwmsBasicMaterialInfo entity);

    /**
     * 更新
     * @param entity
     * @return
     */
    Boolean update(SwmsBasicMaterialInfo entity,String[] supIds);

    /**
     * 查询所有-名称模糊
     * @param materialName
     * @return
     */
    List<SwmsBasicMaterialInfo> getAll(String materialNamezz);

    /**
     * 查询所有-名称模糊-分页
     * @param page
     * @param materialName
     * @return
     */
    IPage<SwmsBasicMaterialInfo> getAllByPage(Page page, String materialName);

    /**
     * 删除
     * @param id 主键
     * @return
     */
    Boolean delete(String id);

    /**
     * 批量删除
     * @param ids 主键数组
     * @return
     */
    Boolean batchDelete(Set<String> ids);

    /**
     * 根据物料大类查找
     * @param type
     * @return
     */
    List getByType(Integer type);
}
