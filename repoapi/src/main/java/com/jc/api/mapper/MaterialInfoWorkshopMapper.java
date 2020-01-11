package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.api.entity.po.MaterialInfoWorkshop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author XudongHu
 * @apiNote 物料供货商mapper
 * @className MaterialInfoWorkshopMapper
 * @modifier
 * @since 19.12.8日1:42
 */
@Repository
@Mapper
public interface MaterialInfoWorkshopMapper  extends BaseMapper<MaterialInfoWorkshop> {
}
