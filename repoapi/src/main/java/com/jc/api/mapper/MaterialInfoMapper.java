package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.api.entity.po.MaterialInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author XudongHu
 * @className MaterialInfoMapper
 * @apiNote 物料基本信息po
 * @modifer
 * @since 2019/10/30日15:34
 */
@Repository
@Mapper
public interface MaterialInfoMapper extends BaseMapper<MaterialInfo> {
}
