package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.api.entity.SwmsStockInLedgersDayReports;
import com.jc.api.entity.SwmsStockInventoryDailyReports;
import com.jc.api.entity.SwmsStockInventoryDailyReportsTotals;
import com.jc.api.entity.SwmsStockOutLedgersDayReports;

import java.util.List;

/**
 * <p>
 * 物料库存日报表 服务类
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-13
 */
public interface ISwmsStockInventoryDailyReportsService extends IService<SwmsStockInventoryDailyReports> {

    IPage<SwmsStockInventoryDailyReports> selectByPage(Page page, Integer typeId, Integer subTypeId, String startTime, String endTime);

    /*void update(Long id, String comments);*/

    IPage<SwmsStockInventoryDailyReportsTotals> selectByPage1(Page page, Integer typeId, Integer subTypeId, String startTime, String endTime);

    void update1(Long id,String comment);

    List detail(Long id);

    //每天由新松接口写入入库、出库流水后，进行解析时，修改本表数据；
    //物料按供应商统计计算。
    void updateDailyRecord(SwmsStockInLedgersDayReports in, SwmsStockOutLedgersDayReports out);
}
