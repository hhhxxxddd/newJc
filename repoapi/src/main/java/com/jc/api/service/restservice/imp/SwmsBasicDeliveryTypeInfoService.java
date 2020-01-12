package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicDeliveryAddressInfo;
import com.jc.api.entity.SwmsBasicDeliveryTypeInfo;
import com.jc.api.entity.SwmsBasicPlantInfo;
import com.jc.api.exception.custom.DataAssociationException;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.exception.custom.ParamVerifyException;
import com.jc.api.mapper.SwmsBasicDeliveryTypeInfoMapper;
import com.jc.api.service.restservice.ISwmsBasicDeliveryTypeInfoService;
import com.netflix.discovery.converters.Auto;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/12 17:57
 * @Description:    出库类别Service实现层
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class SwmsBasicDeliveryTypeInfoService implements ISwmsBasicDeliveryTypeInfoService {

    @Autowired
    private SwmsBasicDeliveryTypeInfoMapper swmsBasicDeliveryTypeInfoMapper;

    /**
     * @Description:    出库类别-不分页
     * @Author: River
     * @Date: 2020/1/12 18:05
     {@link List< SwmsBasicDeliveryTypeInfo>}
     java.util.List<com.jc.api.entity.SwmsBasicDeliveryTypeInfo>
     **/
    @Override
    public List<SwmsBasicDeliveryTypeInfo> getAll(String deliveryTypeName) {
        QueryWrapper<SwmsBasicDeliveryTypeInfo> queryWrapper = new QueryWrapper<>();
        if (!StringUtil.isNullOrEmpty(deliveryTypeName)) {
            queryWrapper.likeRight("delivery_type_name", deliveryTypeName);
        }
        return swmsBasicDeliveryTypeInfoMapper.selectList(queryWrapper);
    }

    /**
     * @Description:    出库类别-分页
     * @Author: River
     * @Date: 2020/1/12 18:06
     {@link IPage< SwmsBasicDeliveryTypeInfo>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicDeliveryTypeInfo>
     **/
    @Override
    public IPage<SwmsBasicDeliveryTypeInfo> getAllByPage(Page page, String deliveryTypeName) {
        QueryWrapper<SwmsBasicDeliveryTypeInfo> queryWrapper = new QueryWrapper<>();
        if (!StringUtil.isNullOrEmpty(deliveryTypeName)) {
            queryWrapper.likeRight("delivery_type_name", deliveryTypeName);
        }
        return swmsBasicDeliveryTypeInfoMapper.selectPage(page, queryWrapper);
    }

    /**
     * @Description:    出库类别-新增
     * @Author: River
     * @Date: 2020/1/12 18:06
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean add(SwmsBasicDeliveryTypeInfo swmsBasicDeliveryTypeInfo) {
        QueryWrapper<SwmsBasicDeliveryTypeInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delivery_type_name", swmsBasicDeliveryTypeInfo.getDeliveryTypeName());
        if (swmsBasicDeliveryTypeInfoMapper.selectOne(queryWrapper) != null) {
            throw new DataDuplicateException("新增失败,出库类别同名:" + swmsBasicDeliveryTypeInfo.getDeliveryTypeName());
        }
        return swmsBasicDeliveryTypeInfoMapper.insert(swmsBasicDeliveryTypeInfo) > 0;
    }

    /**
     * @Description:    出库类别-更新
     * @Author: River
     * @Date: 2020/1/12 18:06
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean update(SwmsBasicDeliveryTypeInfo swmsBasicDeliveryTypeInfo) {
        if (StringUtils.isEmpty(swmsBasicDeliveryTypeInfo.getId())) {
            throw new ParamVerifyException("传入数据的ID为空");
        }
        SwmsBasicDeliveryTypeInfo queryBuId = swmsBasicDeliveryTypeInfoMapper.selectById(swmsBasicDeliveryTypeInfo);
        if (queryBuId == null) {
            throw new DataDuplicateException("出库类别不存在，请确认后重新提交");
        }
        if (StringUtils.isEmpty(swmsBasicDeliveryTypeInfo.getDeliveryTypeName())) {
            throw new ParamVerifyException("传入的出库类别名称为空，请检查后输入");
        }
        if (!swmsBasicDeliveryTypeInfo.getDeliveryTypeName().equals(queryBuId.getDeliveryTypeName())) {
            QueryWrapper<SwmsBasicDeliveryTypeInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("delivery_type_name", swmsBasicDeliveryTypeInfo.getDeliveryTypeName());
            List<SwmsBasicDeliveryTypeInfo> queryByName = swmsBasicDeliveryTypeInfoMapper.selectList(queryWrapper);
            if (queryByName.size() > 0) {
                throw new DataDuplicateException("更新失败，出库类别名称重复：" + swmsBasicDeliveryTypeInfo.getDeliveryTypeName());
            }
        }
        return swmsBasicDeliveryTypeInfoMapper.updateById(swmsBasicDeliveryTypeInfo) > 0;
    }

    /**
     * @Description:    出库类别-删除
     * @Author: River
     * @Date: 2020/1/12 18:07
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean delete(Integer id) {
        try {
            return swmsBasicDeliveryTypeInfoMapper.deleteById(id) > 0;
        } catch (Exception e) {
            throw new DataAssociationException(String.format("删除失败,该数据正在被使用:%d,如要删除请选择强力删除,将删除包括关联的台账,出库信息", id));
        }
    }

    /**
     * @Description:    出库类别-批量删除
     * @Author: River
     * @Date: 2020/1/12 18:07
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean batchDelete(Set<String> ids) {
        try {
            return swmsBasicDeliveryTypeInfoMapper.deleteBatchIds(ids) == ids.size();
        } catch (Exception e) {
            throw new DataAssociationException("删除失败,数据正在被使用");
        }
    }
}
