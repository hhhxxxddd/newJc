package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.api.entity.po.MaterialType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author XudongHu
 * @className MaterialTypeMapper
 * @apiNote 物料类型 自关联
 * @modifer
 * @since 2019/10/30日15:35
 */
@Repository
@Mapper
public interface MaterialTypeMapper extends BaseMapper<MaterialType> {
}
