package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicDeliveryAddressInfo;
import com.jc.api.exception.custom.DataAssociationException;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.exception.custom.ParamVerifyException;
import com.jc.api.mapper.SwmsBasicDeliveryAddressInfoMapper;
import com.jc.api.service.restservice.ISwmsBasicDeliveryAddressInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/12 15:05
 * @Description:    出库点Service实现层
 */
@Service
@Slf4j
@SuppressWarnings("all")
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
        try {
            QueryWrapper<SwmsBasicDeliveryAddressInfo> queryWrapper = new QueryWrapper<>();
            if (StringUtils.isNotEmpty(deliveryAddressName)) {
                queryWrapper.likeRight("delivery_address_name", deliveryAddressName);
            }
            return swmsBasicDeliveryAddressInfoMapper.selectPage(page, queryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description:    出库点-新增
     * @Author: River
     * @Date: 2020/1/12 15:23
     {@link Boolean}
     java.lang.Boolean
     **/
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

    /**
     * @Description:    出库点-更新
     * @Author: River
     * @Date: 2020/1/12 15:24
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean update(SwmsBasicDeliveryAddressInfo swmsBasicDeliveryAddressInfo) {
        if (StringUtils.isEmpty(swmsBasicDeliveryAddressInfo.getId())) {
            throw new ParamVerifyException("传入数据的ID为空");
        }
        SwmsBasicDeliveryAddressInfo queryBuId = swmsBasicDeliveryAddressInfoMapper.selectById(swmsBasicDeliveryAddressInfo);
        if (queryBuId == null) {
            throw new DataDuplicateException("出库点不存在，请确认后重新提交");
        }
        if (!swmsBasicDeliveryAddressInfo.getDeliveryAddressName().equals(queryBuId.getDeliveryAddressName())) {
            QueryWrapper<SwmsBasicDeliveryAddressInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("delivery_address_name", swmsBasicDeliveryAddressInfo.getDeliveryAddressName());
            List<SwmsBasicDeliveryAddressInfo> queryByName = swmsBasicDeliveryAddressInfoMapper.selectList(queryWrapper);
            if (queryByName.size() > 0) {
                throw new DataDuplicateException("更新失败，出库点名称重复：" + swmsBasicDeliveryAddressInfo.getDeliveryAddressName());
            }
        }
        return swmsBasicDeliveryAddressInfoMapper.updateById(swmsBasicDeliveryAddressInfo) > 0;
    }

    /**
     * @Description:    出库点-删除
     * @Author: River
     * @Date: 2020/1/12 15:24
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean delete(Integer id) {
        try {
            return swmsBasicDeliveryAddressInfoMapper.deleteById(id) > 0;
        } catch (Exception e) {
            throw new DataAssociationException(String.format("删除失败,该数据正在被使用:%d,如要删除请选择强力删除,将删除包括关联的台账,出库信息", id));
        }
    }

    /**
     * @Description:    出库点-批量删除
     * @Author: River
     * @Date: 2020/1/12 15:24
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean batchDelete(Set<String> ids) {
        try {
            return swmsBasicDeliveryAddressInfoMapper.deleteBatchIds(ids) == ids.size();
        } catch (Exception e) {
            throw new DataAssociationException("删除失败,数据正在被使用");
        }
    }
}
