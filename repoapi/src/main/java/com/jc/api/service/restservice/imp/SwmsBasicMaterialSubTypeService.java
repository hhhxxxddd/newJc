package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicMaterialSubType;
import com.jc.api.entity.SwmsBasicMaterialType;
import com.jc.api.exception.custom.DataAssociationException;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.mapper.SwmsBasicMaterialSubTypeMapper;
import com.jc.api.mapper.SwmsBasicMaterialTypeMapper;
import com.jc.api.service.restservice.ISwmsBasicMaterialSubTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/11 17:56
 * @Description:    物料子类型Service实现层
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class SwmsBasicMaterialSubTypeService implements ISwmsBasicMaterialSubTypeService {

    @Autowired
    private SwmsBasicMaterialSubTypeMapper swmsBasicMaterialSubTypeMapper;

    @Autowired
    private SwmsBasicMaterialTypeMapper swmsBasicMaterialTypeMapper;

    /**
     * @Description:    物料子类型-不分页
     * @Author: River
     * @Date: 2020/1/11 18:00
     {@link List< SwmsBasicMaterialSubType>}
     java.util.List<com.jc.api.entity.SwmsBasicMaterialSubType>
     **/
    @Override
    public List<SwmsBasicMaterialSubType> getAll(String subTypeName) {
        return swmsBasicMaterialSubTypeMapper.findList(subTypeName);
    }

    /**
     * @Description:    物料子类型-分页
     * @Author: River
     * @Date: 2020/1/12 14:54
     {@link IPage< SwmsBasicMaterialSubType>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicMaterialSubType>
     **/
    @Override
    public IPage<SwmsBasicMaterialSubType> getAllByPage(Page<SwmsBasicMaterialSubType> page, String subTypeName) {
        return swmsBasicMaterialSubTypeMapper.selectPageVo(page, subTypeName);
    }

    /**
     * @Description:    物料子类型-新增
     * @Author: River
     * @Date: 2020/1/11 20:03
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean add(SwmsBasicMaterialSubType swmsBasicMaterialSubType) {
        QueryWrapper<SwmsBasicMaterialSubType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sub_type_code", swmsBasicMaterialSubType.getSubTypeCode());
        List<SwmsBasicMaterialSubType> queryByCode = swmsBasicMaterialSubTypeMapper.selectList(queryWrapper);
        if (queryByCode.size() > 0) {
            throw new DataDuplicateException("新增失败,类型代号同名:" + swmsBasicMaterialSubType.getSubTypeCode());
        }

        if (swmsBasicMaterialSubType.getTypeId() == null) {
            throw new DataDuplicateException("物料类型未选择");
        }
        SwmsBasicMaterialType typeQueryById = swmsBasicMaterialTypeMapper.selectById(swmsBasicMaterialSubType.getTypeId());
        if (typeQueryById == null) {
            throw new DataDuplicateException("新增失败，父类型不存在：" + swmsBasicMaterialSubType.getTypeId());
        }
        return swmsBasicMaterialSubTypeMapper.insert(swmsBasicMaterialSubType) > 0;
    }

    /**
     * @Description:    物料子类型自动新增
     * @Author: River
     * @Date: 2020/1/11 20:04
     {@link SwmsBasicMaterialSubType}
     com.jc.api.entity.SwmsBasicMaterialSubType
     **/
    @Override
    public SwmsBasicMaterialSubType autoAdd(SwmsBasicMaterialSubType swmsBasicMaterialSubType) {
        if (StringUtils.isEmpty(swmsBasicMaterialSubType.getSubTypeCode())) {
            throw new DataDuplicateException("物料子类型代号不存在");
        }
        QueryWrapper<SwmsBasicMaterialSubType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sub_type_code", swmsBasicMaterialSubType.getSubTypeCode());
        SwmsBasicMaterialSubType queryByCode = swmsBasicMaterialSubTypeMapper.selectOne(queryWrapper);
        if (queryByCode != null) {
            return queryByCode;
        }
        log.info("发现新物料子类型,开始新增========>:");
        if (swmsBasicMaterialSubType.getTypeId() == null) {
            throw new DataDuplicateException("物料类型未选择");
        }
        SwmsBasicMaterialType typeQueryById = swmsBasicMaterialTypeMapper.selectById(swmsBasicMaterialSubType.getTypeId());
        if (typeQueryById == null) {
            throw new DataDuplicateException("物料类型不存在");
        }
        swmsBasicMaterialSubType.setAutoFlag(true);
        swmsBasicMaterialSubType.setSubTypeName("未知子类型名称");
        swmsBasicMaterialSubTypeMapper.insert(swmsBasicMaterialSubType);
        log.info("物料子类型新增结束=========>:{}", swmsBasicMaterialSubType.toString());
        return swmsBasicMaterialSubType;
    }

    /**
     * @Description:    物料子类型-更新
     * @Author: River
     * @Date: 2020/1/11 20:18
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean update(SwmsBasicMaterialSubType swmsBasicMaterialSubType) {
        SwmsBasicMaterialSubType querySubTypeById = swmsBasicMaterialSubTypeMapper.selectById(swmsBasicMaterialSubType.getId());
        if (querySubTypeById == null) {
            throw new DataDuplicateException("更新失败,不存在的物料子类型:" + swmsBasicMaterialSubType.getId());
        }
        if (swmsBasicMaterialSubType.getTypeId() == null) {
            throw new DataDuplicateException("更新失败，未选择物料类型" + swmsBasicMaterialSubType.getId());
        }
        SwmsBasicMaterialType queryTypeById = swmsBasicMaterialTypeMapper.selectById(swmsBasicMaterialSubType.getTypeId());
        if (querySubTypeById == null) {
            throw new DataDuplicateException("更新失败，不存在的物料类型：" + swmsBasicMaterialSubType.getTypeId());
        }
        if (StringUtils.isNotEmpty(swmsBasicMaterialSubType.getSubTypeCode()) && !querySubTypeById.getSubTypeCode().equals(swmsBasicMaterialSubType.getSubTypeCode())) {
            QueryWrapper<SwmsBasicMaterialSubType> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("sub_type_code", swmsBasicMaterialSubType.getSubTypeCode());
            List<SwmsBasicMaterialSubType> queryBuCode = swmsBasicMaterialSubTypeMapper.selectList(queryWrapper);
            if (queryBuCode.size() > 0) {
                throw new DataDuplicateException("更新失败，物料子类型代号同名:" + swmsBasicMaterialSubType.getSubTypeCode());
            }
        }
        return swmsBasicMaterialSubTypeMapper.updateById(swmsBasicMaterialSubType) > 0;
    }

    /**
     * @Description:    物料子类型-删除
     * @Author: River
     * @Date: 2020/1/11 20:27
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean delete(Integer id) {
        try {
            return swmsBasicMaterialSubTypeMapper.deleteById(id) > 0;
        } catch (Exception e) {
            throw new DataAssociationException(String.format("删除失败,该数据正在被使用:%d,如要删除请选择强力删除,将删除包括关联的台账,出库信息", id));
        }
    }

    /**
     * @Description:    物料子类型-批量删除
     * @Author: River
     * @Date: 2020/1/12 14:38
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean batchDelete(Set<String> ids) {
        try {
            return swmsBasicMaterialSubTypeMapper.deleteBatchIds(ids) == ids.size();
        } catch (Exception e) {
            throw new DataAssociationException("删除失败,数据正在被使用");
        }
    }
}
