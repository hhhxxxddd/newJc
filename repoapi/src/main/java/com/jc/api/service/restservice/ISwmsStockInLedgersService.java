package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsStockInLedgers;

import java.util.List;

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

    /**
     * 查询所有-名称模糊
     * @param materialCode
     * @return
     */
    List<SwmsStockInLedgers> getAll(String materialCode);

    /**
     * 查询所有-名称模糊/分页
     * @param materialCode
     * @return
     */
    IPage<SwmsStockInLedgers> getAllByPage(Page page, String materialCode);
}
