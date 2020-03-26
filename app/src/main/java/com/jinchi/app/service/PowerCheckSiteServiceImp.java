package com.jinchi.app.service;

import com.jinchi.app.domain.PowerCheckSite;
import com.jinchi.app.domain.PowerCheckSiteExample;
import com.jinchi.app.dto.Page;
import com.jinchi.app.dto.QueryDTO;
import com.jinchi.app.mapper.PowerCheckSiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-21 13:05
 * @description:
 **/
@Service
public class PowerCheckSiteServiceImp implements PowerCheckSiteService {

    @Autowired
    PowerCheckSiteMapper checkSiteMapper;

    @Autowired
    PowerCheckModelService modelService;

    @Override
    public List getAll(String condition) {
        PowerCheckSiteExample example = new PowerCheckSiteExample();
        example.createCriteria().andSiteNameLike(condition + "%");
        example.setOrderByClause("code desc");
        List<PowerCheckSite> powerCheckSites = checkSiteMapper.selectByExample(example);

        List<Map<String, String>> list = new ArrayList<>();
        for (PowerCheckSite site : powerCheckSites) {
            Map<String, String> map = new HashMap<>();
            map.put("siteCode", site.getCode().toString());
            map.put("siteName", site.getSiteName());
            map.put("num", modelService.getNumsOfModelsBySiteCode(site.getCode()).toString());
            list.add(map);
        }
        return list;
    }

    @Override
    public List getRealAll() {
        PowerCheckSiteExample example = new PowerCheckSiteExample();
        example.createCriteria();
        return checkSiteMapper.selectByExample(example);
    }

    @Override
    public List listByPage(QueryDTO dto) {
        return new Page(dto.getSize(), dto.getPage(), getAll(dto.getCondi())).getList();
    }


}
