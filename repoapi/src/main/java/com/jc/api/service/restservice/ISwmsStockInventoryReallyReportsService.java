package com.jc.api.service.restservice;

import com.jc.api.entity.SwmsStockInventoryReallyReports;

/**
 * @author XudongHu
 * @apiNote 物料实际库存报表记录接口
 * @className ISwmsStockInventoryReallyReportsService
 * @modifier
 * @since 20.1.12日17:56
 */
public interface ISwmsStockInventoryReallyReportsService {
    /**
     * 自动新增库存 / 存在则修改库存,否则新增
     * @param entity
     * @return entity
     */
    SwmsStockInventoryReallyReports autoAdd(SwmsStockInventoryReallyReports entity);
}
