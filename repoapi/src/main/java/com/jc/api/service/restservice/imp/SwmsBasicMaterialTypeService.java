package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicMaterialType;
import com.jc.api.exception.custom.DataAssociationException;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.mapper.SwmsBasicMaterialTypeMapper;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: River
 * @Date: 2020/1/11 17:22
 * @Description:    物料类型Service实现层
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class SwmsBasicMaterialTypeService implements SwmsBasicMaterialTypeService {

    @Auto
    private SwmsBasicMaterialTypeMapper swmsBasicMaterialTypeMapper;

    /**
     * @Description:    物料类型-不分页
     * @Author: River
     * @Date: 2020/1/11 17:27
     {@link List< SwmsBasicMaterialType>}
     java.util.List<com.jc.api.entity.SwmsBasicMaterialType>
     **/
    @Override
    public List<SwmsBasicMaterialType> getAll(SwmsBasicMaterialType swmsBasicMaterialType) {
        QueryWrapper<SwmsBasicMaterialType> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(swmsBasicMaterialType.getTypeCode())) {
            queryWrapper.likeRight("type_code", swmsBasicMaterialType.getTypeCode());
        }
        if (StringUtils.isNotEmpty(swmsBasicMaterialType.getTypeName())) {
            queryWrapper.likeRight("type_name", swmsBasicMaterialType.getTypeName());
        }
        if (swmsBasicMaterialType.getAutoFlag() != null) {
            queryWrapper.eq("auto_flag", swmsBasicMaterialType.getAutoFlag());
        }
        return swmsBasicMaterialTypeMapper.selectList(queryWrapper);
    }

    /**
     * @Description:    物料类型-分页
     * @Author: River
     * @Date: 2020/1/11 17:27
     {@link IPage< SwmsBasicMaterialType>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicMaterialType>
     **/
    @Override
    public IPage<SwmsBasicMaterialType> getAllByPage(Page page, SwmsBasicMaterialType swmsBasicMaterialType) {
        QueryWrapper<SwmsBasicMaterialType> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(swmsBasicMaterialType.getTypeCode())) {
            queryWrapper.likeRight("type_code", swmsBasicMaterialType.getTypeCode());
        }
        if (StringUtils.isNotEmpty(swmsBasicMaterialType.getTypeName())) {
            queryWrapper.likeRight("type_name", swmsBasicMaterialType.getTypeName());
        }
        if (swmsBasicMaterialType.getAutoFlag() != null) {
            queryWrapper.eq("auto_flag", swmsBasicMaterialType.getAutoFlag());
        }
        return swmsBasicMaterialTypeMapper.selectPage(page, queryWrapper);
    }
    
    /**
     * @Description:    物料类型-新增
     * @Author: River 
     * @Date: 2020/1/11 17:28
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean add(SwmsBasicMaterialType swmsBasicMaterialType) {
        QueryWrapper<SwmsBasicMaterialType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_code", swmsBasicMaterialType.getTypeCode());
        List<SwmsBasicMaterialType> queryByCode = swmsBasicMaterialTypeMapper.selectList(queryWrapper);
        if (queryByCode.size() > 0) {
            throw new DataDuplicateException("新增失败,类型代号同名:" + swmsBasicMaterialType.getTypeCode());
        }
        return swmsBasicMaterialTypeMapper.insert(swmsBasicMaterialType) > 0;
    }

    /**
     * @Description:    根据传入的代号查询类型，无此数据则新增
     * @Author: River
     * @Date: 2020/1/11 17:30
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public SwmsBasicMaterialType autoAdd(SwmsBasicMaterialType swmsBasicMaterialType) {
        QueryWrapper<SwmsBasicMaterialType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_code", swmsBasicMaterialType.getTypeCode());
        SwmsBasicMaterialType oldMaterialType = swmsBasicMaterialTypeMapper.selectOne(queryWrapper);
        if (oldMaterialType != null) {
            return oldMaterialType;
        }
        log.info("发现新物料类型,开始新增========>:");
        if (StringUtils.isEmpty(swmsBasicMaterialType.getTypeCode())) {
            throw new DataDuplicateException("物料类型代号缺失");
        }
        swmsBasicMaterialType.setAutoFlag(true);
        swmsBasicMaterialType.setTypeName("未知类型名称");
        swmsBasicMaterialTypeMapper.insert(swmsBasicMaterialType);
        log.info("物料类型新增结束=========>:{}", swmsBasicMaterialType.toString());
        return swmsBasicMaterialType;
    }

    /**
     * @Description:    物料类型-更新
     * @Author: River
     * @Date: 2020/1/11 17:34
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean update(SwmsBasicMaterialType swmsBasicMaterialType) {
        SwmsBasicMaterialType queryById = swmsBasicMaterialTypeMapper.selectById(swmsBasicMaterialType.getId());
        if (queryById == null) {
            throw new DataDuplicateException("更新失败,不存在的物料类型:" + swmsBasicMaterialType.getId());
        }
        if (StringUtils.isNotEmpty(swmsBasicMaterialType.getTypeCode()) && !queryById.getTypeCode().equals(swmsBasicMaterialType.getTypeCode())) {
            QueryWrapper<SwmsBasicMaterialType> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("type_code", swmsBasicMaterialType.getTypeCode());
            List<SwmsBasicMaterialType> queryByCode = swmsBasicMaterialTypeMapper.selectList(queryWrapper);
            if (queryByCode.size() > 0) {
                throw new DataDuplicateException("更新失败,物料类型代号同名:" + swmsBasicMaterialType.getTypeCode());
            }
        }
        return swmsBasicMaterialTypeMapper.updateById(swmsBasicMaterialType) > 0;
    }

    /**
     * @Description:    物料类型：删除
     * @Author: River
     * @Date: 2020/1/11 17:42
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean delete(Integer id) {
        try {
            return swmsBasicMaterialTypeMapper.deleteById(id) > 0;
        } catch (Exception e) {
            throw new DataAssociationException(String.format("删除失败,该数据正在被使用:%d,如要删除请选择强力删除,将删除包括关联的台账,出库信息", id));
        }
    }
}
