package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.api.entity.po.MaterialInfoSupplier;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author XudongHu
 * @apiNote 物料供货商mapper
 * @className MaterialInfoSupplierMapper
 * @modifier
 * @since 19.12.8日1:41
 */
@Repository
@Mapper
public interface MaterialInfoSupplierMapper extends BaseMapper<MaterialInfoSupplier> {
}
