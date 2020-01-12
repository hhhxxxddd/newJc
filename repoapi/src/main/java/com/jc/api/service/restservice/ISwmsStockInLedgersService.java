package com.jc.api.service.restservice;

/**
 * @author XudongHu
 * @apiNote 入库台账接口
 * @className ISwmsStockInLedgersService
 * @modifier
 * @since 20.1.12日16:40
 */
public interface ISwmsStockInLedgersService {
    /**
     * 新增台账
     * 解析并新增
     * @param SwmsStockInJournalAccountId 入库台账id
     * @return boolean
     */
    void parsingAndInsert(String SwmsStockInJournalAccountId);

}
