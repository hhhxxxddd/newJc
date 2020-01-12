package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicDeliveryTypeInfo;
import com.jc.api.entity.SwmsBasicPlantInfo;

import java.util.List;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/12 17:55
 * @Description:    出库类别Service层
 */
public interface ISwmsBasicDeliveryTypeInfoService {

    /**
     * @Description:    出库类别-不分页
     * @Author: River
     * @Date: 2020/1/12 17:56
     {@link List< SwmsBasicPlantInfo>}
     java.util.List<com.jc.api.entity.SwmsBasicPlantInfo>
     **/
    List<SwmsBasicDeliveryTypeInfo> getAll(String deliveryTypeName);

    /**
     * @Description:    出库类别-分页
     * @Author: River
     * @Date: 2020/1/12 14:47
    {@link IPage < SwmsBasicDeliveryTypeInfo>}
    com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicDeliveryTypeInfo>
     **/
    IPage<SwmsBasicDeliveryTypeInfo> getAllByPage(Page page, String deliveryTypeName);

    /**
     * @Description:    出库类别-新增
     * @Author: River
     * @Date: 2020/1/11 15:37
    {@link Boolean}
    java.lang.Boolean
     **/
    Boolean add(SwmsBasicDeliveryTypeInfo swmsBasicDeliveryTypeInfo);

    /**
     * @Description:    出库类别-更新
     * @Author: River
     * @Date: 2020/1/11 15:37
    {@link Boolean}
    java.lang.Boolean
     **/
    Boolean update(SwmsBasicDeliveryTypeInfo swmsBasicDeliveryTypeInfo);

    /**
     * @Description:    出库类别-删除
     * @Author: River
     * @Date: 2020/1/11 15:38
    {@link Boolean}
    java.lang.Boolean
     **/
    Boolean delete(Integer id);

    /**
     * @Description:    出库类别-删除
     * @Author: River
     * @Date: 2020/1/12 14:41
    {@link Boolean}
    java.lang.Boolean
     **/
    Boolean batchDelete(Set<String> ids);
}
