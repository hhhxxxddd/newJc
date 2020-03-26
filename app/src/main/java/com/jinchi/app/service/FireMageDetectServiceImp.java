package com.jinchi.app.service;

import com.jinchi.app.domain.fireMage.FireMageDetectInfo;
import com.jinchi.app.domain.fireMage.FireMageDetectInfoExample;
import com.jinchi.app.dto.Page;
import com.jinchi.app.dto.QueryDTO;
import com.jinchi.app.mapper.fireMage.FireMageDetectInfoMapper;
import com.jinchi.app.utils.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FireMageDetectServiceImp implements FireMageDetectService {

    @Autowired
    FireMageDetectInfoMapper infoMapper;

    @Override
    public Page page(QueryDTO queryDTO) {
        String sql = "select * from fire_mage_detect_info where batch like '" + queryDTO.getCondi() + "%' order by code desc limit " + (queryDTO.getPage()-1)*queryDTO.getSize() + "," + queryDTO.getSize();
        List<FireMageDetectInfo> infos = infoMapper.selectByCondition(sql);
        sql = "select count(*) from fire_mage_detect_info where batch like '" + queryDTO.getCondi() + "%'";
        Integer total = infoMapper.countByCondition(sql);
        List<Map<String,String>> maps = new ArrayList<>();
        for(int i=0;i<infos.size();i++){
            Map<String,String> map = new HashMap<>();
            map.put("batch",infos.get(i).getBatch());
            map.put("date", ComUtil.dateToString(infos.get(i).getCheckInTime(),"yyyy-MM-dd"));
            map.put("people",infos.get(i).getDelieryPeople());
            map.put("id",""+infos.get(i).getCode());
            maps.add(map);
        }
        Page page = new Page(queryDTO.getSize(),1,maps);
        page.setPage(queryDTO.getPage());
        page.setTotal(total);
        return page;
    }

    @Override
    public Map detail(Long id) {
        Map<String,String> ans = new HashMap<>();
        ans.put("dept",null);
        ans.put("batch",null);
        ans.put("status",null);
        ans.put("result","list");
        return ans;
    }
}
