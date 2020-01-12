package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicMeasureUnit;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.exception.custom.DataNotFindException;
import com.jc.api.exception.custom.ParamVerifyException;
import com.jc.api.mapper.SwmsBasicMeasureUnitMapper;
import com.jc.api.service.restservice.ISwmsBasicMeasureUnitService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author XudongHu
 * @apiNote 计量单位实现类
 * @className SwmsBasicMeasureUnitService
 * @modifier
 * @since 20.1.12日14:44
 */
@Service
@Slf4j
public class SwmsBasicMeasureUnitService implements ISwmsBasicMeasureUnitService {
    @Autowired
    private SwmsBasicMeasureUnitMapper swmsBasicMeasureUnitMapper;

    //新增校验
    private SwmsBasicMeasureUnit addVerify(SwmsBasicMeasureUnit entity) {
        if (StringUtil.isNullOrEmpty(entity.getMeasureUnit())) {
            throw new ParamVerifyException("新增失败,计量单位代号不存在");
        }
        if (StringUtil.isNullOrEmpty(entity.getMeasureUnitDesc())) {
            throw new ParamVerifyException("新增失败,计量单位描述不存在");
        }
        entity.setAutoFlag(false);
        return entity;
    }

    //更新校验
    private SwmsBasicMeasureUnit updateVerify(SwmsBasicMeasureUnit entity) {
        SwmsBasicMeasureUnit oldValue = swmsBasicMeasureUnitMapper.selectById(entity.getId());
        if (oldValue == null) {
            throw new DataNotFindException("更新失败,不存在该id的计量单位:" + entity.getId());
        }
        if (!StringUtil.isNullOrEmpty(entity.getMeasureUnit()) && !oldValue.getMeasureUnit().equals(entity.getMeasureUnit())) {
            QueryWrapper<SwmsBasicMeasureUnit> byUnit = new QueryWrapper<>();
            byUnit.eq("measure_unit", entity.getMeasureUnit()).last("limit 1");
            if (swmsBasicMeasureUnitMapper.selectOne(byUnit) != null) {
                throw new DataDuplicateException("更新失败,计量单位重复:" + entity.getMeasureUnit());
            }
        }

        return entity;
    }


    @Override
    public Boolean add(SwmsBasicMeasureUnit entity) {
        return swmsBasicMeasureUnitMapper.insert(addVerify(entity)) > 0;
    }

    @Override
    public SwmsBasicMeasureUnit autoAdd(SwmsBasicMeasureUnit entity) {
        if (StringUtil.isNullOrEmpty(entity.getMeasureUnit())) {
            throw new ParamVerifyException("自动新增失败,计量单位代号为空");
        }

        QueryWrapper<SwmsBasicMeasureUnit> byUnit = new QueryWrapper<>();
        byUnit.eq("measure_unit", entity.getMeasureUnit()).last("limit 1");
        SwmsBasicMeasureUnit oldValue = swmsBasicMeasureUnitMapper.selectOne(byUnit);
        if (oldValue != null) return oldValue;
        entity.setAutoFlag(true);
        entity.setMeasureUnitDesc("未知计量单位");
        swmsBasicMeasureUnitMapper.insert(entity);
        return entity;
    }

    @Override
    public Boolean update(SwmsBasicMeasureUnit entity) {
        return swmsBasicMeasureUnitMapper.updateById(updateVerify(entity)) > 0;
    }

    @Override
    public List<SwmsBasicMeasureUnit> getAll(String measureUnitDesc) {
        QueryWrapper<SwmsBasicMeasureUnit> byDesc = new QueryWrapper<>();
        byDesc.likeRight("measure_unit_desc", measureUnitDesc);
        return swmsBasicMeasureUnitMapper.selectList(byDesc);
    }

    @Override
    public IPage<SwmsBasicMeasureUnit> getAllByPage(Page page, String measureUnitDesc) {
        QueryWrapper<SwmsBasicMeasureUnit> byDesc = new QueryWrapper<>();
        byDesc.likeRight("measure_unit_desc", measureUnitDesc);
        return swmsBasicMeasureUnitMapper.selectPage(page, byDesc);
    }

    @Override
    public Boolean delete(String id) {
        return swmsBasicMeasureUnitMapper.deleteById(id) > 0;
    }

    @Override
    public Boolean batchDelete(Set<String> ids) {
        return swmsBasicMeasureUnitMapper.deleteBatchIds(ids)==ids.size();
    }
}
