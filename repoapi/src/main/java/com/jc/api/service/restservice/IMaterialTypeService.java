package com.jc.api.service.restservice;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.po.MaterialType;

import java.util.List;
import java.util.Set;

/**
 * @author XudongHu
 * @className MaterialTypeService
 * @apiNote 物料类型service
 * @modifer
 * @since 2019/10/30日16:18
 */
public interface IMaterialTypeService {
    /**
     * 查询父类型链
     * 父类型=>子类型 顺序排列
     * @param id 子类型主键
     * @return list
     */
    List<MaterialType> getCompleteType(Integer id);

    /**
     * 查询所有类型:树形
     * @return jsonArray
     */
    JSONArray getAllTypeTree();

    /**
     * 查询目标id所有子类型:树形
     * @param id 主键
     * @return jsonArray
     */
    JSONArray getTypeTree(Integer id);

    /**
     * 查询子类型
     * @param id  主键
     * @return list
     */
    Set<String> getAllChildren(Integer id);
    /**
     * 条件查询所有-分页
     * @return page
     */
    IPage<MaterialType> getAllByPage(Page page,MaterialType materialType);

    /**
     * 条件查询所有
     * @return list
     */
    List<MaterialType> getAll(MaterialType materialType);

    /**
     * 新增 (逻辑新增)
     * @param materialType 物料类型po
     * @return boolean
     */
    Boolean add(MaterialType materialType);

    /**
     * 更新
     * @param materialType 物料类型po
     * @return boolean
     */
    Boolean update(MaterialType materialType);

    /**
     * 自动新增(根据类型代号名称链逻辑保存)
     * @param typeCodeList 类型全称
     * @return 物料类型po List
     */
    List<MaterialType> autoAdd(String typeCodeList);

    /**
     * 根据主键删除
     * @param id 主键
     * @return
     */
    Boolean delete(Integer id);
}
