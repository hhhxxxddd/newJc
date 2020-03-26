package com.jinchi.app.service;

import com.jinchi.app.domain.*;
import com.jinchi.app.dto.Page;
import com.jinchi.app.mapper.PowerCheckItemMapper;
import com.jinchi.app.mapper.PowerCheckModelDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-21 14:56
 * @description:
 **/
@Service
public class PowerCheckItemServiceImp implements PowerCheckItemService {

    @Autowired
    PowerCheckItemMapper itemMapper;
    @Autowired
    PowerCheckSiteService siteService;
    @Autowired
    PowerCheckModelDetailMapper modelDetailMapper;

    @Override
    public PowerCheckItem add(PowerCheckItem item) {
        itemMapper.insertSelective(item);
        return item;
    }

    @Override
    public PowerCheckItem update(PowerCheckItem item) {
        itemMapper.updateByPrimaryKeySelective(item);
        PowerCheckModelDetailExample example = new PowerCheckModelDetailExample();
        example.createCriteria().andItemCodeEqualTo(item.getCode());
        PowerCheckModelDetail detail = new PowerCheckModelDetail();
        detail.setCheckContent(item.getCheckContent());
        detail.setDataType(item.getDataType());
        detail.setFrequency(item.getFrequency());
        modelDetailMapper.updateByExampleSelective(detail, example);
        return item;
    }

    @Override
    public List listPlaceBySite(Long siteCode) {
        return itemMapper.selectBySiteCode(siteCode);
    }

    @Override
    public void deleteById(Long id) {
        itemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List getAll(String condition) {
        PowerCheckItemExample example = new PowerCheckItemExample();
        example.createCriteria().andCheckItemLike(condition + "%");
        example.setOrderByClause("code desc");
        List<PowerCheckItem> powerCheckItems = itemMapper.selectByExample(example);

        List<PowerCheckSite> all = siteService.getAll("");
        Map<Long, String> siteMap = new HashMap<>();
        all.forEach(item -> siteMap.put(item.getCode(), item.getSiteName()));

        List<Map<String, Object>> ans = new ArrayList<>();

        powerCheckItems.forEach(item -> {
            ans.add(genMapFormat(siteMap, item));
        });

        return ans;
    }

    @Override
    public Page listByPage(String condition, Integer page, Integer size) {
        return new Page(size, page, getAll(condition));
    }

    @Override
    public Map<String, Object> getOne(Long id) {

        List<PowerCheckSite> all = siteService.getAll("");
        Map<Long, String> siteMap = new HashMap<>();
        all.forEach(item -> siteMap.put(item.getCode(), item.getSiteName()));

        PowerCheckItem powerCheckItem = itemMapper.selectByPrimaryKey(id);

        return genMapFormat(siteMap, powerCheckItem);
    }

    @Override
    public List getByIds(Long[] ids) {
        List res = new ArrayList();
        for (Long id : ids) {
            res.add(getOne(id));
        }
        return res;
    }

    private Map<String, Object> genMapFormat(Map<Long, String> siteMap, PowerCheckItem powerCheckItem) {
        Map<String, Object> res = new HashMap<>();
        res.put("code", powerCheckItem.getCode());
        res.put("siteCode", powerCheckItem.getSiteCode());
        res.put("siteName", siteMap.get(powerCheckItem.getSiteCode()));
        res.put("place", powerCheckItem.getPlace());
        res.put("checkItem", powerCheckItem.getCheckItem());
        res.put("checkContent", powerCheckItem.getCheckContent());
        res.put("dataType", powerCheckItem.getDataType());
        res.put("frequency", powerCheckItem.getFrequency());
        return res;
    }

    @Override
    public List getItemByConditions(Long siteCode, String place) {
        PowerCheckItemExample example = new PowerCheckItemExample();
        example.createCriteria().andSiteCodeEqualTo(siteCode).andPlaceEqualTo(place);
        //List uniqueList = list.stream().distinct().collect(Collectors.toList());
        List<PowerCheckItem> powerCheckItems = itemMapper.selectByExample(example);

        List<String> checkItems = new ArrayList<>();

        Map<Long, String> map = new HashMap<>();

        powerCheckItems.forEach(item -> {
            checkItems.add(item.getCheckItem());
            map.put(item.getCode(), item.getCheckItem());
        });

        List<String> uniqueList = checkItems.stream().distinct().collect(Collectors.toList());

        List<Map<String, Object>> res = new ArrayList<>();
        for (String str : uniqueList) {
            Map<String, Object> ans = new HashMap<>();
            ans.put("checkItem", str);
            ans.put("codeList", getKeyByValue(map, str));
            res.add(ans);
        }
        return res;
    }

    private static List getKeyByValue(Map<Long, String> map, String s) {
        List codeList = new ArrayList<>();
        for (Map.Entry entry : map.entrySet()) {
            if (s.equals(entry.getValue())) {
                codeList.add(entry.getKey());
            }
        }
        return codeList;
    }
}
