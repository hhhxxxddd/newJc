package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.api.entity.SwmsStockInLedgersDayReports;

/**
 * <p>
 * 物料入库日台账表 服务类
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-13
 */
public interface ISwmsStockInLedgersDayReportsService extends IService<SwmsStockInLedgersDayReports> {

    IPage<SwmsStockInLedgersDayReports> selectByPage(Page page,Integer typeId,Integer subTypeId,Integer supplierId,String startTime,String endTime);
}
