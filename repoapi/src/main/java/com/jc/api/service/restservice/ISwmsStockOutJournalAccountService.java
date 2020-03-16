package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsStockOutJournalAccount;

import java.util.List;

/**
 * @author XudongHu
 * @apiNote 出库流水接口
 * @className ISwmsStockOutJournalAccountService
 * @modifier
 * @since 20.1.12日20:49
 */
public interface ISwmsStockOutJournalAccountService {
    /**
     * 生成出库流水
     * <p>
     * 在出库完成之后
     *
     * @param swmsStockOutRecordDetailId 出库详情id
     * @return
     */
    SwmsStockOutJournalAccount generate(String swmsStockOutRecordDetailId);

    /**
     * 生成出库流水-测试接口
     * @param repoInAccountId 入库台账id
     * @return 出库流水
     */
    SwmsStockOutJournalAccount testGenerate(String repoInAccountId);


    /**
     * 查询所有出库流水
     *
     * @param materialCode 物料编码
     * @return
     */
    List<SwmsStockOutJournalAccount> getAll(String materialCode);

    /**
     * 出库上报
     * @param stockOutRecordHeadCode
     * @param materialCode
     */
    void outPost(String stockOutRecordHeadCode, String materialCode);

    /**
     * 出库完成上报
     * @param stockOutRecordHeadCode
     */
    void outFinished(String stockOutRecordHeadCode);

    /**
     * 查询所有出库流水 分页/物料编码模糊
     *
     * @param materialCode 物料编码
     * @return
     */
    IPage<SwmsStockOutJournalAccount> getAllByPage(Page page, String materialCode);
}
