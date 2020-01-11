package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.api.entity.po.StockInRecordAccount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author XudongHu
 * @apiNote 入库台账mapper
 * @className StockInRecordAccountMapper
 * @modifier
 * @since 19.12.8日1:27
 */
@Repository
@Mapper
public interface StockInRecordAccountMapper extends BaseMapper<StockInRecordAccount> {
}
