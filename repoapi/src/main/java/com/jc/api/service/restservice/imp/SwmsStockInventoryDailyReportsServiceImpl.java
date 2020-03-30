package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.api.entity.SwmsStockInLedgersDayReports;
import com.jc.api.entity.SwmsStockInventoryDailyReports;
import com.jc.api.entity.SwmsStockInventoryDailyReportsTotals;
import com.jc.api.entity.SwmsStockOutLedgersDayReports;
import com.jc.api.exception.custom.DataNotFindException;
import com.jc.api.mapper.SwmsStockInventoryDailyReportsMapper;
import com.jc.api.mapper.SwmsStockInventoryDailyReportsTotalsMapper;
import com.jc.api.service.restservice.ISwmsStockInventoryDailyReportsService;
import com.jc.api.utils.ComUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    //每天由新松接口写入入库、出库流水后，进行解析时，修改本表数据；
    //物料按供应商统计计算。
    @Override
    public void updateDailyRecord(SwmsStockInLedgersDayReports in, SwmsStockOutLedgersDayReports out) {
        if(in != null){
            QueryWrapper<SwmsStockInventoryDailyReports> byBatchByCode = new QueryWrapper<>();
            byBatchByCode.eq("material_name_code",in.getMaterialNameCode())
                    .eq("material_supplier_code",in.getMaterialSupplierCode())
                    .last("order by stock_date limit 1");//更新的物料信息的时间可能有问题
            SwmsStockInventoryDailyReports entity = inventoryDailyReportsMapper.selectOne(byBatchByCode);

            if(entity == null) {
                log.info("库存日报不存在:" + in.getMaterialNameCode());
                return;
            }
            entity.setCurrentInRecord(entity.getCurrentInRecord() + in.getWeight());
            entity.setWeight(entity.getWeight() + in.getWeight());

            inventoryDailyReportsMapper.updateById(entity);

        }else{
            QueryWrapper<SwmsStockInventoryDailyReports> byBatchByCode = new QueryWrapper<>();
            byBatchByCode.eq("material_name_code",out.getMaterialNameCode())
                    .eq("material_supplier_code",out.getMaterialSupplierCode())
                    .last("order by stock_date limit 1");//更新的物料信息的时间可能有问题
            SwmsStockInventoryDailyReports entity = inventoryDailyReportsMapper.selectOne(byBatchByCode);

            if(entity == null){
                log.info("库存日报不存在:" + out.getMaterialNameCode());
                return;
            }
            entity.setCurrentOutRecord(entity.getCurrentOutRecord() + out.getWeight());
            entity.setWeight(entity.getWeight() - out.getWeight());

            inventoryDailyReportsMapper.updateById(entity);
        }
    }
}
