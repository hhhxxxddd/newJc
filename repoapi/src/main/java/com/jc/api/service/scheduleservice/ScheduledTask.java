package com.jc.api.service.scheduleservice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.api.entity.*;
import com.jc.api.mapper.*;
import com.jc.api.utils.ComUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ScheduledTask {

    @Autowired
    SwmsStockInventoryDailyReportsMapper dailyReportsMapper;
    @Autowired
    SwmsBasicMaterialInfoMapper materialInfoMapper;
    @Autowired
    SwmsStockInventoryStreamDailyReportsMapper streamDailyReportsMapper;
    @Autowired
    SwmsStockInventoryDailyReportsTotalsMapper totalsMapper;
    @Autowired
    SwmsStockInLedgersDayReportsMapper ledgersDayReportsMapper;

    @Value("${schedule.enable}")
    Boolean enable;

    /**
     * SWMS_stock_inventory_daily_reports
     * （定时服务1）每天凌晨0：05点，
     * 查询“库存日期-1”的所有数据记录，
     * 将现存量的值写入前日库存，当日入库和当日出库置0，
     * 现存量保持不变，备注置空，库存日期为当前日期；其他值保持不变；
     */
    @Scheduled(cron = "0 5 0 * * *")
    void swmsDailyReport() {
        if (enable) {
            log.info("开始物料库存日报表定时服务");
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime yesterdayStart = today.minusDays(1);
            yesterdayStart = yesterdayStart.withHour(0).withMinute(0).withSecond(0);
            LocalDateTime yesterdayEnd = yesterdayStart.withHour(23).withMinute(59).withSecond(59);
            List condi = dailyReportsMapper.selectList(new QueryWrapper<>());
            if(condi.size() == 0){
                QueryWrapper<SwmsStockInLedgersDayReports> queryWrapper = new QueryWrapper<>();
                queryWrapper.ge("created_day",ComUtil.localDateTimeToDate(yesterdayStart))
                        .le("created_day",ComUtil.localDateTimeToDate(yesterdayEnd));
                List<SwmsStockInLedgersDayReports> list = ledgersDayReportsMapper.selectList(queryWrapper);
                Map<Integer,SwmsStockInventoryDailyReports> map = new HashMap<>();
                list.forEach(e->{
                    if(map.containsKey(e.getMaterialNameCode())){
                        SwmsStockInventoryDailyReports temp = map.get(e);
                        temp.setCurrentInRecord(temp.getCurrentInRecord() + e.getWeight())
                                .setCurrentOutRecord(temp.getCurrentOutRecord() + e.getOutWeight())
                                .setWeight(temp.getCurrentInRecord() - temp.getCurrentOutRecord());
                        map.put(e.getMaterialNameCode(),temp);
                    }else{
                        SwmsStockInventoryDailyReports temp = new SwmsStockInventoryDailyReports();
                        temp.setMaterialName(e.getMaterialName())
                                .setMaterialNameCode(e.getMaterialNameCode())
                                .setMaterialSubTypeId(e.getMaterialSubTypeId())
                                .setMaterialSupplierCode(e.getMaterialSupplierCode())
                                .setMaterialTypeId(e.getMaterialTypeId())
                                .setMeasureUnit(e.getMeasureUnit())
                                .setCurrentInRecord(e.getWeight())
                                .setCurrentOutRecord(e.getOutWeight())
                                .setLastWeight(0f)
                                .setWeight(temp.getCurrentInRecord() - temp.getCurrentOutRecord())
                                .setStockDate(ComUtil.localDateTimeToDate(today));
                        map.put(e.getMaterialNameCode(),temp);
                    }
                });
                map.forEach((k,v)->{
                    dailyReportsMapper.insert(v);
                });
            }else {
                QueryWrapper<SwmsStockInventoryDailyReports> queryWrapper = new QueryWrapper<>();
                queryWrapper.ge("stock_date", ComUtil.localDateTimeToDate(yesterdayStart))
                        .le("stock_date", ComUtil.localDateTimeToDate(yesterdayEnd));

                List<SwmsStockInventoryDailyReports> reports = dailyReportsMapper.selectList(queryWrapper);
                for (int i = 0; i < reports.size(); i++) {
                    QueryWrapper<SwmsStockInventoryDailyReportsTotals> totalsQueryWrapper = new QueryWrapper<>();
                    totalsQueryWrapper.eq("material_name_code", reports.get(i).getMaterialNameCode())
                            .eq("stock_date", ComUtil.localDateTimeToDate(today));
                    List<SwmsStockInventoryDailyReportsTotals> totals = totalsMapper.selectList(totalsQueryWrapper);
                    if (totals.size() == 0) {
                        SwmsStockInventoryDailyReportsTotals t = new SwmsStockInventoryDailyReportsTotals();
                        t.setWeight(reports.get(i).getWeight())
                                .setCurrentInRecord(reports.get(i).getCurrentInRecord())
                                .setCurrentOutRecord(reports.get(i).getCurrentOutRecord())
                                .setLastWeight(reports.get(i).getLastWeight())
                                .setMaterialName(reports.get(i).getMaterialName())
                                .setMaterialNameCode(reports.get(i).getMaterialNameCode())
                                .setMaterialSubTypeId(reports.get(i).getMaterialSubTypeId())
                                .setMaterialTypeId(reports.get(i).getMaterialTypeId())
                                .setMeasureUnit(reports.get(i).getMeasureUnit())
                                //.setSafityInventory(reports.get(i).)
                                .setStockDate(ComUtil.localDateTimeToDate(today));
                        totalsMapper.insert(t);
                    } else {
                        SwmsStockInventoryDailyReportsTotals t = totals.get(0);
                        t.setLastWeight(t.getLastWeight() + reports.get(i).getLastWeight())
                                .setCurrentOutRecord(t.getCurrentOutRecord() + reports.get(i).getCurrentOutRecord())
                                .setCurrentInRecord(t.getCurrentInRecord() + reports.get(i).getCurrentInRecord())
                                /*.setSafityInventory()*/
                                .setWeight(t.getWeight() + reports.get(i).getWeight());
                        totalsMapper.updateById(t);
                    }
                    SwmsStockInventoryDailyReports entity = reports.get(i);
                    entity.setId(null);
                    entity.setStockDate(ComUtil.localDateTimeToDate(today));
                    entity.setCurrentInRecord(0f);
                    entity.setCurrentOutRecord(0f);
                    entity.setLastWeight(entity.getWeight());
                    dailyReportsMapper.insert(entity);
                }
            }
            log.info("定时服务物料库存日报表结束");
        }
    }

    /**
     * （定时服务2）本表通过后台服务进行计算，
     * 根据流量统计标志位=0进行统计。
     * 在凌晨1点进行统计计算，
     * 首先查询物料库存日报表（SWMS_stock _inventory_daily_reports）
     * 中满足流量统计标志位=0的所有物料的“库存日期=当前日期-1”的所有当日入库、当日出库、库存量记录，
     * 统计其合计值并分别存储到当日入库、当日出库、当日库存量中，同时，置库存日期=当前日期-1。
     */
    @Scheduled(cron = "0 0 1 * * *")
    void streamDailyReport() {
        if (enable) {
            log.info("定数服务物料库存流量日报表开始");
            QueryWrapper<SwmsBasicMaterialInfo> materialInfoQueryWrapper = new QueryWrapper<>();
            materialInfoQueryWrapper.eq("stream_flag", 0);
            List<SwmsBasicMaterialInfo> infos = materialInfoMapper.selectList(materialInfoQueryWrapper);
            if (infos.size() == 0) {
                log.info("定数服务物料库存流量日报表结束");
                return;
            }
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime yesterdayStart = today.minusDays(1);
            yesterdayStart = yesterdayStart.withHour(0).withMinute(0).withSecond(0);
            LocalDateTime yesterdayEnd = yesterdayStart.withHour(23).withMinute(59).withSecond(59);

            List<String> matIds = new ArrayList<>();
            infos.forEach(e -> matIds.add(e.getId()));

            QueryWrapper<SwmsStockInventoryDailyReports> dailyReportsQueryWrapper = new QueryWrapper<>();
            dailyReportsQueryWrapper.in("material_name_code", matIds)
                    .ge("stock_date", ComUtil.localDateTimeToDate(yesterdayStart))
                    .le("stock_date", ComUtil.localDateTimeToDate(yesterdayEnd));
            System.out.println(dailyReportsQueryWrapper.getSqlSelect());
            List<SwmsStockInventoryDailyReports> reports = dailyReportsMapper.selectList(dailyReportsQueryWrapper);

            if (reports.size() == 0) {
                log.info("定数服务物料库存流量日报表结束");
                return;
            }
            Float in = 0f, out = 0f, store = 0f;
            SwmsStockInventoryStreamDailyReports entity = new SwmsStockInventoryStreamDailyReports();
            entity.setMeasureUnit(reports.get(0).getMeasureUnit());
            entity.setStockDate(ComUtil.localDateTimeToDate(yesterdayStart));
            entity.setYears("" + yesterdayStart.getYear());
            entity.setMonths("" + yesterdayStart.getMonthValue());
            for (int i = 0; i < reports.size(); i++) {
                in += reports.get(i).getCurrentInRecord();
                out += reports.get(i).getCurrentOutRecord();
                store += reports.get(i).getWeight();
            }
            entity.setCurrentInRecord(in);
            entity.setCurrentOutRecord(out);
            entity.setCurrentWeight(store);
            streamDailyReportsMapper.insert(entity);
            log.info("定数服务物料库存流量日报表结束");
        }
    }
}
