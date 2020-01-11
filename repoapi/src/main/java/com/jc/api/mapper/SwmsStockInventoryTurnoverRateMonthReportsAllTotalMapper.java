package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.api.entity.SwmsStockInventoryTurnoverRateMonthReportsAllTotal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 物料库存周转率总合计报表 Mapper 接口
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-10
 */
@Repository
@Mapper
public interface SwmsStockInventoryTurnoverRateMonthReportsAllTotalMapper extends BaseMapper<SwmsStockInventoryTurnoverRateMonthReportsAllTotal> {

}
