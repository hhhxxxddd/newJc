package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.api.entity.SwmsBasicInactionStockDeadline;
import com.jc.api.entity.SwmsStockInLedgersDayReports;
import com.jc.api.mapper.SwmsBasicInactionStockDeadlineMapper;
import com.jc.api.mapper.SwmsStockInLedgersDayReportsMapper;
import com.jc.api.service.restservice.ISwmsDullStatisticService;
import com.jc.api.utils.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SwmsDullStatisticService implements ISwmsDullStatisticService {

    @Autowired
    SwmsBasicInactionStockDeadlineMapper deadlineMapper;
    @Autowired
    SwmsStockInLedgersDayReportsMapper dayReportsMapper;

    @Override
    public Map<String, Object> query(Integer type, Integer subType) {
        Map<String,Object> ans = new HashMap<>();
        Long normal = 0l;
        Long obnormal = 0l;
        QueryWrapper<SwmsBasicInactionStockDeadline> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(type!=null,"material_type_id",type);
        queryWrapper.eq(subType!=null,"sub_type_id",subType);
        List<SwmsBasicInactionStockDeadline> deadlines = deadlineMapper.selectList(queryWrapper);

        List<Map> list = new ArrayList<>();
        for(int i=0;i<deadlines.size();i++){
            QueryWrapper<SwmsStockInLedgersDayReports> dayReportsQueryWrapper = new QueryWrapper<>();
            dayReportsQueryWrapper.eq("material_type_id",deadlines.get(i).getMaterialTypeId());
            dayReportsQueryWrapper.eq("material_sub_type_id",deadlines.get(i).getSubTypeId());
            dayReportsQueryWrapper.eq("flag",0);
            List<SwmsStockInLedgersDayReports> reports = dayReportsMapper.selectList(dayReportsQueryWrapper);
            for(int l=0;l<reports.size();l++){
                Map<String,Object> map = new HashMap<>();
                map.put("批次",reports.get(l).getMaterialBatch());
                map.put("重量",reports.get(l).getWeight());
                Date date = reports.get(l).getCreatedDay();
                LocalDateTime localDateTime = ComUtil.dateToLocalDateTime(date);
                Duration duration = Duration.between(localDateTime,LocalDateTime.now());
                long stockAge = duration.toDays();
                map.put("入库时间", ComUtil.dateToString(date,"yyyy年MM月dd日"));
                map.put("库龄",stockAge+"天");
                if(stockAge > deadlines.get(i).getDeadline())
                    obnormal++;
                else
                    normal++;
                list.add(map);
            }
        }
        ans.put("normal",normal);
        ans.put("obnormal",obnormal);
        ans.put("list",list);
        return ans;
    }
}
