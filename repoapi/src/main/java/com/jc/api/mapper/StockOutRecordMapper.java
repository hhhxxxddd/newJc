package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.api.entity.po.StockOutRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author XudongHu
 * @className StockOutRecordMapper
 * @apiNote 出库详情po
 * @modifer
 * @since 2019/10/31日12:05
 */
@Repository
@Mapper
public interface StockOutRecordMapper extends BaseMapper<StockOutRecord> {
}

