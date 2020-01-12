package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsStockInLedgers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 物料入库台账表 Mapper 接口
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-10
 */
@Repository
@Mapper
public interface SwmsStockInLedgersMapper extends BaseMapper<SwmsStockInLedgers> {
    List<SwmsStockInLedgers> selectEntity(@Param(value = "materialCode") String materialCode);

    IPage<SwmsStockInLedgers> selectEntityByPage(Page<?> page, @Param(value = "materialCode") String materialCode);
}
