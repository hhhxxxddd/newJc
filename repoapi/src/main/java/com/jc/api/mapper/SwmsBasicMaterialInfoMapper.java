package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicMaterialInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 物料信息表 Mapper 接口
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-10
 */
@Repository
@Mapper
public interface SwmsBasicMaterialInfoMapper extends BaseMapper<SwmsBasicMaterialInfo> {
    List<SwmsBasicMaterialInfo> selectByNameLike(@Param(value = "materialName")String materialName);

    IPage<SwmsBasicMaterialInfo> selectPageVo(Page<?> page, @Param(value = "materialName")String materialName);
}
