package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.api.entity.SwmsStockInventoryStreamDailyReports;
import com.jc.api.entity.SwmsStockInventoryStreamMonthReports;
import com.jc.api.mapper.SwmsStockInventoryStreamDailyReportsMapper;
import com.jc.api.mapper.SwmsStockInventoryStreamMonthReportsMapper;
import com.jc.api.service.restservice.ISwmsStreamStatisticService;
import com.jc.api.utils.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SwmsStreamStatisticService implements ISwmsStreamStatisticService {

    @Autowired
    SwmsStockInventoryStreamDailyReportsMapper dailyReportsMapper;
    @Autowired
    SwmsStockInventoryStreamMonthReportsMapper monthReportsMapper;

    @Override
    public List monthView(Integer year, Integer month) {
        List ans = new ArrayList();

        QueryWrapper<SwmsStockInventoryStreamDailyReports> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("years",year).eq("months",month).orderByAsc("stock_date");
        List<SwmsStockInventoryStreamDailyReports> dailyReports = dailyReportsMapper.selectList(queryWrapper);

        for(int i=0;i<dailyReports.size();i++){
            Map<String,Object> map =  new HashMap<>();
            map.put("in",dailyReports.get(i).getCurrentInRecord());
            map.put("out",dailyReports.get(i).getCurrentOutRecord());
            map.put("store",dailyReports.get(i).getCurrentWeight());
            map.put("date", ComUtil.dateToString(dailyReports.get(i).getStockDate(),"yyyy-MM-dd"));
            ans.add(map);
        }
        return ans;
    }

    @Override
    public List yearView(Integer year) {
        List ans = new ArrayList();

        QueryWrapper<SwmsStockInventoryStreamMonthReports> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("years",year).orderByAsc("begin_date");
        List<SwmsStockInventoryStreamMonthReports> monthReports = monthReportsMapper.selectList(queryWrapper);

        for(int i=0;i<monthReports.size();i++){
            Map<String,Object> map =  new HashMap<>();
            map.put("in",monthReports.get(i).getCurrentInWeight());
            map.put("out",monthReports.get(i).getCurrentOutWeight());
            map.put("store",monthReports.get(i).getCurrentStockWeight());
            map.put("date", ComUtil.dateToString(monthReports.get(i).getBeginDate(),"yyyy-MM-dd"));
            ans.add(map);
        }
        return ans;
    }
}
