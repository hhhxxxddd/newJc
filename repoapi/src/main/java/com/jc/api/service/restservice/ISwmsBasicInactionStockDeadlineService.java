package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicInactionStockDeadline;
import com.jc.api.entity.SwmsBasicMaterialSubType;

import java.util.List;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/12 18:13
 * @Description:    呆滞期限Service层
 */
public interface ISwmsBasicInactionStockDeadlineService {

    /**
     * @Description:    呆滞期限-不分页
     * @Author: River
     * @Date: 2020/1/11 17:52
    {@link List <  SwmsBasicMaterialSubType >}
    java.util.List<com.jc.api.entity.SwmsBasicInactionStockDeadline>
     **/
    List<SwmsBasicInactionStockDeadline> getAll(String subTypeName);

    /**
     * @Description:    呆滞期限-分页
     * @Author: River
     * @Date: 2020/1/11 17:53
    {@link IPage < SwmsBasicMaterialSubType>}
    com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicInactionStockDeadline>
     **/
    IPage<SwmsBasicInactionStockDeadline> getAllByPage(Page<SwmsBasicInactionStockDeadline> page, String subTypeName);

    /**
     * @Description:    呆滞期限-新增
     * @Author: River
     * @Date: 2020/1/11 17:54
    {@link Boolean}
    java.lang.Boolean
     **/
    Boolean add(SwmsBasicInactionStockDeadline swmsBasicInactionStockDeadline);

    /**
     * @Description:    呆滞期限-更新
     * @Author: River
     * @Date: 2020/1/11 17:55
    {@link Boolean}
    java.lang.Boolean
     **/
    Boolean update(SwmsBasicInactionStockDeadline swmsBasicInactionStockDeadline);

    /**
     * @Description:    呆滞期限-根据ID删除
     * @Author: River
     * @Date: 2020/1/11 17:55
    {@link Boolean}
    java.lang.Boolean
     **/
    Boolean delete(Integer id);

    /**
     * @Description:    批量删除
     * @Author: River 
     * @Date: 2020/1/12 18:41
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean batchDelete(Set<String> ids);
}
