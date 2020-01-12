package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicMaterialSubType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 物料子类型表 Mapper 接口
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-10
 */
@Repository
@Mapper
public interface SwmsBasicMaterialSubTypeMapper extends BaseMapper<SwmsBasicMaterialSubType> {

    /**
     * @Description:    物料子类型-不分页
     * @Author: River
     * @Date: 2020/1/11 19:45
     {@link List< SwmsBasicMaterialSubType>}
     java.util.List<com.jc.api.entity.SwmsBasicMaterialSubType>
     **/
    List<SwmsBasicMaterialSubType> findList(@Param(value = "subTypeName")String subTypeName);

    /**
     * @Description:    物料子类型-分页
     * @Author: River
     * @Date: 2020/1/12 14:54
     {@link IPage< SwmsBasicMaterialSubType>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicMaterialSubType>
     **/
    IPage<SwmsBasicMaterialSubType> selectPageVo(Page<?> page, @Param(value = "subTypeName")String subTypeName);
}
