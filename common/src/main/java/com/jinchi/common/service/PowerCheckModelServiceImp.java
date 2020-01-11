package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.PowerCheckModelDTO;
import com.jinchi.common.mapper.PowerCheckModelDetailMapper;
import com.jinchi.common.mapper.PowerCheckModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-21 16:40
 * @description:
 **/
@Service
public class PowerCheckModelServiceImp implements PowerCheckModelService {

    @Autowired
    PowerCheckModelMapper modelMapper;
    @Autowired
    PowerCheckModelDetailMapper detailMapper;
    @Autowired
    PowerCheckSiteService siteService;

    @Override
    public PowerCheckModelDTO add(PowerCheckModelDTO dto) {
        PowerCheckModel model = dto.getModel();

        modelMapper.insertSelective(model);

        List<PowerCheckModelDetail> details = dto.getDetails();
        for (PowerCheckModelDetail detail : details) {
            detail.setModelCode(model.getCode());
            detailMapper.insertSelective(detail);
        }
        return dto;
    }

    @Override
    public void delete(Long id) {
        PowerCheckModelDetailExample example = new PowerCheckModelDetailExample();
        example.createCriteria().andModelCodeEqualTo(id);
        detailMapper.deleteByExample(example);

        modelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        for (Long id : ids) {
            delete(id);
        }
    }

    @Override
    public PowerCheckModelDTO detail(Long id) {
        PowerCheckModelDTO dto = new PowerCheckModelDTO();

        PowerCheckModel model = modelMapper.selectByPrimaryKey(id);
        dto.setModel(model);

        List<PowerCheckSite> all = siteService.getAll("");
        Map<Long, String> siteMap = new HashMap<>();
        all.forEach(powerCheckSite -> siteMap.put(powerCheckSite.getCode(), powerCheckSite.getSiteName()));

        dto.setSiteName(siteMap.get(model.getSiteCode()));

        PowerCheckModelDetailExample example = new PowerCheckModelDetailExample();
        example.createCriteria().andModelCodeEqualTo(id);
        List<PowerCheckModelDetail> details = detailMapper.selectByExample(example);
        dto.setDetails(details);

        return dto;
    }

    @Override
    public PowerCheckModelDTO update(PowerCheckModelDTO dto) {

        PowerCheckModel model = dto.getModel();
        modelMapper.updateByPrimaryKeySelective(model);

        //模板详情先删除 再插入
        PowerCheckModelDetailExample example = new PowerCheckModelDetailExample();
        example.createCriteria().andModelCodeEqualTo(model.getCode());
        detailMapper.deleteByExample(example);

        List<PowerCheckModelDetail> details = dto.getDetails();
        for (PowerCheckModelDetail detail : details) {
            detail.setModelCode(model.getCode());
            detailMapper.insertSelective(detail);
        }

        return dto;
    }

    @Override
    public List getAll(String condition) {
        PowerCheckModelExample example = new PowerCheckModelExample();
        example.createCriteria().andModelNameLike(condition + "%");
        example.setOrderByClause("code desc");
        List<PowerCheckModel> powerCheckModels = modelMapper.selectByExample(example);

        List<PowerCheckSite> all = siteService.getAll("");
        Map<Long, String> siteMap = new HashMap<>();
        all.forEach(powerCheckSite -> siteMap.put(powerCheckSite.getCode(), powerCheckSite.getSiteName()));

        List<PowerCheckModelDTO> res = new ArrayList<>();

        for (PowerCheckModel model : powerCheckModels) {
            PowerCheckModelDTO dto = new PowerCheckModelDTO();
            dto.setModel(model);
            dto.setSiteName(siteMap.get(model.getSiteCode()));
            res.add(dto);
        }
        return res;
    }

    @Override
    public Page byPage(String condition, Integer page, Integer size) {
        return new Page(getAll(condition), page, size);
    }

    @Override
    public List getModelBySiteCode(Long siteCode) {
        PowerCheckModelExample example = new PowerCheckModelExample();
        example.createCriteria().andSiteCodeEqualTo(siteCode);
        return modelMapper.selectByExample(example);
    }
}
