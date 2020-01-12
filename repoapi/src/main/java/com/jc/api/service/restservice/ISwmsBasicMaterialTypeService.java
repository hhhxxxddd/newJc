package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicMaterialType;

import java.util.List;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/11 17:08
 * @Description:    物料类型Service层
 */
public interface ISwmsBasicMaterialTypeService {

    /**
     * @Description:    物料类型-不分页
     * @Author: River
     * @Date: 2020/1/11 17:10
     * {@link List< SwmsBasicMaterialType>}
     * java.util.List<com.jc.api.entity.SwmsBasicMaterialType>
     **/
    List<SwmsBasicMaterialType> getAll(String typeName);

    /**
     * @Description:    物料类型-分页
     * @Author: River
     * @Date: 2020/1/11 17:10
     {@link IPage< SwmsBasicMaterialType>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicMaterialType>
     **/
    IPage<SwmsBasicMaterialType> getAllByPage(Page page, String typeName);

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

    /**
     * @Description:    物料类型-删除
     * @Author: River
     * @Date: 2020/1/12 14:41
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean batchDelete(Set<String> ids);
}
