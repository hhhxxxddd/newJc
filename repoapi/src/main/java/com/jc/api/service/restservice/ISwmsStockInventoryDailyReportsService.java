package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.api.entity.SwmsStockInventoryDailyReports;
import com.jc.api.entity.SwmsStockInventoryDailyReportsTotals;

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
}
