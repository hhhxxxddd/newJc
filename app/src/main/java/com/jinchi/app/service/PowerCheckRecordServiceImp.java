package com.jinchi.app.service;

import com.jinchi.app.domain.*;
import com.jinchi.app.dto.*;
import com.jinchi.app.mapper.PowerCheckItemMapper;
import com.jinchi.app.mapper.PowerCheckModelMapper;
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

    @Autowired
    PowerCheckModelMapper modelMapper;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    PowerCheckItemMapper itemMapper;


    @Override
    public PowerCheckDTO add(PowerCheckDTO dto) {

        CheckHeadDTO head = dto.getHead();

        int flag = Integer.parseInt(dto.getFlag());
        AuthUserDTO byId = authUserService.findById(Integer.parseInt(head.getUserId()));

        long modelCode = Long.parseLong(head.getModelCode());
        long siteCode = Long.parseLong(head.getSiteCode());

        PowerCheckModel checkModel = modelMapper.selectByPrimaryKey(modelCode);

        PowerCheckRecordHead recordHead = new PowerCheckRecordHead();
        recordHead.setSiteCode(siteCode);
        recordHead.setModelName(checkModel.getModelName());
        recordHead.setCheckDate(new Date());
        recordHead.setOperator(byId.getName());
        recordHead.setClassNum(head.getClassNum());
        recordHead.setNote(head.getNote());
        recordHead.setStatus(flag == 1);
        recordHead.setEffectiveDate(checkModel.getEffectiveDate());
        headMapper.insertSelective(recordHead);

        for (CheckDetailDTO detailDTO : dto.getDetails()) {
            long itemCode = Long.parseLong(detailDTO.getItemCode());
            PowerCheckItem checkItem = itemMapper.selectByPrimaryKey(itemCode);

            PowerCheckRecordDetail detail = new PowerCheckRecordDetail();
            detail.setRecordCode(recordHead.getCode());
            detail.setPlace(checkItem.getPlace());
            detail.setCheckItem(checkItem.getCheckItem());
            detail.setCheckContent(checkItem.getCheckContent());
            detail.setCheckValue(detailDTO.getCheckValue() == null ? 0 : Byte.parseByte(detailDTO.getCheckValue()));
            detail.setCheckResult(detailDTO.getCheckResult());
            detail.setDataType(Byte.parseByte(detailDTO.getDataType()));

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
        Date startOfToday = ComUtil.localDateTimeToDate(ComUtil.getTodayStart().minusDays(7));
        Date now = new Date();
        example.createCriteria().andCheckDateBetween(startOfToday, now);

        example.setOrderByClause("status asc, check_date desc");
        List<PowerCheckRecordHead> powerCheckRecordHeads = headMapper.selectByExample(example);

        List<Map<String, String>> res = new ArrayList<>();
        for (PowerCheckRecordHead head : powerCheckRecordHeads) {
            Map<String, String> map = new HashMap<>();
            map.put("recordId", head.getCode().toString());
            map.put("modelName", head.getModelName());
            map.put("checkDate", ComUtil.dateToString(head.getCheckDate(), "yyyy-MM-dd HH:mm:ss"));
            map.put("shift", head.getClassNum());
            map.put("status", String.valueOf(head.getStatus()));
            res.add(map);
        }
        return res;
    }

    @Override
    public List page(QueryDTO dto) {
        return new Page(dto.getSize(), dto.getPage(), getTodayRecords()).getList();
    }

    @Override
    public PowerCheckRecordDTO update(PowerCheckRecordDTO dto) {

        //更新头表
        headMapper.updateByPrimaryKeySelective(dto.getHead());

        //逐条更新详情
        for (PowerCheckRecordDetail detail : dto.getDetails()) {
            detailMapper.updateByPrimaryKeySelective(detail);
        }
        return dto;
    }
}
