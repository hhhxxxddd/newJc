package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.api.entity.SwmsStockInventoryDailyReports;
import com.jc.api.entity.SwmsStockInventoryDailyReportsTotals;
import com.jc.api.mapper.SwmsStockInventoryDailyReportsMapper;
import com.jc.api.mapper.SwmsStockInventoryDailyReportsTotalsMapper;
import com.jc.api.service.restservice.ISwmsStockInventoryDailyReportsService;
import com.jc.api.utils.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 物料库存日报表 服务实现类
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-13
 */
@Service
public class SwmsStockInventoryDailyReportsServiceImpl extends ServiceImpl<SwmsStockInventoryDailyReportsMapper, SwmsStockInventoryDailyReports> implements ISwmsStockInventoryDailyReportsService {

    @Autowired
    SwmsStockInventoryDailyReportsMapper inventoryDailyReportsMapper;
    @Autowired
    SwmsStockInventoryDailyReportsTotalsMapper totalsMapper;

    @Override
    public IPage<SwmsStockInventoryDailyReports> selectByPage(Page page, Integer typeId, Integer subTypeId, String startTime, String endTime) {
        return inventoryDailyReportsMapper.selectPageVo(page,typeId,subTypeId,startTime,endTime);
    }
/*
    @Override
    public void update(Long id, String comments) {
        inventoryDailyReportsMapper.updateComments(id,comments);
    }*/

    @Override
    public IPage<SwmsStockInventoryDailyReportsTotals> selectByPage1(Page page, Integer typeId, Integer subTypeId, String startTime, String endTime) {
        return totalsMapper.selectPageVo(page, typeId, subTypeId, startTime, endTime);
    }

    @Override
    public void update1(Long id, String comment) {
        totalsMapper.updateComments(id,comment);
    }

    @Override
    public List detail(Long id) {
        SwmsStockInventoryDailyReportsTotals totals = totalsMapper.selectById(id);
        if (totals == null)
            return null;
        LocalDateTime start = ComUtil.dateToLocalDateTime(totals.getStockDate()).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = ComUtil.dateToLocalDateTime(totals.getStockDate()).withHour(23).withMinute(59).withSecond(59);
        return totalsMapper.detail(totals.getMaterialNameCode(),ComUtil.localDateTimeToString(start,"yyyy-MM-dd HH:mm:ss"),ComUtil.localDateTimeToString(end,"yyyy-MM-dd HH:mm:ss"));
    }
}
