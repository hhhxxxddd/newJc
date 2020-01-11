package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.param.MaterialInfoWorkshopQueryParam;
import com.jc.api.entity.po.MaterialInfoWorkshop;
import com.jc.api.exception.custom.DataAssociationException;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.exception.custom.DataNotFindException;
import com.jc.api.mapper.MaterialInfoWorkshopMapper;
import com.jc.api.service.restservice.IMaterialInfoWorkshopService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author XudongHu
 * @apiNote 物料供应车间服务实现类
 * @className MaterialInfoWorkshopService
 * @modifier
 * @since 19.12.9日3:08
 */
@Deprecated
@Slf4j
@Service
public class MaterialInfoWorkshopService implements IMaterialInfoWorkshopService {
    @Autowired
    private MaterialInfoWorkshopMapper materialInfoWorkshopMapper;

    /**
     * 新增
     *
     * @param materialInfoWorkshop 物料供应车间po
     * @return boolean
     */
    @Override
    @Transactional
    public Boolean add(MaterialInfoWorkshop materialInfoWorkshop) {
        String materialWorkshopCode = materialInfoWorkshop.getMaterialWorkshopCode();
        QueryWrapper<MaterialInfoWorkshop> byWorkshopCode = new QueryWrapper<>();
        byWorkshopCode.eq("material_workshop_code", materialWorkshopCode).last("limit 1");
        if (null != materialInfoWorkshopMapper.selectOne(byWorkshopCode))
            throw new DataDuplicateException("新增失败,物料车间代号同名:" + materialWorkshopCode);
        return materialInfoWorkshopMapper.insert(materialInfoWorkshop) > 0;
    }

    /**
     * 自动新增
     *
     * @param materialInfoWorkshop 物料供应车间po
     * @return
     */
    @Override
    @Transactional
    public MaterialInfoWorkshop autoAdd(MaterialInfoWorkshop materialInfoWorkshop) {
        String materialWorkshopCode = materialInfoWorkshop.getMaterialWorkshopCode();
        QueryWrapper<MaterialInfoWorkshop> byWorkshopCode = new QueryWrapper<>();
        byWorkshopCode.eq("material_workshop_code", materialWorkshopCode).last("limit 1");
        MaterialInfoWorkshop oldValue = materialInfoWorkshopMapper.selectOne(byWorkshopCode);
        if (null != oldValue) return oldValue;
        log.info("发现新车间,开始新增========>:");
        materialInfoWorkshop.setAutoFlag(1);
        materialInfoWorkshop.setMaterialWorkshopName("未知车间名称");
        materialInfoWorkshopMapper.insert(materialInfoWorkshop);
        log.info("车间新增结束=========>:{}", materialInfoWorkshop.toString());
        return materialInfoWorkshop;
    }

    /**
     * 更新
     *
     * @param materialInfoWorkshop 物料供应车间po
     * @return
     */
    @Override
    @Transactional
    public Boolean update(MaterialInfoWorkshop materialInfoWorkshop) {
        MaterialInfoWorkshop oldValue = materialInfoWorkshopMapper.selectById(materialInfoWorkshop.getId());
        if (oldValue == null) throw new DataNotFindException("更新失败,不存在的物料供应车间:" + materialInfoWorkshop.getId());
        String materialWorkshopCode = materialInfoWorkshop.getMaterialWorkshopCode();
        String materialWorkshopName = materialInfoWorkshop.getMaterialWorkshopName();

        if (!StringUtil.isNullOrEmpty(materialWorkshopCode) && !oldValue.getMaterialWorkshopCode().equals(materialWorkshopCode)) {
            oldValue.setMaterialWorkshopCode(materialWorkshopCode);
            QueryWrapper<MaterialInfoWorkshop> byWorkshopCode = new QueryWrapper<>();
            byWorkshopCode.eq("material_workshop_code", byWorkshopCode).last("limit 1");
            if (null != materialInfoWorkshopMapper.selectOne(byWorkshopCode))
                throw new DataDuplicateException("更新失败,物料供应车间代号同名:" + materialWorkshopCode);
        }
        if (!StringUtil.isNullOrEmpty(materialWorkshopName)) oldValue.setMaterialWorkshopName(materialWorkshopName);

        return materialInfoWorkshopMapper.updateById(oldValue) > 0;
    }

    /**
     * 查询所有-条件
     *
     * @param materialInfoWorkshopQueryParam 物料供应车间参数
     * @return
     */
    @Override
    public List<MaterialInfoWorkshop> getAll(MaterialInfoWorkshopQueryParam materialInfoWorkshopQueryParam) {
        QueryWrapper<MaterialInfoWorkshop> build = materialInfoWorkshopQueryParam.build();
        return materialInfoWorkshopMapper.selectList(build);
    }

    /**
     * 查询所有-分页/条件
     *
     * @param page                           分页参数
     * @param materialInfoWorkshopQueryParam 物料供应车间参数
     * @return
     */
    @Override
    public IPage getAllByPage(Page page, MaterialInfoWorkshopQueryParam materialInfoWorkshopQueryParam) {
        QueryWrapper<MaterialInfoWorkshop> build = materialInfoWorkshopQueryParam.build();
        return materialInfoWorkshopMapper.selectPage(page, build);
    }

    /**
     * 删除
     *
     * @param id     主键
     * @return
     */
    @Override
    @Transactional
    public Boolean delete(Integer id) {
        try {
            return materialInfoWorkshopMapper.deleteById(id) > 0;
        } catch (Exception e) {
            throw new DataAssociationException(String.format("删除失败,该数据正在被使用:%d,如要删除请选择强力删除,将删除包括关联的台账,出库信息", id));
        }
    }
}
