package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicDeliveryAddressInfo;
import com.jc.api.entity.SwmsBasicMaterialType;

import java.util.List;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/12 15:01
 * @Description:    出库点Service层
 */
public interface ISwmsBasicDeliveryAddressInfoService {

    /**
     * @Description:    出库点-不分页
     * @Author: River
     * @Date: 2020/1/12 15:03
     {@link List< SwmsBasicDeliveryAddressInfo>}
     java.util.List<com.jc.api.entity.SwmsBasicDeliveryAddressInfo>
     **/
    List<SwmsBasicDeliveryAddressInfo> getAll(String deliveryAddressName);

    /**
     * @Description:    出库点-分页
     * @Author: River
     * @Date: 2020/1/12 15:03
     {@link IPage< SwmsBasicDeliveryAddressInfo>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicDeliveryAddressInfo>
     **/
    IPage<SwmsBasicDeliveryAddressInfo> getAllByPage(Page page, String deliveryAddressName);

    /**
     * @Description:    出库点-新增
     * @Author: River
     * @Date: 2020/1/12 15:03
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean add(SwmsBasicDeliveryAddressInfo swmsBasicDeliveryAddressInfo);

    /**
     * @Description:    出库点-更新
     * @Author: River
     * @Date: 2020/1/12 15:04
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean update(SwmsBasicDeliveryAddressInfo swmsBasicDeliveryAddressInfo);

   /**
    * @Description:     出库点-删除
    * @Author: River
    * @Date: 2020/1/12 15:04
    {@link Boolean}
    java.lang.Boolean
    **/
    Boolean delete(Integer id);

    /**
     * @Description:    出库点-批量删除
     * @Author: River
     * @Date: 2020/1/12 15:05
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean batchDelete(Set<String> ids);


    /**
     * 根据type获取出库点
     * @param type
     * @return
     */
    List getByType(Integer type);
}
