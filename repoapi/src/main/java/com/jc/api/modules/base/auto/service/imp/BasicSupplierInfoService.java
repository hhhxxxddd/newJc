package com.jc.api.modules.base.auto.service.imp;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.common.exception.custom.DataAssociationException;
import com.jc.api.common.exception.custom.DataDuplicateException;
import com.jc.api.modules.base.auto.entity.param.BasicSupplierInfoQueryParam;
import com.jc.api.modules.base.auto.entity.BasicSupplierInfo;
import com.jc.api.modules.base.auto.mapper.BasicSupplierInfoMapper;
import com.jc.api.modules.base.auto.service.IBasicSupplierInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author XudongHu
 * @apiNote 物料供应商实现类
 * @className BasicSupplierInfoService
 * @modifier
 * @since 20.1.9日15:16
 */
@Service
@Slf4j
public class BasicSupplierInfoService implements IBasicSupplierInfoService {
    @Autowired
    private BasicSupplierInfoMapper basicSupplierInfoMapper;

    @Override
    public Boolean add(BasicSupplierInfo basicSupplierInfo) {
        String materialSupplierCode = basicSupplierInfo.getMaterialSupplierCode();
        QueryWrapper<BasicSupplierInfo> bySupplierCode = new QueryWrapper<>();
        bySupplierCode.eq("material_supplier_code", materialSupplierCode).last("limit 1");
        if (null != basicSupplierInfoMapper.selectOne(bySupplierCode))
            throw new DataDuplicateException("新增失败,物料供应商代号同名:" + materialSupplierCode);
        return basicSupplierInfoMapper.insert(basicSupplierInfo) > 0;
    }

    @Override
    public BasicSupplierInfo autoAdd(BasicSupplierInfo basicSupplierInfo) {
        String materialSupplierCode = basicSupplierInfo.getMaterialSupplierCode();
        QueryWrapper<BasicSupplierInfo> bySupplierCode = new QueryWrapper<>();
        bySupplierCode.eq("material_supplier_code", materialSupplierCode).last("limit 1");

        BasicSupplierInfo oldValue = basicSupplierInfoMapper.selectOne(bySupplierCode);
        if (null != oldValue) return oldValue;
        log.info("发现新供应商,开始新增=========>");
        basicSupplierInfo.setAutoFlag(true);
        basicSupplierInfo.setMaterialSupplierName("未知厂商名称");
        basicSupplierInfoMapper.insert(basicSupplierInfo);
        log.info("新供应商新增结束=========>{}", basicSupplierInfo);
        return basicSupplierInfo;
    }

    @Override
    public Boolean delete(String id) {
        try {
            return basicSupplierInfoMapper.deleteById(id) > 0;
        } catch (Exception e) {
            throw new DataAssociationException(String.format("删除失败,该数据正在被使用:%s", id));
        }
    }

    @Override
    public Boolean batchDelete(Set<String> ids) {
        try {
            return basicSupplierInfoMapper.deleteBatchIds(ids) > 0;
        } catch (Exception e) {
            throw new DataAssociationException("删除失败,存在被使用的数据");
        }
    }

    @Override
    public Boolean update(BasicSupplierInfo basicSupplierInfo) {
        return null;
    }

    @Override
    public List<BasicSupplierInfo> getAll(BasicSupplierInfoQueryParam basicSupplierInfoQueryParam) {
        return null;
    }

    @Override
    public Page<BasicSupplierInfo> getAllByPage(Page page, BasicSupplierInfoQueryParam basicSupplierInfoQueryParam) {
        return null;
    }


    //
    private Boolean isDuplicate(BasicSupplierInfo basicSupplierInfo) {
        String materialSupplierCode = basicSupplierInfo.getMaterialSupplierCode();
        QueryWrapper<BasicSupplierInfo> bySupplierCode = new QueryWrapper<>();
        bySupplierCode.eq("material_supplier_code", materialSupplierCode).last("limit 1");
        return basicSupplierInfoMapper.selectOne(bySupplierCode) == null;
    }
}

