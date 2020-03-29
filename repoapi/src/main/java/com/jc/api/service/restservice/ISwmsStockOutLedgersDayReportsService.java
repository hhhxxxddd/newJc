package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.api.entity.SwmsStockInLedgersDayReports;
import com.jc.api.entity.SwmsStockOutLedgersDayReports;

/**
 * <p>
 * 物料出库日台账表 服务类
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-13
 */
public interface ISwmsStockOutLedgersDayReportsService extends IService<SwmsStockOutLedgersDayReports> {
    IPage<SwmsStockOutLedgersDayReports> selectByPage(Page page, Integer typeId, Integer subTypeId, Integer deptId, String startTime, String endTime);

}
