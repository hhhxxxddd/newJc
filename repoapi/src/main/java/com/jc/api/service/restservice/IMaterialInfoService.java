package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.param.MaterialInfoQueryParam;
import com.jc.api.entity.po.MaterialInfo;
import com.jc.api.entity.vo.MaterialInfoJointVo;

import java.util.List;

/**
 * @author XudongHu
 * @apiNote 物料信息service
 * @className MaterialInfoService
 * @modifer
 * @since 2019/10/31日2:54
 */
@Deprecated
public interface IMaterialInfoService {

     MaterialInfoJointVo getById(String materialInfoId);
    /**
     * 查询所有物料信息vo
     *
     * @param materialInfoQueryParam 物料信息查询参数
     * @return list
     */
    List<MaterialInfoJointVo> getAllVo(MaterialInfoQueryParam materialInfoQueryParam);

    /**
     * 查询所有物料信息vo
     *
     * @param materialInfoQueryParam 物料信息查询参数
     * @return Page
     */
    IPage<MaterialInfoJointVo> getAllVoByPage(Page page, MaterialInfoQueryParam materialInfoQueryParam);

    /**
     * 自动新增物料信息
     * @param materialInfo 物料信息po
     * @return 物料信息po
     */
    MaterialInfo autoAdd(MaterialInfo materialInfo);
    /**
     * 新增物料信息
     *
     * @param materialInfo 物料信息
     * @return 物料信息表单
     */
    Boolean add(MaterialInfo materialInfo);

    /**
     * 更新物料信息
     * @param materialInfo 物料信息
     * @return
     */
    Boolean update(MaterialInfo materialInfo);

    /**
     * 根据主键删除
     *
     * @param id    主键
     * @return boolean
     */
    Boolean delete(Integer id);
}
