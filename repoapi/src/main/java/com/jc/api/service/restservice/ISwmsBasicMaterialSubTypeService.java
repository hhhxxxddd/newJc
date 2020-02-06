package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicMaterialSubType;

import java.util.List;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/11 17:49
 * @Description:    物料子类型Service层
 */
public interface ISwmsBasicMaterialSubTypeService {

    /**
     * @Description:    物料子类型-不分页
     * @Author: River
     * @Date: 2020/1/11 17:52
     {@link List< SwmsBasicMaterialSubType>}
     java.util.List<com.jc.api.entity.SwmsBasicMaterialSubType>
     **/
    List<SwmsBasicMaterialSubType> getAll(String subTypeName);

    /**
     * @Description:    物料子类型-分页
     * @Author: River
     * @Date: 2020/1/11 17:53
     {@link IPage< SwmsBasicMaterialSubType>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicMaterialSubType>
     **/
    IPage<SwmsBasicMaterialSubType> getAllByPage(Page<SwmsBasicMaterialSubType> page, String subTypeName);

    /**
     * @Description:    物料子类型-新增
     * @Author: River
     * @Date: 2020/1/11 17:54
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean add(SwmsBasicMaterialSubType swmsBasicMaterialSubType);

    /**
     * @Description:    物料子类型-自动新增
     * @Author: River
     * @Date: 2020/1/11 17:54
     {@link SwmsBasicMaterialSubType}
     com.jc.api.entity.SwmsBasicMaterialSubType
     **/
    SwmsBasicMaterialSubType autoAdd(SwmsBasicMaterialSubType swmsBasicMaterialSubType);

    /**
     * @Description:    物料子类型-更新
     * @Author: River
     * @Date: 2020/1/11 17:55
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean update(SwmsBasicMaterialSubType swmsBasicMaterialSubType);

    /**
     * @Description:    物料子类型-根据ID删除
     * @Author: River 
     * @Date: 2020/1/11 17:55
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean delete(Integer id);

    /**
     * 批量删除
     * @param ids 主键数组
     * @return
     */
    Boolean batchDelete(Set<String> ids);

    /**
     * 根据主类查子类
     * @param type
     * @return
     */
    List getByType(Integer type);
}
