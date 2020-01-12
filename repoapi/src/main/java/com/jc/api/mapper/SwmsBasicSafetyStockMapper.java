package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicMaterialSubType;
import com.jc.api.entity.SwmsBasicSafetyStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 安全库存设置表 Mapper 接口
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-10
 */
public interface SwmsBasicSafetyStockMapper extends BaseMapper<SwmsBasicSafetyStock> {

    /**
     * @Description:    安全库存-不分页
     * @Author: River
     * @Date: 2020/1/12 16:39
     {@link List< SwmsBasicSafetyStock>}
     java.util.List<com.jc.api.entity.SwmsBasicSafetyStock>
     **/
    List<SwmsBasicSafetyStock> findList(@Param(value = "materialName") String materialName);

    /**
     * @Description:    安全库存-分页
     * @Author: River
     * @Date: 2020/1/12 16:40
     {@link IPage< SwmsBasicSafetyStock>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicSafetyStock>
     **/
    IPage<SwmsBasicSafetyStock> selectPage(Page<?> page, @Param(value = "materialName")String materialName);
}
