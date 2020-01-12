package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsStockOutLedgers;

import java.util.List;

/**
 * @author XudongHu
 * @apiNote 出库台账
 * @className ISwmsStockOutLedgersService
 * @modifier
 * @since 20.1.12日21:30
 */
public interface ISwmsStockOutLedgersService {
    /**
     * 生成出库台账 (出库流水之后生成)
     * @param swmsStockOutRecordDetailId 出库明细id
     * @return
     */
    SwmsStockOutLedgers generate(String swmsStockOutRecordDetailId,String swmsStockInRecordAccountId,String swmsStockOutJournalAccountId);

    /**
     * 查询所有 -名称模糊
     * @param materialCode
     * @return
     */
    List<SwmsStockOutLedgers> getAll(String materialCode);

    /**
     * 查询所有 -分页 名称模糊
     * @param page
     * @param materialCode
     * @return
     */
    IPage<SwmsStockOutLedgers> getAllByPage(Page page,String materialCode);
}
