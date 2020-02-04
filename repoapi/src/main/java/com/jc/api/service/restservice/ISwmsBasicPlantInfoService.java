package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicPlantInfo;

import java.util.List;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/11 15:25
 * @Description: 车间Service层
 */
public interface ISwmsBasicPlantInfoService {

    /**
     * @Description:    车间-不分页
     * @Author: River
     * @Date: 2020/1/11 15:34
     {@link List< SwmsBasicPlantInfo>}
     java.util.List<com.jc.api.entity.SwmsBasicPlantInfo>
     **/
    List<SwmsBasicPlantInfo> getAll(String plantName);

    /**
     * @Description:    车间-分页
     * @Author: River
     * @Date: 2020/1/12 14:47
     {@link IPage< SwmsBasicPlantInfo>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicPlantInfo>
     **/
    IPage<SwmsBasicPlantInfo> getAllByPage(Page page, String plantName);

    /**
     * @Description:    车间-新增
     * @Author: River
     * @Date: 2020/1/11 15:37
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean add(SwmsBasicPlantInfo swmsBasicPlantInfo);

    /**
     * @Description:    车间-自动新增
     * @Author: River
     * @Date: 2020/1/11 15:40
     {@link SwmsBasicPlantInfo}
     com.jc.api.entity.SwmsBasicPlantInfo
     **/
    SwmsBasicPlantInfo autoAdd(SwmsBasicPlantInfo swmsBasicPlantInfo);

    /**
     * @Description:    车间-更新
     * @Author: River
     * @Date: 2020/1/11 15:37
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean update(SwmsBasicPlantInfo swmsBasicPlantInfo);

    /**
     * @Description:    车间-删除
     * @Author: River
     * @Date: 2020/1/11 15:38
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean delete(Integer id);

    /**
     * @Description:    车间-删除
     * @Author: River
     * @Date: 2020/1/12 14:41
    {@link Boolean}
    java.lang.Boolean
     **/
    Boolean batchDelete(Set<String> ids);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    void deleteByIds(Integer[] ids);

}
