package com.jc.api.modules.base.auto.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.api.modules.base.auto.entity.BasicSupplierInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author XudongHu
 * @apiNote 物料供应商mapper
 * @className BasicSupplierInfoMapper
 * @modifier
 * @since 20.1.9日15:18
 */
@Repository
@Mapper
public interface BasicSupplierInfoMapper extends BaseMapper<BasicSupplierInfo> {
}
