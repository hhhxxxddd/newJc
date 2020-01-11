package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicPlantInfo;

import java.util.List;

/**
 * @Auther: River
 * @Date: 2020/1/11 15:25
 * @Description: 车间Service层
 */
public interface ISwmsBasicPlantInfoService {

    /**
     * @Description:    查询所有-不分页
     * @Author: River
     * @Date: 2020/1/11 15:34
     {@link List< SwmsBasicPlantInfo>}
     java.util.List<com.jc.api.entity.SwmsBasicPlantInfo>
     **/
    List<SwmsBasicPlantInfo> getAll(SwmsBasicPlantInfo swmsBasicPlantInfo);

    /**
     * @Description:    查询所有-分页
     * @Author: River
     * @Date: 2020/1/11 15:35
     {@link List< SwmsBasicPlantInfo>}
     java.util.List<com.jc.api.entity.SwmsBasicPlantInfo>
     **/
    IPage<SwmsBasicPlantInfo> getAllByPage(Page page,SwmsBasicPlantInfo swmsBasicPlantInfo);

    /**
     * @Description:    新增
     * @Author: River
     * @Date: 2020/1/11 15:37
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean add(SwmsBasicPlantInfo swmsBasicPlantInfo);

    /**
     * @Description:    自动新增
     * @Author: River
     * @Date: 2020/1/11 15:40
     {@link SwmsBasicPlantInfo}
     com.jc.api.entity.SwmsBasicPlantInfo
     **/
    SwmsBasicPlantInfo autoAdd(SwmsBasicPlantInfo swmsBasicPlantInfo);

    /**
     * @Description:    更新
     * @Author: River
     * @Date: 2020/1/11 15:37
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean update(SwmsBasicPlantInfo swmsBasicPlantInfo);

    /**
     * @Description:    删除
     * @Author: River
     * @Date: 2020/1/11 15:38
     {@link Boolean}
     java.lang.Boolean
     **/
    Boolean delete(Integer id);

}
