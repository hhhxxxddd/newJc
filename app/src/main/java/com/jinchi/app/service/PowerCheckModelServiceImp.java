package com.jinchi.app.service;

import com.jinchi.app.domain.*;
import com.jinchi.app.dto.PowerCheckModelDTO;
import com.jinchi.app.mapper.PowerCheckModelDetailMapper;
import com.jinchi.app.mapper.PowerCheckModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public PowerCheckModelDTO detail(Long id) {
        PowerCheckModelDTO dto = new PowerCheckModelDTO();

        PowerCheckModel model = modelMapper.selectByPrimaryKey(id);
        dto.setModel(model);

        List<PowerCheckSite> all = siteService.getRealAll();
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
    public Integer getNumsOfModelsBySiteCode(Long code) {
        PowerCheckModelExample example = new PowerCheckModelExample();
        example.createCriteria().andSiteCodeEqualTo(code);
        return modelMapper.countByExample(example);
    }

    @Override
    public List getModelBySiteCode(Long siteCode) {
        PowerCheckModelExample example = new PowerCheckModelExample();
        example.createCriteria().andSiteCodeEqualTo(siteCode);
        return modelMapper.selectByExample(example);
    }
}
