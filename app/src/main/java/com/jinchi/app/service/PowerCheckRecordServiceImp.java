package com.jinchi.app.service;

import com.jinchi.app.domain.*;
import com.jinchi.app.dto.Page;
import com.jinchi.app.dto.PowerCheckRecordDTO;
import com.jinchi.app.mapper.PowerCheckRecordDetailMapper;
import com.jinchi.app.mapper.PowerCheckRecordHeadMapper;
import com.jinchi.app.utils.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-22 13:40
 * @description:
 **/
@Service
public class PowerCheckRecordServiceImp implements PowerCheckRecordService {
    @Autowired
    PowerCheckRecordHeadMapper headMapper;

    @Autowired
    PowerCheckRecordDetailMapper detailMapper;

    @Autowired
    PowerCheckSiteService siteService;

    @Override
    public PowerCheckRecordDTO add(PowerCheckRecordDTO dto) {
        PowerCheckRecordHead head = dto.getHead();
        head.setStatus(true);
        head.setEffectiveDate(dto.getEffectiveDate());
        headMapper.insertSelective(head);

        List<PowerCheckRecordDetail> details = dto.getDetails();
        for (PowerCheckRecordDetail detail : details) {
            detail.setRecordCode(head.getCode());
            detailMapper.insertSelective(detail);
        }

        return dto;
    }


    @Override
    public PowerCheckRecordDTO detail(Long id) {
        PowerCheckRecordDTO res = new PowerCheckRecordDTO();

        PowerCheckRecordHead head = headMapper.selectByPrimaryKey(id);

        List<PowerCheckSite> all = siteService.getRealAll();
        Map<Long, String> siteMap = new HashMap<>();
        all.forEach(powerCheckSite -> siteMap.put(powerCheckSite.getCode(), powerCheckSite.getSiteName()));

        res.setHead(head);
        res.setSiteName(siteMap.get(head.getSiteCode()));

        PowerCheckRecordDetailExample example = new PowerCheckRecordDetailExample();
        example.createCriteria().andRecordCodeEqualTo(id);
        List<PowerCheckRecordDetail> details = detailMapper.selectByExample(example);
        res.setDetails(details);

        return res;
    }


    @Override
    public List getTodayRecords() {
        PowerCheckRecordHeadExample example = new PowerCheckRecordHeadExample();
        Date startOfToday = ComUtil.localDateTimeToDate(ComUtil.getTodayStart());
        Date now = new Date();
        example.createCriteria().andCheckDateBetween(startOfToday, now);

        example.setOrderByClause("check_date desc");
        List<PowerCheckRecordHead> powerCheckRecordHeads = headMapper.selectByExample(example);

        List<Map<String, String>> res = new ArrayList<>();
        for (PowerCheckRecordHead head : powerCheckRecordHeads) {
            Map<String, String> map = new HashMap<>();
            map.put("recordId", head.getCode().toString());
            map.put("modelName", head.getModelName());
            map.put("checkDate", ComUtil.dateToString(head.getCheckDate(), "yyyy-MM-dd HH:mm:ss"));
            map.put("shift", head.getClassNum());
            res.add(map);
        }
        return res;
    }

    @Override
    public List page(Integer page, Integer size) {
        return new Page(size, page, getTodayRecords()).getList();
    }
}
