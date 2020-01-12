package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicDeliveryAddressInfo;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.exception.custom.ParamVerifyException;
import com.jc.api.mapper.SwmsBasicDeliveryAddressInfoMapper;
import com.jc.api.service.restservice.ISwmsBasicDeliveryAddressInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/12 15:05
 * @Description:    出库点Service实现层
 */
public class SwmsBasicDeliveryAddressInfoService implements ISwmsBasicDeliveryAddressInfoService {

    @Autowired
    private SwmsBasicDeliveryAddressInfoMapper swmsBasicDeliveryAddressInfoMapper;
    
    /**
     * @Description:
     * @Author: River 
     * @Date: 2020/1/12 15:06
     {@link List< SwmsBasicDeliveryAddressInfo>}
     java.util.List<com.jc.api.entity.SwmsBasicDeliveryAddressInfo>
     **/
    @Override
    public List<SwmsBasicDeliveryAddressInfo> getAll(String deliveryAddressName) {
        QueryWrapper<SwmsBasicDeliveryAddressInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(deliveryAddressName)) {
            queryWrapper.likeRight("delivery_address_name", deliveryAddressName);
        }
        return swmsBasicDeliveryAddressInfoMapper.selectList(queryWrapper);
    }

    /**
     * @Description:    出库点-分页
     * @Author: River 
     * @Date: 2020/1/12 15:07
     {@link IPage< SwmsBasicDeliveryAddressInfo>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicDeliveryAddressInfo>
     **/
    @Override
    public IPage<SwmsBasicDeliveryAddressInfo> getAllByPage(Page page, String deliveryAddressName) {
        QueryWrapper<SwmsBasicDeliveryAddressInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(deliveryAddressName)) {
            queryWrapper.likeRight("delivery_address_name", deliveryAddressName);
        }
        return swmsBasicDeliveryAddressInfoMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Boolean add(SwmsBasicDeliveryAddressInfo swmsBasicDeliveryAddressInfo) {
        QueryWrapper<SwmsBasicDeliveryAddressInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delivery_address_name", swmsBasicDeliveryAddressInfo.getDeliveryAddressName());
        List<SwmsBasicDeliveryAddressInfo> queryBuName = swmsBasicDeliveryAddressInfoMapper.selectList(queryWrapper);
        if (queryBuName.size() > 0) {
            throw new DataDuplicateException("出库点名称重复：" + swmsBasicDeliveryAddressInfo.getDeliveryAddressName());
        }
        return swmsBasicDeliveryAddressInfoMapper.insert(swmsBasicDeliveryAddressInfo) > 0;
    }

    @Override
    public Boolean update(SwmsBasicDeliveryAddressInfo swmsBasicDeliveryAddressInfo) {
        if (StringUtils.isEmpty(swmsBasicDeliveryAddressInfo.getId())) {
            throw new ParamVerifyException("传入数据的ID为空");
        }
        SwmsBasicDeliveryAddressInfo queryBuId = swmsBasicDeliveryAddressInfoMapper.selectById(swmsBasicDeliveryAddressInfo);
        if (queryBuId == null) {
            throw new DataDuplicateException("出库点不存在，请确认后重新提交");
        }
        QueryWrapper<SwmsBasicDeliveryAddressInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delivery_address_name", swmsBasicDeliveryAddressInfo.getDeliveryAddressName());
        List<SwmsBasicDeliveryAddressInfo> queryByName = swmsBasicDeliveryAddressInfoMapper.selectList(queryWrapper);
        if (queryByName.size() > 0) {

        }
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }

    @Override
    public Boolean batchDelete(Set<String> ids) {
        return null;
    }
}
