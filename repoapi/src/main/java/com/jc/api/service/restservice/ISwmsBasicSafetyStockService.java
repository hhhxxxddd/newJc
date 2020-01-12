package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicSafetyStock;

import java.util.List;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/12 15:39
 * @Description:    安全库存Service层
 */
public interface ISwmsBasicSafetyStockService {

    /**
     * @Description:
     * @Author: River 
     * @Date: 2020/1/12 15:46
     {@link List< SwmsBasicSafetyStock>}
     java.util.List<com.jc.api.entity.SwmsBasicSafetyStock>
     **/
    List<SwmsBasicSafetyStock> getAll(String materialName);

    /**
     * @Description:    安全库存-分页
     * @Author: River
     * @Date: 2020/1/12 15:47
     {@link IPage< SwmsBasicSafetyStock>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicSafetyStock>
     **/
    IPage<SwmsBasicSafetyStock> getAllByPage(Page page, String materialName);

    /**
     * @Description:    安全库存-新增
     * @Author: River
     * @Date: 2020/1/12 15:48
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean add(SwmsBasicSafetyStock swmsBasicSafetyStock);

    /**
     * @Description:    安全库存-更新
     * @Author: River
     * @Date: 2020/1/12 15:48
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean update(SwmsBasicSafetyStock swmsBasicSafetyStock);

    /**
     * @Description:    安全库存-删除
     * @Author: River
     * @Date: 2020/1/12 15:48
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean delete(Integer id);

    /**
     * @Description:    安全库存-批量删除
     * @Author: River
     * @Date: 2020/1/12 15:49
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean batchDelete(Set<String> ids);
}
