package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicMatSup;
import com.jc.api.entity.SwmsBasicMaterialInfo;
import com.jc.api.entity.SwmsBasicSupplierInfo;
import com.jc.api.exception.custom.DataAssociationException;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.exception.custom.ParamVerifyException;
import com.jc.api.mapper.*;
import com.jc.api.service.restservice.ISwmsBasicMaterialInfoService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author XudongHu
 * @apiNote 物料信息实现类
 * @className SwmsBasicMaterialInfoService
 * @modifier
 * @since 20.1.12日3:01
 */
@Transactional
@Service
@Slf4j
public class SwmsBasicMaterialInfoService implements ISwmsBasicMaterialInfoService {
    @Autowired
    private SwmsBasicMaterialInfoMapper swmsBasicMaterialInfoMapper;
    @Autowired
    private SwmsBasicMaterialTypeMapper swmsBasicMaterialTypeMapper;
    @Autowired
    private SwmsBasicMaterialSubTypeMapper swmsBasicMaterialSubTypeMapper;
    @Autowired
    private SwmsMatSupMapper matSupMapper;
    @Autowired
    private SwmsBasicSupplierInfoMapper supplierInfoMapper;

    //新增校验
    private SwmsBasicMaterialInfo addVerify(SwmsBasicMaterialInfo entity) {
        if (StringUtil.isNullOrEmpty(entity.getMaterialName())) {
            throw new ParamVerifyException("新增失败,物料名称为空");
        }
        if (StringUtil.isNullOrEmpty(entity.getMaterialNameCode())) {
            throw new ParamVerifyException("新增失败,物料代号为空");
        }

        QueryWrapper<SwmsBasicMaterialInfo> byCode = new QueryWrapper<>();
        byCode.eq("material_name_code", entity.getMaterialNameCode()).last("limit 1");
        if (swmsBasicMaterialInfoMapper.selectOne(byCode) != null) {
            throw new DataDuplicateException("新增失败,物料代号重复:" + entity.getMaterialNameCode());
        }
        if (StringUtil.isNullOrEmpty(entity.getMeasureUnit())) {
            throw new ParamVerifyException("新增失败,物料单位为空");
        }
        if (entity.getMaterialTypeId() == null || swmsBasicMaterialTypeMapper.selectById(entity.getMaterialTypeId()) == null) {
            throw new ParamVerifyException("新增失败,物料大类不存在:" + entity.getMaterialTypeId());
        }
        if (entity.getSubTypeId() == null || swmsBasicMaterialSubTypeMapper.selectById(entity.getSubTypeId()) == null) {
            throw new ParamVerifyException("新增失败,物料小类不存在:" + entity.getSubTypeId());
        }
        if (entity.getAlkaliFlag() == null) entity.setAlkaliFlag(false);
        if (entity.getCoFlag() == null) entity.setCoFlag(false);
        if (entity.getMnFlag() == null) entity.setMnFlag(false);
        if (entity.getNiFlag() == null) entity.setNiFlag(false);
        if (entity.getNhFlag() == null) entity.setNhFlag(false);
        if (entity.getStreamFlag() == null) entity.setStreamFlag(false);
        return entity;
    }

    //更新校验
    private SwmsBasicMaterialInfo updateVerify(SwmsBasicMaterialInfo entity) {
        SwmsBasicMaterialInfo oldValue = swmsBasicMaterialInfoMapper.selectById(entity.getId());
        if (null == oldValue) {
            throw new ParamVerifyException("更新失败,不存在该id的数据:" + entity.getId());
        }
        if (entity.getMaterialTypeId() != null && swmsBasicMaterialTypeMapper.selectById(entity.getMaterialTypeId()) == null) {
            throw new ParamVerifyException("更新失败,物料大类不存在:" + entity.getMaterialTypeId());
        }
        if (entity.getSubTypeId() != null && swmsBasicMaterialSubTypeMapper.selectById(entity.getSubTypeId()) == null) {
            throw new ParamVerifyException("更新失败,物料小类不存在");
        }
        if (!StringUtil.isNullOrEmpty(entity.getMaterialNameCode()) && !entity.getMaterialNameCode().equals(oldValue.getMaterialNameCode())) {
            QueryWrapper<SwmsBasicMaterialInfo> byNameCode = new QueryWrapper<>();
            byNameCode.eq("material_name_code", entity.getMaterialNameCode()).last("limit 1");
            if (swmsBasicMaterialInfoMapper.selectOne(byNameCode) != null) {
                throw new DataDuplicateException("更新失败,物料代号重名:" + entity.getMaterialNameCode());
            }
        }

        return entity;
    }

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    @Override
    public Boolean add(SwmsBasicMaterialInfo entity, String[] supIds) {
        entity.setAutoFlag(false);
        SwmsBasicMaterialInfo mat = addVerify(entity);
        swmsBasicMaterialInfoMapper.insert(mat);
        for (int i = 0; i < supIds.length; i++) {
            SwmsBasicMatSup ms = new SwmsBasicMatSup();
            ms.setSupId(Integer.valueOf(supIds[i]));
            ms.setMatId(Integer.valueOf(mat.getId()));
            matSupMapper.insert(ms);
        }
        return true;
    }

    /**
     * 自动新增
     *
     * @param entity
     * @return
     */
    @Override
    public SwmsBasicMaterialInfo autoAdd(SwmsBasicMaterialInfo entity) {
        QueryWrapper<SwmsBasicMaterialInfo> byCode = new QueryWrapper<>();
        byCode.eq("material_name_code", entity.getMaterialNameCode()).last("limit 1");
        SwmsBasicMaterialInfo oldValue = swmsBasicMaterialInfoMapper.selectOne(byCode);
        if (oldValue != null) {
            return oldValue;
        }
        entity.setAutoFlag(true);
        entity.setCoFlag(false);
        entity.setMnFlag(false);
        entity.setNhFlag(false);
        entity.setNiFlag(false);
        entity.setAlkaliFlag(false);
        entity.setMaterialName("未知物料名称");
        entity.setStreamFlag(false);

        if (entity.getMaterialTypeId() == null) {
            throw new ParamVerifyException("物料自动新增设置错误,类型设置为空");
        }
        if (StringUtil.isNullOrEmpty(entity.getMeasureUnit())) {
            throw new ParamVerifyException("物料自动新增设置错误,单位设置为空");
        }
        swmsBasicMaterialInfoMapper.insert(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public Boolean update(SwmsBasicMaterialInfo entity, String[] supIds) {
        SwmsBasicMaterialInfo mat = updateVerify(entity);
        swmsBasicMaterialInfoMapper.updateById(mat);
        updateSup(mat.getId(), supIds);
        return true;
    }

    /**
     * 查询所有
     *
     * @param materialName 名称模糊
     * @return
     */
    @Override
    public List<SwmsBasicMaterialInfo> getAll(String materialName) {
        return swmsBasicMaterialInfoMapper.selectByNameLike(materialName);
    }

    /**
     * 分页查询所有
     *
     * @param page
     * @param materialName
     * @return
     */
    @Override
    public IPage<SwmsBasicMaterialInfo> getAllByPage(Page page, String materialName) {
        IPage ans = swmsBasicMaterialInfoMapper.selectPageVo(page, materialName);
        List<Map<String, Object>> rec = new ArrayList<>();
        List<SwmsBasicMaterialInfo> record = ans.getRecords();
        for (int i = 0; i < record.size(); i++) {
            QueryWrapper<SwmsBasicMatSup> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("mat_id", record.get(i).getId());
            List<SwmsBasicMatSup> ms = matSupMapper.selectList(queryWrapper);
            List ss = new ArrayList();
            if (ms.size() != 0) {
                SwmsBasicSupplierInfo sInfo = supplierInfoMapper.selectById(ms.get(0).getSupId());
                ss.add(sInfo);
            }
            for (int l = 1; l < ms.size(); l++) {
                SwmsBasicSupplierInfo sInfo = supplierInfoMapper.selectById(ms.get(l).getSupId());
                ss.add(sInfo);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("head", record.get(i));
            map.put("sup", ss);
            rec.add(map);
        }
        ans.setRecords(rec);
        return ans;
    }

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return
     */
    @Override
    public Boolean delete(String id) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("mat_id", id);
            matSupMapper.deleteByMap(map);
            return swmsBasicMaterialInfoMapper.deleteById(id) > 0;
        } catch (Exception e) {
            throw new DataAssociationException("删除失败,数据使用中:" + id);
        }

    }

    /**
     * 批量删除
     *
     * @param ids 主键数组
     * @return
     */
    @Override
    public Boolean batchDelete(Set<String> ids) {
        try {
            Map<String, Object> map = new HashMap<>();
            ids.forEach(e -> {
                map.put("mat_id", e);
                matSupMapper.deleteByMap(map);
            });
            return swmsBasicMaterialInfoMapper.deleteBatchIds(ids) > 0;
        } catch (Exception e) {
            throw new DataAssociationException("删除失败,数据使用中");
        }
    }

    @Override
    public List getByType(Integer type) {
        QueryWrapper<SwmsBasicMaterialInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("material_type_id", type);
        return swmsBasicMaterialInfoMapper.selectList(queryWrapper);
    }

    @Override
    public List getByTypeBySubtype(Integer type, Integer subType) {
        QueryWrapper<SwmsBasicMaterialInfo> byTypeBySubType = new QueryWrapper<>();
        byTypeBySubType.eq(type != null,"material_type_id",type)
                .eq(subType != null,"sub_type_id",subType);
        return swmsBasicMaterialInfoMapper.selectList(byTypeBySubType);
    }

    private void updateSup(String matId, String[] ids) {
        Map<String, Object> map = new HashMap<>();
        map.put("mat_id", matId);
        matSupMapper.deleteByMap(map);

        for (int i = 0; i < ids.length; i++) {
            SwmsBasicMatSup ms = new SwmsBasicMatSup();
            ms.setMatId(Integer.valueOf(matId));
            ms.setSupId(Integer.valueOf(ids[i]));
            matSupMapper.insert(ms);
        }

    }
}
