package com.jinchi.app.service;

import com.jinchi.app.domain.fireMage.*;
import com.jinchi.app.dto.Page;
import com.jinchi.app.dto.QueryDTO;
import com.jinchi.app.mapper.fireMage.FireMageBatchItemsValuesMapper;
import com.jinchi.app.mapper.fireMage.FireMageDeptMapper;
import com.jinchi.app.mapper.fireMage.FireMageDetectInfoMapper;
import com.jinchi.app.mapper.fireMage.FireMageTestItemsMapper;
import com.jinchi.app.utils.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FireMageDetectServiceImp implements FireMageDetectService {

    @Autowired
    FireMageDetectInfoMapper infoMapper;
    @Autowired
    FireMageBatchItemsValuesMapper itemsValuesMapper;
    @Autowired
    FireMageDeptMapper deptMapper;
    @Autowired
    FireMageTestItemsMapper itemsMapper;

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
        Map<String,Object> ans = new HashMap<>();
        List<Map> list = new ArrayList<>();
        FireMageDetectInfo info = infoMapper.selectByPrimaryKey(id);
        if(info == null)
            throw new RuntimeException("找不到该批号");
        FireMageDept dept = deptMapper.selectByPrimaryKey(info.getDeptCode());

        FireMageBatchItemsValuesExample example = new FireMageBatchItemsValuesExample();
        example.createCriteria().andBatchCodeEqualTo(id);
        List<FireMageBatchItemsValues> values = itemsValuesMapper.selectByExample(example);
        if(values.size() == 0)
            throw new RuntimeException("找不到详情");
        FireMageBatchItemsValues itemsValues = values.get(0);

        String[] ids = itemsValues.getItemCodes().split(",");
        String[] items = itemsValues.getItemNames().split(",");
        String[] vs = itemsValues.getItemValues().split(",");

        for(int i=0;i<items.length;i++){
            Map<String,String> map = new HashMap<>();
            FireMageTestItems item = itemsMapper.selectByPrimaryKey(new Long(ids[i]));
            if(item == null)
                throw new RuntimeException("不存在的检测项目"+ids[i]);
            map.put(items[i],(vs[i].equals("-1")?"":vs[i])+item.getUnit());
            list.add(map);
        }
        String status = "";
        Byte b = info.getDetectStatus();
        if(b != null){
            if(b == 0)
                status = "合格";
            if(b == 1)
                status = "不合格";
            if(b == 2)
                status = "让步接收";
        }
        ans.put("dept",dept.getDeptName());
        ans.put("batch",info.getBatch());
        ans.put("status",status);
        ans.put("result",list);
        return ans;
    }
}
