package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.api.entity.SwmsBasicSupplierInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 物料供应商表 Mapper 接口
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-10
 */
@Repository
@Mapper
public interface SwmsBasicSupplierInfoMapper extends BaseMapper<SwmsBasicSupplierInfo> {

}
