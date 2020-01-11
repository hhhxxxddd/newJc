package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.PowerCheckRecordDTO;
import com.jinchi.common.mapper.BasicInfoUserDeviceDeptMapMapper;
import com.jinchi.common.mapper.PowerCheckRecordDetailMapper;
import com.jinchi.common.mapper.PowerCheckRecordHeadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    BasicInfoUserDeviceDeptMapMapper deptMapMapper;
    @Autowired
    AuthUserService authUserService;

    @Override
    public PowerCheckRecordDTO add(PowerCheckRecordDTO dto) {
        Integer flag = dto.getFlag();
        PowerCheckRecordHead head = dto.getHead();
        head.setStatus(flag == 1);
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
    public PowerCheckRecordDTO update(PowerCheckRecordDTO dto) {
        PowerCheckRecordHead head = dto.getHead();
        head.setStatus(dto.getFlag() == 1);
        headMapper.updateByPrimaryKeySelective(head);

        //先删除点检详情 再插入
        PowerCheckRecordDetailExample example = new PowerCheckRecordDetailExample();
        example.createCriteria().andRecordCodeEqualTo(head.getCode());
        detailMapper.deleteByExample(example);

        List<PowerCheckRecordDetail> details = dto.getDetails();
        for (PowerCheckRecordDetail detail : details) {
            detail.setRecordCode(head.getCode());
            detailMapper.insertSelective(detail);
        }

        return dto;
    }

    @Override
    public void delete(Long id) {
        //先删除详情表 再删除主表
        PowerCheckRecordDetailExample example = new PowerCheckRecordDetailExample();
        example.createCriteria().andRecordCodeEqualTo(id);
        detailMapper.deleteByExample(example);

        headMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deletes(Long[] ids) {
        for (Long id : ids) {
            delete(id);
        }
    }

    @Override
    public PowerCheckRecordDTO detail(Long id) {
        PowerCheckRecordDTO res = new PowerCheckRecordDTO();

        PowerCheckRecordHead head = headMapper.selectByPrimaryKey(id);

        List<PowerCheckSite> all = siteService.getAll("");
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
    public List getAll(String condition) {
        PowerCheckRecordHeadExample example = new PowerCheckRecordHeadExample();
        example.createCriteria().andModelNameLike(condition + "%");
        example.setOrderByClause("code desc");
        List<PowerCheckRecordHead> powerCheckRecordHeads = headMapper.selectByExample(example);

        List<PowerCheckSite> all = siteService.getAll("");
        Map<Long, String> siteMap = new HashMap<>();
        all.forEach(powerCheckSite -> siteMap.put(powerCheckSite.getCode(), powerCheckSite.getSiteName()));

        List<PowerCheckRecordDTO> res = new ArrayList<>();
        for (PowerCheckRecordHead head : powerCheckRecordHeads) {
            PowerCheckRecordDTO dto = new PowerCheckRecordDTO();
            dto.setHead(head);
            dto.setSiteName(siteMap.get(head.getSiteCode()));
            res.add(dto);
        }
        return res;
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        return new Page(getAll(condition), page, size);
    }

    @Override
    public List getOperator() {
        BasicInfoUserDeviceDeptMapExample example = new BasicInfoUserDeviceDeptMapExample();
        example.createCriteria().andDeptCodeEqualTo(74);
        List<BasicInfoUserDeviceDeptMap> maps = deptMapMapper.selectByExample(example);
        List<String> res = new ArrayList<>();
        for (BasicInfoUserDeviceDeptMap map : maps) {
            res.add(authUserService.findById(map.getAuthCode()).getName());
        }
        return res;
    }
}
