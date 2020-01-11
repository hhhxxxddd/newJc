package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicSupplierInfo;
import com.jc.api.exception.custom.DataAssociationException;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.exception.custom.DataNotFindException;
import com.jc.api.exception.custom.ParamVerifyException;
import com.jc.api.mapper.SwmsBasicSupplierInfoMapper;
import com.jc.api.service.restservice.ISwmsBasicSupplierInfoService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author XudongHu
 * @apiNote 物料供应商实现类
 * @className SwmsBasicSupplierInfoService
 * @modifier
 * @since 20.1.11日13:05
 */
@Service
@Slf4j
public class SwmsBasicSupplierInfoService implements ISwmsBasicSupplierInfoService {
    @Autowired
    private SwmsBasicSupplierInfoMapper swmsBasicSupplierInfoMapper;


    //新增校验
    private SwmsBasicSupplierInfo addVerify(SwmsBasicSupplierInfo entity) {
        if (StringUtil.isNullOrEmpty(entity.getMaterialSupplierCode())) {
            throw new ParamVerifyException("供应商编码为空");
        }
        if (StringUtil.isNullOrEmpty(entity.getMaterialSupplierName())) {
            throw new ParamVerifyException("供应商名称为空");
        }
        String materialSupplierCode = entity.getMaterialSupplierCode();
        QueryWrapper<SwmsBasicSupplierInfo> bySupplierCode = new QueryWrapper<>();
        bySupplierCode.eq("material_supplier_code", materialSupplierCode).last("limit 1");
        if (null != swmsBasicSupplierInfoMapper.selectOne(bySupplierCode)) {
            throw new DataDuplicateException("新增失败,物料供应商代号同名:" + materialSupplierCode);
        }
        if (entity.getAutoFlag() == null) {
            entity.setAutoFlag(false);
        }
        return entity;
    }

    //更新校验
    private SwmsBasicSupplierInfo updateVerity(SwmsBasicSupplierInfo entity) {
        if (StringUtil.isNullOrEmpty(entity.getId())) {
            throw new ParamVerifyException("找不到供应商id");
        }
        SwmsBasicSupplierInfo oldValue = swmsBasicSupplierInfoMapper.selectById(entity.getId());
        if (oldValue == null) {
            throw new DataNotFindException("更新失败,不存在的物料供货商:" + entity.getId());
        }
        String materialSupplierCode = entity.getMaterialSupplierCode();

        if (!StringUtil.isNullOrEmpty(materialSupplierCode) && !oldValue.getMaterialSupplierCode().equals(materialSupplierCode)) {
            QueryWrapper<SwmsBasicSupplierInfo> bySupplierCode = new QueryWrapper<>();
            bySupplierCode.eq("material_supplier_code", materialSupplierCode).last("limit 1");
            if (null != swmsBasicSupplierInfoMapper.selectOne(bySupplierCode)) {
                throw new DataDuplicateException("更新失败,物料供应商代号同名:" + materialSupplierCode);
            }
        }

        return entity;
    }


    @Override
    public Boolean add(SwmsBasicSupplierInfo entity) {
        return swmsBasicSupplierInfoMapper.insert(addVerify(entity)) > 0;
    }

    @Override
    public SwmsBasicSupplierInfo autoAdd(SwmsBasicSupplierInfo entity) {
        String materialSupplierCode = entity.getMaterialSupplierCode();
        QueryWrapper<SwmsBasicSupplierInfo> bySupplierCode = new QueryWrapper<>();
        bySupplierCode.eq("material_supplier_code", materialSupplierCode).last("limit 1");
        SwmsBasicSupplierInfo oldValue = swmsBasicSupplierInfoMapper.selectOne(bySupplierCode);
        if (null != oldValue) return oldValue;
        log.info("发现新供应商,开始新增=========>");
        entity.setAutoFlag(true).setMaterialSupplierName("未知厂商名称");
        swmsBasicSupplierInfoMapper.insert(entity);
        log.info("新供应商新增结束=========>{}", entity.toString());
        return entity;
    }

    @Override
    public Boolean update(SwmsBasicSupplierInfo entity) {
        return swmsBasicSupplierInfoMapper.updateById(updateVerity(entity)) > 0;
    }

    @Override
    public List<SwmsBasicSupplierInfo> getAll(SwmsBasicSupplierInfo entity) {
        QueryWrapper<SwmsBasicSupplierInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(entity);
        //todo
        return swmsBasicSupplierInfoMapper.selectList(queryWrapper);

    }

    @Override
    public IPage<SwmsBasicSupplierInfo> getAllByPage(Page page, SwmsBasicSupplierInfo entity) {
        QueryWrapper<SwmsBasicSupplierInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(entity);
        //todo
        return swmsBasicSupplierInfoMapper.selectPage(page,queryWrapper);
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            return swmsBasicSupplierInfoMapper.deleteById(id) > 0;
        } catch (Exception e) {
            throw new DataAssociationException(String.format("删除失败,该数据正在被使用:%d", id));
        }
    }

    @Override
    @Transactional
    public Boolean batchDelete(Set<String> ids) {
        try {
            return swmsBasicSupplierInfoMapper.deleteBatchIds(ids) == ids.size();
        } catch (Exception e) {
            throw new DataAssociationException("删除失败,数据正在被使用");
        }
    }
}
