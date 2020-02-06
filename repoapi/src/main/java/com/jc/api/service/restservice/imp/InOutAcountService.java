package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.*;
import com.jc.api.entity.dto.StreamDTO;
import com.jc.api.mapper.*;
import com.jc.api.service.restservice.IInOutAcountService;
import com.jc.api.utils.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InOutAcountService implements IInOutAcountService {

    @Autowired
    SwmsStockInventoryDailyReportsMapper dailyReportsMapper;
    @Autowired
    SwmsStockInOutMonthReportsMapper inOutMonthReportsMapper;
    @Autowired
    SwmsStockInventoryStreamMonthReportsMapper streamMonthReportsMapper;
    @Autowired
    SwmsStockInventoryTurnoverRateMonthReportsAllTotalMapper allTotalMapper;
    @Autowired
    SwmsStockInventoryTurnoverRateMonthReportsTypeTotalMapper totalMapper;
    @Autowired
    SwmsStockInventoryTurnoverRateMonthReportsDetailsMapper detailsMapper;
    @Autowired
    SwmsBasicMaterialInfoService materialInfoService;

    @Override
    public Boolean addStatistic(Integer year,Integer month,String startTime,String endTime) {
        Map<String, StreamDTO> typeSubType = new HashMap<>();
        Map<String,StreamDTO> type = new HashMap<>();

        startTime += " 00:00:00";
        endTime += " 00:00:00";
        Date start = ComUtil.getDate(startTime,"yyyy-MM-dd HH:mm:ss");
        Date end = ComUtil.getDate(endTime,"yyyy-MM-dd HH:mm:ss");

        Integer lastYear = 0, lastMonth = 0;
        if (month == 1) {
            lastYear = year - 1;
            lastMonth = 12;
        } else {
            lastMonth = month - 1;
        }

        QueryWrapper<SwmsStockInventoryDailyReports> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("stock_date",start)
                .le("stock_date",end).orderByAsc("stock_date");
        List<SwmsStockInventoryDailyReports> reports = dailyReportsMapper.selectList(queryWrapper);

        List<SwmsBasicMaterialInfo> materialInfos = materialInfoService.getAll("");
        Float ttIn = 0f,ttOut = 0f,ttStore = 0f;
        for(int l = 0;l<materialInfos.size();l++) {
            SwmsBasicMaterialInfo info = materialInfos.get(l);
            String key1 = info.getMaterialTypeId() + "-" + info.getSubTypeId();
            String key2 = "" + info.getMaterialTypeId();
            Float tIn = 0f, tOut = 0f, store = 0f, lStore = 0f;
            for (int i = 0; i < reports.size(); i++) {
                SwmsStockInventoryDailyReports report = reports.get(i);
                if (info.getId().equals(report.getMaterialNameCode())) {
                    tIn += report.getCurrentInRecord();
                    ttIn += report.getCurrentInRecord();
                    tOut += report.getCurrentOutRecord();
                    ttOut += report.getCurrentOutRecord();
                    if (i == reports.size() - 1) {
                        store = report.getWeight();

                    }

                    if(!info.getStreamFlag()) {
                        /**
                         * 用于SWMS_stock_inventory_ turnover_rate_month_reports_details
                         */
                        if (!typeSubType.containsKey(key1)) {
                            typeSubType.put(key1, new StreamDTO(report.getCurrentInRecord(), report.getCurrentOutRecord(), report.getWeight()));
                        } else {
                            StreamDTO dto = typeSubType.get(key1);
                            dto.setIn(dto.getIn() + report.getCurrentInRecord())
                                    .setOut(dto.getOut() + report.getCurrentOutRecord())
                                    .setStore(dto.getStore() + report.getWeight());
                            typeSubType.put(key1, dto);
                        }

                        /**
                         * 用于SWMS_stock_inventory_ turnover_rate_month_reports_type_total
                         */
                        if (!type.containsKey(key2)) {
                            type.put(key2, new StreamDTO(report.getCurrentInRecord(), report.getCurrentOutRecord(), report.getWeight()));
                        } else {
                            StreamDTO dto = typeSubType.get(key2);
                            dto.setIn(dto.getIn() + report.getCurrentInRecord())
                                    .setOut(dto.getOut() + report.getCurrentOutRecord())
                                    .setStore(dto.getStore() + report.getWeight());
                            type.put(key2, dto);
                        }
                    }
                }
            }
            SwmsStockInOutMonthReports entity = new SwmsStockInOutMonthReports();
            entity.setCurrentInWeight(tIn)
                    .setCurrentOutWeight(tOut)
                    .setCurrentStockWeight(store)
                    .setBeginDate(start)
                    .setEndDate(end)
                    .setYears("" + year)
                    .setMonths("" + month)
                    .setMaterialNameCode(Integer.parseInt(info.getId()))
                    .setMaterialTypeId(info.getMaterialTypeId())
                    .setMaterialSubTypeId(info.getSubTypeId())
                    .setMaterialName(info.getMaterialName())
                    .setMeasureUnit(info.getMeasureUnit());

            //查找上月库存
            QueryWrapper<SwmsStockInOutMonthReports> monthReportsQueryWrapper = new QueryWrapper<>();
            monthReportsQueryWrapper.eq("years", lastYear).eq("months", lastMonth)
                    .eq("material_name_code", info.getId());
            List<SwmsStockInOutMonthReports> monthReports = inOutMonthReportsMapper.selectList(monthReportsQueryWrapper);
            if (monthReports.size() != 0)
                lStore = monthReports.get(0).getCurrentStockWeight();
            entity.setLastStockWeight(lStore);
            inOutMonthReportsMapper.insert(entity);
        }
/*
            *//**
             * 单击【新增统计】时，同时还会生成物料库存流量月报表
             * （SWMS_stock_inventory_stream_ month_reports）数据；
             *//*
            if(!info.getStreamFlag()){
                SwmsStockInventoryStreamMonthReports entity1 = new SwmsStockInventoryStreamMonthReports();
                entity1.setBeginDate(start)
                        .setEndDate(end)
                        .setMeasureUnit(info.getMeasureUnit())
                        .setCurrentInWeight(tIn)
                        .setCurrentOutWeight(tOut)
                        .setCurrentStockWeight(store)
                        .setYears(""+year)
                        .setMonths(""+month);
                streamMonthReportsMapper.insert(entity1);
            }
            *//**
             * 单击【新增统计】时，同时还会生成物料库存周转率明细报表
             * （SWMS_stock_inventory_ turnover_rate_month_reports_details）数据；
             *//*
            for(String key:typeSubType.keySet()){
                SwmsStockInventoryTurnoverRateMonthReportsDetails detail = new SwmsStockInventoryTurnoverRateMonthReportsDetails();
                String[] keys = key.split("-");
                Integer big = Integer.parseInt(keys[0]);
                Integer small = Integer.parseInt(keys[1]);
                detail.setMaterialTypeId(big)
                        .setMaterialSubTypeId(small)
                        .setMeasureUnit(info.getMeasureUnit())
                        .setCurrentInWeight(typeSubType.get(key).getIn())
                        .setCurrentOutWeight(typeSubType.get(key).getOut())
                        .setCurrentStockWeight(typeSubType.get(key).getStore())
                        .setYears(""+year)
                        .setMonths(""+month);
                QueryWrapper<SwmsStockInventoryTurnoverRateMonthReportsDetails> dQueryWrapper = new QueryWrapper<>();
                dQueryWrapper.eq("years",lastYear).eq("months",lastMonth)
                        .eq("material_type_id",big).eq("material_sub_type_id",small);
                List<SwmsStockInventoryTurnoverRateMonthReportsDetails> ds = detailsMapper.selectList(dQueryWrapper);

                if(ds.size() == 0){
                    detail.setLastStockWeight(0f);
                }else{
                    detail.setLastStockWeight(ds.get(0).getCurrentStockWeight());
                }

                Float rate = detail.getCurrentOutWeight() / ((detail.getLastStockWeight() + detail.getCurrentStockWeight())/2 + 0.0000000001f);
                detail.setTurnoverRate(rate);
                detailsMapper.insert(detail);
            }

            *//**
             * 单击【新增统计】时，同时还会生成物料库存周转率大类合计报表
             * （SWMS_stock_inventory_ turnover_rate_month_reports_type_total）数据；
             *//*
            for(String key:type.keySet()){
                SwmsStockInventoryTurnoverRateMonthReportsTypeTotal total = new SwmsStockInventoryTurnoverRateMonthReportsTypeTotal();
                Integer big = Integer.parseInt(key);
                total.setMaterialTypeId(big)
                        .setMeasureUnit(info.getMeasureUnit())
                        .setCurrentInWeight(type.get(key).getIn())
                        .setCurrentOutWeight(type.get(key).getOut())
                        .setCurrentStockWeight(type.get(key).getStore())
                        .setYears(""+year)
                        .setMonths(""+month);
                QueryWrapper<SwmsStockInventoryTurnoverRateMonthReportsTypeTotal> tq = new QueryWrapper<>();
                tq.eq("years",lastYear).eq("months",lastMonth)
                        .eq("material_type_id",big);
                List<SwmsStockInventoryTurnoverRateMonthReportsTypeTotal> ts = totalMapper.selectList(tq);

                if(ts.size() == 0){
                    total.setLastStockWeight(0f);
                }else{
                    total.setLastStockWeight(ts.get(0).getCurrentStockWeight());
                }

                Float rate = total.getCurrentOutWeight() / ((total.getLastStockWeight() + total.getCurrentStockWeight())/2 + 0.0000000001f);
                total.setTurnoverRate(rate);
                totalMapper.insert(total);
            }

            *//**
             * 单击【新增统计】时，同时还会生成物料库存周转率总合计报表
             * （SWMS_stock_inventory_ turnover_rate_month_reports_all_total）数据；
             *//*
            SwmsStockInventoryTurnoverRateMonthReportsAllTotal allTotal = new SwmsStockInventoryTurnoverRateMonthReportsAllTotal();
            allTotal.setMeasureUnit(info.getMeasureUnit())
                    .setCurrentInWeight(ttIn)
                    .setCurrentOutWeight(ttOut)
                    .setCurrentOutWeight()*/
        return true;
    }

    @Override
    public IPage pages(Integer type, Integer subType, Integer matId, Integer month, Page page) {
        return null;
    }
}
