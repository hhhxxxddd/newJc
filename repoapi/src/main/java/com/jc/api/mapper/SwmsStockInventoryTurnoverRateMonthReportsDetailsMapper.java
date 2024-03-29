package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.api.entity.SwmsStockInventoryTurnoverRateMonthReportsDetails;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 物料库存周转率明细报表 Mapper 接口
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-10
 */
@Repository
@Mapper
public interface SwmsStockInventoryTurnoverRateMonthReportsDetailsMapper extends BaseMapper<SwmsStockInventoryTurnoverRateMonthReportsDetails> {

}
