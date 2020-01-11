package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.param.MaterialInfoSupplierQueryParam;
import com.jc.api.entity.po.MaterialInfoSupplier;
import com.jc.api.exception.custom.DataAssociationException;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.exception.custom.DataNotFindException;
import com.jc.api.mapper.MaterialInfoSupplierMapper;
import com.jc.api.service.restservice.IMaterialInfoSupplierService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author XudongHu
 * @apiNote 物料供应商服务实现类
 * @className MaterialInfoSupplierService
 * @modifier
 * @since 19.12.8日2:12
 */
@Service
@Slf4j
public class MaterialInfoSupplierService implements IMaterialInfoSupplierService {
    @Autowired
    private MaterialInfoSupplierMapper materialInfoSupplierMapper;

    /**
     * 新增
     *
     * @param materialInfoSupplier 物料供应商po
     * @return boolean
     */
    @Override
    @Transactional
    public Boolean add(MaterialInfoSupplier materialInfoSupplier) {
        String materialSupplierCode = materialInfoSupplier.getMaterialSupplierCode();
        QueryWrapper<MaterialInfoSupplier> bySupplierCode = new QueryWrapper<>();
        bySupplierCode.eq("material_supplier_code", materialSupplierCode).last("limit 1");
        if (null != materialInfoSupplierMapper.selectOne(bySupplierCode))
            throw new DataDuplicateException("新增失败,物料供应商代号同名:" + materialSupplierCode);
        return materialInfoSupplierMapper.insert(materialInfoSupplier) > 0;
    }

    /**
     * 自动新增
     * 根据代号查询
     * 查询成功?返回:新增
     *
     * @param materialInfoSupplier 物料供应商po
     * @return 物料供应商po
     */
    @Override
    @Transactional
    public MaterialInfoSupplier autoAdd(MaterialInfoSupplier materialInfoSupplier) {
        String materialSupplierCode = materialInfoSupplier.getMaterialSupplierCode();
        QueryWrapper<MaterialInfoSupplier> bySupplierCode = new QueryWrapper<>();
        bySupplierCode.eq("material_supplier_code", materialSupplierCode).last("limit 1");

        MaterialInfoSupplier oldValue = materialInfoSupplierMapper.selectOne(bySupplierCode);
        if (null != oldValue) return oldValue;
        log.info("发现新供应商,开始新增=========>");
        materialInfoSupplier.setAutoFlag(1);
        materialInfoSupplier.setMaterialSupplierName("未知厂商名称");
        materialInfoSupplierMapper.insert(materialInfoSupplier);
        log.info("新供应商新增结束=========>{}", materialInfoSupplier.toString());
        return materialInfoSupplier;
    }

    /**
     * 更新
     *
     * @param materialInfoSupplier 物料供应商po
     * @return boolean
     */
    @Override
    @Transactional
    public Boolean update(MaterialInfoSupplier materialInfoSupplier) {
        MaterialInfoSupplier oldValue = materialInfoSupplierMapper.selectById(materialInfoSupplier.getId());
        if (oldValue == null) throw new DataNotFindException("更新失败,不存在的物料供货商:" + materialInfoSupplier.getId());
        String materialSupplierCode = materialInfoSupplier.getMaterialSupplierCode();
        String materialSupplierName = materialInfoSupplier.getMaterialSupplierName();

        if (!StringUtil.isNullOrEmpty(materialSupplierCode) && !oldValue.getMaterialSupplierCode().equals(materialSupplierCode)) {
            oldValue.setMaterialSupplierCode(materialSupplierCode);
            QueryWrapper<MaterialInfoSupplier> bySupplierCode = new QueryWrapper<>();
            bySupplierCode.eq("material_supplier_code", materialSupplierCode).last("limit 1");
            if (null != materialInfoSupplierMapper.selectOne(bySupplierCode))
                throw new DataDuplicateException("更新失败,物料供应商代号同名:" + materialSupplierCode);
        }
        if (!StringUtil.isNullOrEmpty(materialSupplierName)) oldValue.setMaterialSupplierName(materialSupplierName);

        return materialInfoSupplierMapper.updateById(oldValue) > 0;
    }

    /**
     * 条件查询
     *
     * @param materialInfoSupplierQueryParam 物料供应商参数
     * @return list
     */
    @Override
    public List<MaterialInfoSupplier> getAll(MaterialInfoSupplierQueryParam materialInfoSupplierQueryParam) {
        QueryWrapper<MaterialInfoSupplier> build = materialInfoSupplierQueryParam.build();
        return materialInfoSupplierMapper.selectList(build);
    }

    /**
     * 条件查询-分页
     *
     * @param page          分页参数
     * @param supplierParam 物料供应商参数
     * @return page
     */
    @Override
    public IPage getAllByPage(Page page, MaterialInfoSupplierQueryParam supplierParam) {
        QueryWrapper<MaterialInfoSupplier> build = supplierParam.build();
        return materialInfoSupplierMapper.selectPage(page, build);
    }

    /**
     * 删除
     *
     * @param id     主键
     * @return boolean
     */
    @Override
    @Transactional
    public Boolean delete(Integer id) {
        try {
            return materialInfoSupplierMapper.deleteById(id) > 0;
        } catch (Exception e) {
            throw new DataAssociationException(String.format("删除失败,该数据正在被使用:%d", id));
        }
    }
}

