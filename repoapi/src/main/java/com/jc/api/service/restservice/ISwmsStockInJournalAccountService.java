package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsStockInJournalAccount;

import java.util.List;

/**
 * @author XudongHu
 * @apiNote 入库流水接口
 * @className ISwmsStockInJournalAccountService
 * @modifier
 * @since 20.1.12日15:51
 */
public interface ISwmsStockInJournalAccountService {
    /**
     * 新增入库记录
     * @param materialCode 物料编码
     * @param createPerson 创建人
     * @return 是否成功
     */
    Boolean insert(String materialCode, String createPerson);

    /**
     * 查询所有 - 物料编码模糊
     * @param materialCode
     * @return
     */
    List<SwmsStockInJournalAccount> getAll(String materialCode);

    /**
     * 查询所有 - 物料编码模糊/分页
     * @param page
     * @param materialCode
     * @return
     */
    IPage getAllByPage(Page page, String materialCode);


}
