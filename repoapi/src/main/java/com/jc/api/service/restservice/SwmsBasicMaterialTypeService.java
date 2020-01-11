package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicMaterialType;

import java.util.List;

/**
 * @Auther: River
 * @Date: 2020/1/11 17:08
 * @Description:    物料类型Service层
 */
public interface SwmsBasicMaterialTypeService {

    /**
     * @Description:    物料类型-不分页
     * @Author: River
     * @Date: 2020/1/11 17:10
     * {@link List< SwmsBasicMaterialType>}
     * java.util.List<com.jc.api.entity.SwmsBasicMaterialType>
     **/
    List<SwmsBasicMaterialType> getAll(SwmsBasicMaterialType swmsBasicMaterialType);

    /**
     * @Description:    物料类型-分页
     * @Author: River
     * @Date: 2020/1/11 17:10
     {@link IPage< SwmsBasicMaterialType>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicMaterialType>
     **/
    IPage<SwmsBasicMaterialType> getAllByPage(Page page, SwmsBasicMaterialType swmsBasicMaterialType);

    /**
     * @Description:    物料类型-新增
     * @Author: River
     * @Date: 2020/1/11 17:11
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean add(SwmsBasicMaterialType swmsBasicMaterialType);

    /**
     * @Description:    物料类型-自动新增
     * @Author: River
     * @Date: 2020/1/11 17:12
     {@link Boolean}
     java.lang.Boolean
     **/
    SwmsBasicMaterialType autoAdd(SwmsBasicMaterialType swmsBasicMaterialType);

    /**
     * @Description:    物料类型-更新
     * @Author: River
     * @Date: 2020/1/11 17:13
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean update(SwmsBasicMaterialType swmsBasicMaterialType);

    /**
     * @Description:    物料类型-删除
     * @Author: River
     * @Date: 2020/1/11 17:13
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean delete(Integer id);
}
