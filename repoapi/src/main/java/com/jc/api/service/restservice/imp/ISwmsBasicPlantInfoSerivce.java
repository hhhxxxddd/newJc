package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicPlantInfo;
import com.jc.api.exception.custom.DataAssociationException;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.mapper.SwmsBasicPlantInfoMapper;
import com.jc.api.service.restservice.ISwmsBasicPlantInfoService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: River
 * @Date: 2020/1/11 15:28
 * @Description: 车间Serivce层
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class ISwmsBasicPlantInfoSerivce implements ISwmsBasicPlantInfoService {

    @Autowired
    private SwmsBasicPlantInfoMapper swmsBasicPlantInfoMapper;

    /**
     * @Description:    查询所有
     * @Author: River
     * @Date: 2020/1/11 15:46
     {@link List< SwmsBasicPlantInfo>}
     java.util.List<com.jc.api.entity.SwmsBasicPlantInfo>
     **/
    @Override
    public List<SwmsBasicPlantInfo> getAll(SwmsBasicPlantInfo swmsBasicPlantInfo) {
        QueryWrapper<SwmsBasicPlantInfo> queryWrapper = new QueryWrapper<>();
        if (!StringUtil.isNullOrEmpty(swmsBasicPlantInfo.getPlantCode())) {
            queryWrapper.likeRight("plant_code", swmsBasicPlantInfo.getPlantCode());
        }
        if (!StringUtil.isNullOrEmpty(swmsBasicPlantInfo.getPlantName())) {
            queryWrapper.likeRight("plant_name", swmsBasicPlantInfo.getPlantName());
        }
        if (swmsBasicPlantInfo.getAutoFlag() != null) {
            queryWrapper.eq("auto_flag", swmsBasicPlantInfo.getAutoFlag());
        }
        return swmsBasicPlantInfoMapper.selectList(queryWrapper);
    }

    /**
     * @Description:    查询所有-分页
     * @Author: River
     * @Date: 2020/1/11 16:08
     {@link IPage< SwmsBasicPlantInfo>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicPlantInfo>
     **/
    @Override
    public IPage<SwmsBasicPlantInfo> getAllByPage(Page page, SwmsBasicPlantInfo swmsBasicPlantInfo) {
        QueryWrapper<SwmsBasicPlantInfo> queryWrapper = new QueryWrapper<>();
        if (!StringUtil.isNullOrEmpty(swmsBasicPlantInfo.getPlantCode())) {
            queryWrapper.likeRight("plant_code", swmsBasicPlantInfo.getPlantCode());
        }
        if (StringUtil.isNullOrEmpty(swmsBasicPlantInfo.getPlantName())) {
            queryWrapper.likeRight("plant_name", swmsBasicPlantInfo.getPlantName());
        }
        if (swmsBasicPlantInfo.getAutoFlag() != null) {
            queryWrapper.eq("auto_flag", swmsBasicPlantInfo.getAutoFlag());
        }
        return swmsBasicPlantInfoMapper.selectPage(page, queryWrapper);
    }

    /**
     * @Description:    物料车间-新增
     * @Author: River
     * @Date: 2020/1/11 16:09
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean add(SwmsBasicPlantInfo swmsBasicPlantInfo) {
        QueryWrapper<SwmsBasicPlantInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plant_code", swmsBasicPlantInfo.getPlantCode());
        if (swmsBasicPlantInfoMapper.selectOne(queryWrapper) != null) {
            throw new DataDuplicateException("新增失败,物料车间代号同名:" + swmsBasicPlantInfo.getPlantCode());
        }
        return swmsBasicPlantInfoMapper.insert(swmsBasicPlantInfo) > 0;
    }

    /**
     * @Description:    物料车间-根据传入的代号查询车间，无此数据则新增
     * @Author: River
     * @Date: 2020/1/11 16:17
     {@link SwmsBasicPlantInfo}
     com.jc.api.entity.SwmsBasicPlantInfo
     **/
    @Override
    public SwmsBasicPlantInfo autoAdd(SwmsBasicPlantInfo swmsBasicPlantInfo) {
        QueryWrapper<SwmsBasicPlantInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plant_code", swmsBasicPlantInfo.getPlantCode());
        SwmsBasicPlantInfo oldPlantInfo = swmsBasicPlantInfoMapper.selectOne(queryWrapper);
        if (oldPlantInfo != null) {
            return oldPlantInfo;
        }
        log.info("发现新车间,开始新增========>:");
        swmsBasicPlantInfo.setAutoFlag(true);
        swmsBasicPlantInfo.setPlantName("未知车间名称");
        swmsBasicPlantInfoMapper.insert(swmsBasicPlantInfo);
        log.info("车间新增结束=========>:{}", swmsBasicPlantInfo.toString());
        return swmsBasicPlantInfo;
    }

    /**
     * @Description:    物料车间-更新
     * @Author: River
     * @Date: 2020/1/11 16:18
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean update(SwmsBasicPlantInfo swmsBasicPlantInfo) {
        String code = swmsBasicPlantInfo.getPlantCode();
        String name = swmsBasicPlantInfo.getPlantName();
        SwmsBasicPlantInfo queryById = swmsBasicPlantInfoMapper.selectById(swmsBasicPlantInfo.getId());
        if (queryById == null) {
            throw new DataDuplicateException("更新失败,不存在的物料供应车间:" + swmsBasicPlantInfo.getId());
        }
        if (StringUtils.isNotEmpty(code) && !queryById.getPlantCode().equals(code)) {
            QueryWrapper<SwmsBasicPlantInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("plant_code", code);
            List<SwmsBasicPlantInfo> queryByCode = swmsBasicPlantInfoMapper.selectList(queryWrapper);
            if (queryByCode.size() > 0) {
                throw new DataDuplicateException("更新失败,物料供应车间代号同名:" + code);
            }
        }
        return swmsBasicPlantInfoMapper.updateById(swmsBasicPlantInfo) > 0;
    }

    /**
     * @Description:    物料车间-删除
     * @Author: River
     * @Date: 2020/1/11 16:30
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean delete(Integer id) {
        try {
            return swmsBasicPlantInfoMapper.deleteById(id) > 0;
        } catch (Exception e) {
            throw new DataAssociationException(String.format("删除失败,该数据正在被使用:%d,如要删除请选择强力删除,将删除包括关联的台账,出库信息", id));
        }
    }
}
