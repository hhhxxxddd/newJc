package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.*;
import com.jc.api.exception.custom.DataAssociationException;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.exception.custom.ParamVerifyException;
import com.jc.api.mapper.*;
import com.jc.api.service.restservice.ISwmsBasicSafetyStockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/12 15:50
 * @Description:    安全库存Service实现层
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class SwmsBasicSafetyStockService implements ISwmsBasicSafetyStockService {

    @Autowired
    private SwmsBasicSafetyStockMapper swmsBasicSafetyStockMapper;

    @Autowired
    private SwmsBasicMaterialInfoMapper swmsBasicMaterialInfoMapper;

    @Autowired
    private SwmsBasicMaterialTypeMapper swmsBasicMaterialTypeMapper;

    @Autowired
    private SwmsBasicMaterialSubTypeMapper swmsBasicMaterialSubTypeMapper;

    @Autowired
    private SwmsBasicSupplierInfoMapper supplierInfoMapper;

    /**
     * @Description:    安全库存-不分页
     * @Author: River
     * @Date: 2020/1/12 16:05
     {@link List< SwmsBasicSafetyStock>}
     java.util.List<com.jc.api.entity.SwmsBasicSafetyStock>
     **/
    @Override
    public List<SwmsBasicSafetyStock> getAll(String materialName) {
        try {
            return swmsBasicSafetyStockMapper.findList(materialName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description:    安全库存-分页
     * @Author: River
     * @Date: 2020/1/12 16:05
     {@link IPage< SwmsBasicSafetyStock>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicSafetyStock>
     **/
    @Override
    public IPage<SwmsBasicSafetyStock> getAllByPage(Page page, String materialName) {
        return swmsBasicSafetyStockMapper.selectPage(page, materialName);
    }

    /**
     * @Description:    安全库存-新增
     * @Author: River
     * @Date: 2020/1/12 16:06
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean add(SwmsBasicSafetyStock swmsBasicSafetyStock) {
        if (swmsBasicSafetyStock.getMaterialId() == null) {
            throw new ParamVerifyException("新增失败：未选择物料");
        }
        SwmsBasicMaterialInfo materialInfo = swmsBasicMaterialInfoMapper.selectById(swmsBasicSafetyStock.getMaterialId());
        if (materialInfo == null) {
            throw new DataDuplicateException("新增失败：物料信息不存在：" + swmsBasicSafetyStock.getMaterialId());
        }
        if (swmsBasicSafetyStock.getMaterialTypeId() == null) {
            throw new ParamVerifyException("新增失败：未选择类型");
        }
        SwmsBasicMaterialType materialType = swmsBasicMaterialTypeMapper.selectById(swmsBasicSafetyStock.getMaterialTypeId());
        if (materialType == null) {
            throw new DataDuplicateException("新增失败：类型信息不存在：" + swmsBasicSafetyStock.getMaterialTypeId());
        }
        if (swmsBasicSafetyStock.getSubTypeId() != null) {
            SwmsBasicMaterialSubType materialSubType = swmsBasicMaterialSubTypeMapper.selectById(swmsBasicSafetyStock.getSubTypeId());
            if (materialSubType == null) {
                throw new DataDuplicateException("新增失败：子类型信息不存在：" + swmsBasicSafetyStock.getSubTypeId());
        }
        }
        /*if (swmsBasicSafetyStock.getSupId() != null) {
            SwmsBasicSupplierInfo supplierInfo = supplierInfoMapper.selectById(swmsBasicSafetyStock.getSupId());
            if (supplierInfo == null) {
                throw new DataDuplicateException("新增失败：供应商不存在：" + swmsBasicSafetyStock.getSupId());
            }
        }*/
        return swmsBasicSafetyStockMapper.insert(swmsBasicSafetyStock) > 0;
    }

    /**
     * @Description:    安全库存-更新
     * @Author: River
     * @Date: 2020/1/12 16:06
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean update(SwmsBasicSafetyStock swmsBasicSafetyStock) {
        if (StringUtils.isEmpty(swmsBasicSafetyStock.getId())) {
            throw new ParamVerifyException("传入数据的ID为空");
        }
        SwmsBasicSafetyStock queryById = swmsBasicSafetyStockMapper.selectById(swmsBasicSafetyStock.getId());
        if (queryById == null) {
            throw new DataDuplicateException("更新失败，安全库存不存在：" + swmsBasicSafetyStock.getId());
        }
        if (swmsBasicSafetyStock.getMaterialId() == null) {
            throw new ParamVerifyException("更新失败：未选择物料");
        }
        SwmsBasicMaterialInfo materialInfo = swmsBasicMaterialInfoMapper.selectById(swmsBasicSafetyStock.getMaterialId());
        if (materialInfo == null) {
            throw new DataDuplicateException("更新失败：物料信息不存在：" + swmsBasicSafetyStock.getMaterialId());
        }
        if (swmsBasicSafetyStock.getMaterialTypeId() == null) {
            throw new ParamVerifyException("更新失败：未选择类型");
        }
        SwmsBasicMaterialType materialType = swmsBasicMaterialTypeMapper.selectById(swmsBasicSafetyStock.getMaterialTypeId());
        if (materialType == null) {
            throw new DataDuplicateException("更新失败：类型信息不存在：" + swmsBasicSafetyStock.getMaterialTypeId());
        }
        if (swmsBasicSafetyStock.getSubTypeId() != null) {
            SwmsBasicMaterialSubType materialSubType = swmsBasicMaterialSubTypeMapper.selectById(swmsBasicSafetyStock.getSubTypeId());
            if (materialSubType == null) {
                throw new DataDuplicateException("更新失败：子类型信息不存在：" + swmsBasicSafetyStock.getSubTypeId());
            }
        }
        return swmsBasicSafetyStockMapper.updateById(swmsBasicSafetyStock) > 0;
    }

    /**
     * @Description:    安全库存-删除
     * @Author: River
     * @Date: 2020/1/12 16:06
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean delete(Integer id) {
        try {
            return swmsBasicSafetyStockMapper.deleteById(id) > 0;
        } catch (Exception e) {
            throw new DataAssociationException(String.format("删除失败,该数据正在被使用:%d,如要删除请选择强力删除,将删除包括关联的台账,出库信息", id));
        }
    }

    /**
     * @Description:    安全库存-批量删除
     * @Author: River
     * @Date: 2020/1/12 16:06
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean batchDelete(Set<String> ids) {
        try {
            return swmsBasicSafetyStockMapper.deleteBatchIds(ids) == ids.size();
        } catch (Exception e) {
            throw new DataAssociationException("删除失败,数据正在被使用");
        }
    }
}
