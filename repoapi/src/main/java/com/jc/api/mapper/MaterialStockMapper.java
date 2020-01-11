package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.api.entity.po.MaterialStock;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author XudongHu
 * @className MaterialStockMapper
 * @apiNote 物料库存po
 * @modifer
 * @since 2019/10/30日15:33
 */
@Repository
@Mapper
public interface MaterialStockMapper extends BaseMapper<MaterialStock> {
}
