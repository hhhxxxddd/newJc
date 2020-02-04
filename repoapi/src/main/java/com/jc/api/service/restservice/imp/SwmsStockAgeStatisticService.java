package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.api.entity.*;
import com.jc.api.mapper.*;
import com.jc.api.service.restservice.ISwmsStockAgeStatisticService;
import com.jc.api.utils.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SwmsStockAgeStatisticService implements ISwmsStockAgeStatisticService {

    @Autowired
    SwmsStockInventoryTurnoverRateMonthReportsDetailsMapper detailsMapper;
    @Autowired
    SwmsStockInventoryTurnoverRateMonthReportsTypeTotalMapper totalMapper;
    @Autowired
    SwmsStockInventoryTurnoverRateMonthReportsAllTotalMapper allTotalMapper;
    @Autowired
    SwmsBasicStockAgingRangeMapper rangeMapper;
    @Autowired
    SwmsStockInLedgersDayReportsMapper dayReportsMapper;

    @Override
    public Map turnoverRate(Integer type, Integer subType,String time) {
        Map<String,Object> ans = new HashMap<>();
        LocalDateTime localDateTime = ComUtil.stringToLocalDateTime(time,"yyyy-MM-dd HH:mm:ss");
        if(subType != null) {
            QueryWrapper<SwmsStockInventoryTurnoverRateMonthReportsDetails> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("material_type_id", type);
            queryWrapper.eq("material_sub_type_id", subType);
            queryWrapper.eq("years",localDateTime.getYear());
            queryWrapper.eq("months",localDateTime.getMonthValue());
            List<SwmsStockInventoryTurnoverRateMonthReportsDetails> details = detailsMapper.selectList(queryWrapper);
            if (details.size() == 0) {
                //可能需要计算统计
                ans.put("周转率", "未统计");
            } else {
                ans.put("周转率", details.get(0).getTurnoverRate());
            }
        }
        if(subType == null){
            QueryWrapper<SwmsStockInventoryTurnoverRateMonthReportsTypeTotal> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("material_type_id",type);
            queryWrapper.eq("years",localDateTime.getYear());
            queryWrapper.eq("months",localDateTime.getMonthValue());
            List<SwmsStockInventoryTurnoverRateMonthReportsTypeTotal> typeTotals = totalMapper.selectList(queryWrapper);
            if(typeTotals.size() == 0){
                //可能需要计算统计
                ans.put("周转率", "未统计");
            }else{
                ans.put("周转率",typeTotals.get(0).getTurnoverRate());
            }
        }
        QueryWrapper<SwmsStockInventoryTurnoverRateMonthReportsAllTotal> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("years",localDateTime.getYear());
        queryWrapper.eq("months",localDateTime.getMonthValue());
        List<SwmsStockInventoryTurnoverRateMonthReportsAllTotal> allTotals = allTotalMapper.selectList(queryWrapper);
        if(allTotals.size() == 0){
            //可能需要计算统计
            ans.put("总库周转率", "未统计");
        }else{
            ans.put("总库周转率",allTotals.get(0).getTurnoverRate());
        }
        return ans;
    }

    @Override
    public Map distribution(SwmsBasicStockAgingRange range) {
        Map<String,Object> ans = new HashMap<>();
        range.setQueryTime(new Date());
        rangeMapper.insert(range);

        List<SwmsStockInLedgersDayReports> dayReports = dayReportsMapper.selectList(new QueryWrapper<>());
        List<String> regions = new ArrayList<>();
        regions.add(range.getAgingRange1a() + "~" + range.getAgingRange1b());
        regions.add(range.getAgingRange2a() + "~" + range.getAgingRange2b());
        regions.add(range.getAgingRange3a() + "~" + range.getAgingRange3b());
        regions.add(range.getAgingRange4a() + "~" + range.getAgingRange4b());
        regions.add(range.getAgingRange5a() + "~" + range.getAgingRange5b());
        regions.add(">" + range.getAgingEnd());
        List<Integer> counts = new ArrayList<>();
        int[] cnt = {0,0,0,0,0,0};
        List<List> details = new ArrayList<>();
        List<Map> r1 = new ArrayList<>();
        List<Map> r2 = new ArrayList<>();
        List<Map> r3 = new ArrayList<>();
        List<Map> r4 = new ArrayList<>();
        List<Map> r5 = new ArrayList<>();
        List<Map> r6 = new ArrayList<>();

        for(int i=0;i<dayReports.size();i++){
            Map<String,Object> map = new HashMap<>();
            map.put("编码",dayReports.get(i).getMaterialBatch());
            map.put("重量",dayReports.get(i).getWeight());
            LocalDateTime localDateTime = ComUtil.dateToLocalDateTime(dayReports.get(i).getCreatedDay());
            Duration duration = Duration.between(localDateTime,LocalDateTime.now());
            long stockAge = duration.toDays();
            if(stockAge >= range.getAgingRange1a() && stockAge < range.getAgingRange1b()){
                cnt[0] ++;
                r1.add(map);
            }
            if(stockAge >= range.getAgingRange2a() && stockAge < range.getAgingRange2b()){
                cnt[1] ++;
                r2.add(map);
            }
            if(stockAge >= range.getAgingRange3a() && stockAge < range.getAgingRange3b()){
                cnt[2] ++;
                r3.add(map);
            }
            if(stockAge >= range.getAgingRange4a() && stockAge < range.getAgingRange4b()){
                cnt[3] ++;
                r4.add(map);
            }
            if(stockAge >= range.getAgingRange5a() && stockAge < range.getAgingRange5b()){
                cnt[4] ++;
                r5.add(map);
            }
            if(stockAge >= range.getAgingEnd()){
                cnt[5] ++;
                r6.add(map);
            }
        }
        for(int i=0;i<6;i++){
            counts.add(cnt[i]);
        }
        details.add(r1);
        details.add(r2);
        details.add(r3);
        details.add(r4);
        details.add(r5);
        details.add(r6);
        ans.put("regions",regions);
        ans.put("counts",counts);
        ans.put("details",details);
        return ans;
    }
}
