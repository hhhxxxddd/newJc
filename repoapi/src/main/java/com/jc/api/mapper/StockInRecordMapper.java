package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.api.entity.po.StockInRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author XudongHu
 * @className StockInRecordMapper
 * @apiNote 入库记录记录mapper
 * @modifer
 * @since 2019/10/31日4:09
 */
@Repository
@Mapper
public interface StockInRecordMapper extends BaseMapper<StockInRecord> {
}
