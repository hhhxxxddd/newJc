package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.api.entity.po.StockOutRecordHead;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author XudongHu
 * @className StockOutHeadMapper
 * @apiNote 出库表头
 * @modifer
 * @since 2019/10/31日12:06
 */
@Repository
@Mapper
public interface StockOutRecordHeadMapper extends BaseMapper<StockOutRecordHead> {
}
