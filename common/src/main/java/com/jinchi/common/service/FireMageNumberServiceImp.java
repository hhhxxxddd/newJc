package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.FireNumberBase;
import com.jinchi.common.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class FireMageNumberServiceImp implements FireMageNumberService {

    @Autowired
    FireMageNumberHeadMapper headMapper;
    @Autowired
    FireMageNumberDev1Mapper dev1Mapper;
    @Autowired
    FireMageNumberDev2Mapper dev2Mapper;
    @Autowired
    FireMageNumberDayMapper dayMapper;
    @Autowired
    FireMageNumberMonthMapper monthMapper;
    @Autowired
    FireMageNumberYearMapper yearMapper;
    @Autowired
    FireMageNumberUnitMapper unitMapper;
    @Autowired
    FireMageNumberLineMapper lineMapper;
    @Autowired
    FireMageNumberStreamMapper streamMapper;
    @Autowired
    FireMageNumberProcessMapper processMapper;
    @Autowired
    FireMageNumberProductMapper productMapper;

    @Override
    public List getHead() {
        List<Map> ans = new ArrayList<>();
        FireMageNumberHead head = headMapper.selectByPrimaryKey(1);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("position", 1);
        map1.put("word", "工序");
        map1.put("issEnable", head.getProcess());
        FireMageNumberProcessExample example1 = new FireMageNumberProcessExample();
        example1.createCriteria();
        List<FireMageNumberProcess> processes = processMapper.selectByExample(example1);
        for (int i = 0; i < processes.size(); i++) {
            if (processes.get(i).getEnable()) {
                map1.put("demo", processes.get(i).getValue());
                break;
            }
        }
        ans.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("position", 2);
        map2.put("word", "设备号1");
        map2.put("issEnable", head.getDev1());
        FireMageNumberDev1Example example2 = new FireMageNumberDev1Example();
        example1.createCriteria();
        List<FireMageNumberDev1> dev1s = dev1Mapper.selectByExample(example2);
        for (int i = 0; i < dev1s.size(); i++) {
            if (dev1s.get(i).getEnable()) {
                map2.put("demo", dev1s.get(i).getValue());
                break;
            }
        }
        ans.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("position", 3);
        map3.put("word", "设备号2");
        map3.put("issEnable", head.getDev2());
        FireMageNumberDev2Example example3 = new FireMageNumberDev2Example();
        example1.createCriteria();
        List<FireMageNumberDev2> dev2s = dev2Mapper.selectByExample(example3);
        for (int i = 0; i < dev2s.size(); i++) {
            if (dev2s.get(i).getEnable()) {
                map3.put("demo", dev2s.get(i).getValue());
                break;
            }
        }
        ans.add(map3);


        Map<String, Object> map4 = new HashMap<>();
        map4.put("position", 4);
        map4.put("word", "产品型号/厂家");
        map4.put("issEnable", head.getProduct());
        FireMageNumberProductExample example4 = new FireMageNumberProductExample();
        example1.createCriteria();
        List<FireMageNumberProduct> products = productMapper.selectByExample(example4);
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getEnable()) {
                map4.put("demo", products.get(i).getValue());
                break;
            }
        }
        ans.add(map4);

        Map<String, Object> map5 = new HashMap<>();
        map5.put("position", 5);
        map5.put("word", "年");
        map5.put("issEnable", head.getYear());
        FireMageNumberYearExample example5 = new FireMageNumberYearExample();
        example3.createCriteria();
        List<FireMageNumberYear> years = yearMapper.selectByExample(example5);
        for (int i = 0; i < years.size(); i++) {
            if (years.get(i).getEnable()) {
                map5.put("demo", years.get(i).getValue());
                break;
            }
        }
        ans.add(map5);

        Map<String, Object> map6 = new HashMap<>();
        map6.put("position", 6);
        map6.put("word", "月");
        map6.put("issEnable", head.getMonth());
        FireMageNumberMonthExample example6 = new FireMageNumberMonthExample();
        example4.createCriteria();
        List<FireMageNumberMonth> months = monthMapper.selectByExample(example6);
        for (int i = 0; i < months.size(); i++) {
            if (months.get(i).getEnable()) {
                map6.put("demo", months.get(i).getValue());
            }
        }
        ans.add(map6);

        Map<String, Object> map7 = new HashMap<>();
        map7.put("position", 7);
        map7.put("word", "天");
        map7.put("issEnable", head.getDay());
        FireMageNumberDayExample example7 = new FireMageNumberDayExample();
        example4.createCriteria();
        List<FireMageNumberDay> days = dayMapper.selectByExample(example7);
        for (int i = 0; i < days.size(); i++) {
            if (days.get(i).getEnable()) {
                map7.put("demo", days.get(i).getValue());
            }
        }
        ans.add(map7);

        Map<String, Object> map8 = new HashMap<>();
        map8.put("position", 8);
        map8.put("word", "单元");
        map8.put("issEnable", head.getUnit());
        FireMageNumberUnitExample example8 = new FireMageNumberUnitExample();
        example4.createCriteria();
        List<FireMageNumberUnit> units = unitMapper.selectByExample(example8);
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getEnable()) {
                map8.put("demo", units.get(i).getValue());
            }
        }
        ans.add(map8);


        Map<String, Object> map9 = new HashMap<>();
        map9.put("position", 9);
        map9.put("word", "产线");
        map9.put("issEnable", head.getLine());
        FireMageNumberLineExample example9 = new FireMageNumberLineExample();
        example4.createCriteria();
        List<FireMageNumberLine> lines = lineMapper.selectByExample(example9);
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).getEnable()) {
                map9.put("demo", lines.get(i).getValue());
            }
        }
        ans.add(map9);

        Map<String, Object> map10 = new HashMap<>();
        map10.put("position", 10);
        map10.put("word", "流水");
        map10.put("issEnable", head.getStream());
        FireMageNumberStreamExample example10 = new FireMageNumberStreamExample();
        example4.createCriteria();
        List<FireMageNumberStream> streams = streamMapper.selectByExample(example10);
        for (int i = 0; i < streams.size(); i++) {
            if (streams.get(i).getEnable()) {
                map10.put("demo", streams.get(i).getValue());
            }
        }
        ans.add(map10);
        return ans;
    }

    @Override
    public void isEnable(Integer position) {
        FireMageNumberHead head = headMapper.selectByPrimaryKey(1);
        switch (position) {
            case 1:
                head.setProcess(!head.getProcess());
                break;
            case 4:
                head.setProduct(!head.getProduct());
                break;
            case 5:
                head.setYear(!head.getYear());
                break;
            case 6:
                head.setMonth(!head.getMonth());
                break;
            case 7:
                head.setDay(!head.getDay());
                break;
            case 8:
                head.setUnit(!head.getUnit());
                break;
            case 9:
                head.setLine(!head.getLine());
                break;
            case 10:
                head.setStream(!head.getStream());
                break;
            case 2:
                head.setDev1(!head.getDev1());
                break;
            case 3:
                head.setDev2(!head.getDev2());
                break;
            default:
                break;
        }
        headMapper.updateByPrimaryKeySelective(head);
    }

    @Override
    public List detail(Integer position) {
        switch (position) {
            case 1:
                FireMageNumberProcessExample example1 = new FireMageNumberProcessExample();
                example1.createCriteria();
                List<FireMageNumberProcess> items = processMapper.selectByExample(example1);
                return processMapper.selectByExample(example1);
            case 4:
                FireMageNumberProductExample example2 = new FireMageNumberProductExample();
                example2.createCriteria();
                return productMapper.selectByExample(example2);
            case 5:
                FireMageNumberYearExample example3 = new FireMageNumberYearExample();
                example3.createCriteria();
                return yearMapper.selectByExample(example3);
            case 6:
                FireMageNumberMonthExample example4 = new FireMageNumberMonthExample();
                example4.createCriteria();
                return monthMapper.selectByExample(example4);
            case 7:
                FireMageNumberDayExample example5 = new FireMageNumberDayExample();
                example5.createCriteria();
                return dayMapper.selectByExample(example5);
            case 8:
                FireMageNumberUnitExample example6 = new FireMageNumberUnitExample();
                example6.createCriteria();
                return unitMapper.selectByExample(example6);
            case 9:
                FireMageNumberLineExample example7 = new FireMageNumberLineExample();
                example7.createCriteria();
                return lineMapper.selectByExample(example7);
            case 10:
                FireMageNumberStreamExample example8 = new FireMageNumberStreamExample();
                example8.createCriteria();
                return streamMapper.selectByExample(example8);
            case 2:
                FireMageNumberDev1Example example9 = new FireMageNumberDev1Example();
                example9.createCriteria();
                return dev1Mapper.selectByExample(example9);
            case 3:
                FireMageNumberDev2Example example10 = new FireMageNumberDev2Example();
                example10.createCriteria();
                return dev2Mapper.selectByExample(example10);
            default:
                break;
        }
        return null;
    }

    @Override
    public void save(Integer position, List<FireNumberBase> rules,String strs) {
        for (int i = 0; i < rules.size(); i++) {
            switch (position) {
                case 1:
                    FireMageNumberProcess item = new FireMageNumberProcess();
                    item.setDescription(rules.get(i).getDescription());
                    item.setEnable(rules.get(i).getEnable());
                    item.setValue(rules.get(i).getValue());
                    processMapper.insertSelective(item);
                    break;
                case 4:
                    FireMageNumberProduct item1 = new FireMageNumberProduct();
                    item1.setDescription(rules.get(i).getDescription());
                    item1.setEnable(rules.get(i).getEnable());
                    item1.setValue(rules.get(i).getValue());
                    productMapper.insertSelective(item1);
                    break;
                case 5:
                    FireMageNumberYear item3 = new FireMageNumberYear();
                    item3.setDescription(rules.get(i).getDescription());
                    item3.setEnable(rules.get(i).getEnable());
                    item3.setValue(rules.get(i).getValue());
                    yearMapper.insertSelective(item3);
                    break;
                case 6:
                    FireMageNumberMonth item4 = new FireMageNumberMonth();
                    item4.setDescription(rules.get(i).getDescription());
                    item4.setEnable(rules.get(i).getEnable());
                    item4.setValue(rules.get(i).getValue());
                    monthMapper.insertSelective(item4);
                    break;
                case 7:
                    FireMageNumberDay item5 = new FireMageNumberDay();
                    item5.setDescription(rules.get(i).getDescription());
                    item5.setEnable(rules.get(i).getEnable());
                    item5.setValue(rules.get(i).getValue());
                    dayMapper.insertSelective(item5);
                    break;
                case 8:
                    FireMageNumberUnit item6 = new FireMageNumberUnit();
                    item6.setDescription(rules.get(i).getDescription());
                    item6.setEnable(rules.get(i).getEnable());
                    item6.setValue(rules.get(i).getValue());
                    unitMapper.insertSelective(item6);
                    break;
                case 9:
                    FireMageNumberLine item7 = new FireMageNumberLine();
                    item7.setDescription(rules.get(i).getDescription());
                    item7.setEnable(rules.get(i).getEnable());
                    item7.setValue(rules.get(i).getValue());
                    lineMapper.insertSelective(item7);
                    break;
                case 10:
                    FireMageNumberStream item8 = new FireMageNumberStream();
                    item8.setDescription(rules.get(i).getDescription());
                    item8.setEnable(rules.get(i).getEnable());
                    item8.setValue(rules.get(i).getValue());
                    streamMapper.insertSelective(item8);
                    break;
                case 2:
                    FireMageNumberDev1 item9 = new FireMageNumberDev1();
                    item9.setDescription(rules.get(i).getDescription());
                    item9.setEnable(rules.get(i).getEnable());
                    item9.setValue(rules.get(i).getValue());
                    dev1Mapper.insertSelective(item9);
                    break;
                case 3:
                    FireMageNumberDev2 item10 = new FireMageNumberDev2();
                    item10.setDescription(rules.get(i).getDescription());
                    item10.setEnable(rules.get(i).getEnable());
                    item10.setValue(rules.get(i).getValue());
                    dev2Mapper.insertSelective(item10);
                    break;
            }
        }
        String[] str1 = strs.split("-");
        List<String> ls = new ArrayList<>();
        ls.add(str1[0]);
        if(str1.length == 1){
            ls.add("");
        }else {
            ls.add(str1[1]);
        }
        String[] str = new String[2];
        str[0] = ls.get(0);
        str[1] = ls.get(1);
        switch (position){
            case 1:
                FireMageNumberProcess item = new FireMageNumberProcess();
                item.setEnable(false);
                processMapper.updateByExampleSelective(item,new FireMageNumberProcessExample());
                FireMageNumberProcessExample example = new FireMageNumberProcessExample();
                example.createCriteria().andValueEqualTo(str[0]).andDescriptionEqualTo(str[1]);
                item = processMapper.selectByExample(example).get(0);
                item.setEnable(true);
                processMapper.updateByPrimaryKeySelective(item);
                break;
            case 2:
                FireMageNumberDev1 item2 = new FireMageNumberDev1();
                item2.setEnable(false);
                dev1Mapper.updateByExampleSelective(item2,new FireMageNumberDev1Example());
                FireMageNumberDev1Example example2 = new FireMageNumberDev1Example();
                example2.createCriteria().andValueEqualTo(str[0]).andDescriptionEqualTo(str[1]);
                item2 = dev1Mapper.selectByExample(example2).get(0);
                item2.setEnable(true);
                dev1Mapper.updateByPrimaryKeySelective(item2);
                break;
            case 3:
                FireMageNumberDev2 item3 = new FireMageNumberDev2();
                item3.setEnable(false);
                dev2Mapper.updateByExampleSelective(item3,new FireMageNumberDev2Example());
                FireMageNumberDev2Example example3 = new FireMageNumberDev2Example();
                example3.createCriteria().andValueEqualTo(str[0]).andDescriptionEqualTo(str[1]);
                item3 = dev2Mapper.selectByExample(example3).get(0);
                item3.setEnable(true);
                dev2Mapper.updateByPrimaryKeySelective(item3);
                break;
            case 4:
                FireMageNumberProduct item4 = new FireMageNumberProduct();
                item4.setEnable(false);
                productMapper.updateByExampleSelective(item4,new FireMageNumberProductExample());
                FireMageNumberProductExample example4 = new FireMageNumberProductExample();
                example4.createCriteria().andValueEqualTo(str[0]).andDescriptionEqualTo(str[1]);
                item4 = productMapper.selectByExample(example4).get(0);
                item4.setEnable(true);
                productMapper.updateByPrimaryKeySelective(item4);
                break;
            case 5:
                FireMageNumberYear item5 = new FireMageNumberYear();
                item5.setEnable(false);
                yearMapper.updateByExampleSelective(item5,new FireMageNumberYearExample());
                FireMageNumberYearExample example5 = new FireMageNumberYearExample();
                example5.createCriteria().andValueEqualTo(str[0]).andDescriptionEqualTo(str[1]);
                item5 = yearMapper.selectByExample(example5).get(0);
                item5.setEnable(true);
                yearMapper.updateByPrimaryKeySelective(item5);
                break;
            case 6:
                FireMageNumberMonth item6 = new FireMageNumberMonth();
                item6.setEnable(false);
                monthMapper.updateByExampleSelective(item6,new FireMageNumberMonthExample());
                FireMageNumberMonthExample example6 = new FireMageNumberMonthExample();
                example6.createCriteria().andValueEqualTo(str[0]).andDescriptionEqualTo(str[1]);
                item6 = monthMapper.selectByExample(example6).get(0);
                item6.setEnable(true);
                monthMapper.updateByPrimaryKeySelective(item6);
                break;
            case 7:
                FireMageNumberDay item7 = new FireMageNumberDay();
                item7.setEnable(false);
                dayMapper.updateByExampleSelective(item7,new FireMageNumberDayExample());
                FireMageNumberDayExample example7 = new FireMageNumberDayExample();
                example7.createCriteria().andValueEqualTo(str[0]).andDescriptionEqualTo(str[1]);
                item7 = dayMapper.selectByExample(example7).get(0);
                item7.setEnable(true);
                dayMapper.updateByPrimaryKeySelective(item7);
                break;
            case 8:
                FireMageNumberUnit item8 = new FireMageNumberUnit();
                item8.setEnable(false);
                unitMapper.updateByExampleSelective(item8,new FireMageNumberUnitExample());
                FireMageNumberUnitExample example8 = new FireMageNumberUnitExample();
                example8.createCriteria().andValueEqualTo(str[0]).andDescriptionEqualTo(str[1]);
                item8 = unitMapper.selectByExample(example8).get(0);
                item8.setEnable(true);
                unitMapper.updateByPrimaryKeySelective(item8);
                break;
            case 9:
                FireMageNumberLine item9 = new FireMageNumberLine();
                item9.setEnable(false);
                lineMapper.updateByExampleSelective(item9,new FireMageNumberLineExample());
                FireMageNumberLineExample example9 = new FireMageNumberLineExample();
                example9.createCriteria().andValueEqualTo(str[0]).andDescriptionEqualTo(str[1]);
                item9 = lineMapper.selectByExample(example9).get(0);
                item9.setEnable(true);
                lineMapper.updateByPrimaryKeySelective(item9);
                break;
            case 10:
                FireMageNumberStream item10 = new FireMageNumberStream();
                item10.setEnable(false);
                streamMapper.updateByExampleSelective(item10,new FireMageNumberStreamExample());
                FireMageNumberStreamExample example10 = new FireMageNumberStreamExample();
                example10.createCriteria().andValueEqualTo(str[0]).andDescriptionEqualTo(str[1]);
                item10 = streamMapper.selectByExample(example10).get(0);
                item10.setEnable(true);
                streamMapper.updateByPrimaryKeySelective(item10);
                break;
        }

    }

    @Override
    public List getAllInfos() {
        List<BatchRuleDTO> ans = new ArrayList<>();
        {
            BatchRuleDTO rule1 = new BatchRuleDTO();
            rule1.setRule("工序");
            rule1.setPosition("1");
            List<FireMageNumberProcess> v1 = processMapper.selectByExample(new FireMageNumberProcessExample());
            List<String> v1s = new ArrayList<>();
            List<Integer> v2s = new ArrayList<>();
            for (int i = 0; i < v1.size(); i++) {
                v1s.add(v1.get(i).getValue());
                v2s.add(v1.get(i).getCode());
                if (v1.get(i).getEnable()) {
                    rule1.setDefaultValue(v1.get(i).getValue());
                    rule1.setDefaultCode(v1.get(i).getCode());
                }
            }
            rule1.setValues(v1s);
            rule1.setCodes(v2s);
            ans.add(rule1);
        }
        {
            BatchRuleDTO rule1 = new BatchRuleDTO();
            rule1.setRule("设备号1");
            rule1.setPosition("2");
            List<FireMageNumberDev1> v1 = dev1Mapper.selectByExample(new FireMageNumberDev1Example());
            List<String> v1s = new ArrayList<>();
            List<Integer> v2s = new ArrayList<>();
            for (int i = 0; i < v1.size(); i++) {
                v1s.add(v1.get(i).getValue());
                v2s.add(v1.get(i).getCode());
                if (v1.get(i).getEnable()) {
                    rule1.setDefaultValue(v1.get(i).getValue());
                    rule1.setDefaultCode(v1.get(i).getCode());
                }
            }
            rule1.setValues(v1s);
            rule1.setCodes(v2s);
            ans.add(rule1);
        }
        {
            BatchRuleDTO rule1 = new BatchRuleDTO();
            rule1.setRule("设备号2");
            rule1.setPosition("3");
            List<FireMageNumberDev2> v1 = dev2Mapper.selectByExample(new FireMageNumberDev2Example());
            List<String> v1s = new ArrayList<>();
            List<Integer> v2s = new ArrayList<>();
            for (int i = 0; i < v1.size(); i++) {
                v1s.add(v1.get(i).getValue());
                v2s.add(v1.get(i).getCode());
                if (v1.get(i).getEnable()) {
                    rule1.setDefaultValue(v1.get(i).getValue());
                    rule1.setDefaultCode(v1.get(i).getCode());
                }
            }
            rule1.setValues(v1s);
            rule1.setCodes(v2s);
            ans.add(rule1);
        }
        {
            BatchRuleDTO rule1 = new BatchRuleDTO();
            rule1.setRule("产品型号/厂家");
            rule1.setPosition("4");
            List<FireMageNumberProduct> v1 = productMapper.selectByExample(new FireMageNumberProductExample());
            List<String> v1s = new ArrayList<>();
            List<Integer> v2s = new ArrayList<>();
            for (int i = 0; i < v1.size(); i++) {
                v1s.add(v1.get(i).getValue());
                v2s.add(v1.get(i).getCode());
                if (v1.get(i).getEnable()) {
                    rule1.setDefaultValue(v1.get(i).getValue());
                    rule1.setDefaultCode(v1.get(i).getCode());
                }
            }
            rule1.setValues(v1s);
            rule1.setCodes(v2s);
            ans.add(rule1);
        }
        {
            BatchRuleDTO rule1 = new BatchRuleDTO();
            rule1.setRule("年");
            rule1.setPosition("5");
            List<FireMageNumberYear> v1 = yearMapper.selectByExample(new FireMageNumberYearExample());
            List<String> v1s = new ArrayList<>();
            List<Integer> v2s = new ArrayList<>();
            for (int i = 0; i < v1.size(); i++) {
                v1s.add(v1.get(i).getValue());
                v2s.add(v1.get(i).getCode());
                if (v1.get(i).getEnable()) {
                    rule1.setDefaultValue(v1.get(i).getValue());
                    rule1.setDefaultCode(v1.get(i).getCode());
                }
            }
            rule1.setValues(v1s);
            rule1.setCodes(v2s);
            ans.add(rule1);
        }
        {
            BatchRuleDTO rule1 = new BatchRuleDTO();
            rule1.setRule("月");
            rule1.setPosition("6");
            List<FireMageNumberMonth> v1 = monthMapper.selectByExample(new FireMageNumberMonthExample());
            List<String> v1s = new ArrayList<>();
            List<Integer> v2s = new ArrayList<>();
            for (int i = 0; i < v1.size(); i++) {
                v1s.add(v1.get(i).getValue());
                v2s.add(v1.get(i).getCode());
                if (v1.get(i).getEnable()) {
                    rule1.setDefaultValue(v1.get(i).getValue());
                    rule1.setDefaultCode(v1.get(i).getCode());
                }
            }
            rule1.setValues(v1s);
            rule1.setCodes(v2s);
            ans.add(rule1);
        }
        {
            BatchRuleDTO rule1 = new BatchRuleDTO();
            rule1.setRule("日");
            rule1.setPosition("7");
            List<FireMageNumberDay> v1 = dayMapper.selectByExample(new FireMageNumberDayExample());
            List<String> v1s = new ArrayList<>();
            List<Integer> v2s = new ArrayList<>();
            for (int i = 0; i < v1.size(); i++) {
                v1s.add(v1.get(i).getValue());
                v2s.add(v1.get(i).getCode());
                if (v1.get(i).getEnable()) {
                    rule1.setDefaultValue(v1.get(i).getValue());
                    rule1.setDefaultCode(v1.get(i).getCode());
                }
            }
            rule1.setValues(v1s);
            rule1.setCodes(v2s);
            ans.add(rule1);
        }
        {
            BatchRuleDTO rule1 = new BatchRuleDTO();
            rule1.setRule("单元");
            rule1.setPosition("8");
            List<FireMageNumberUnit> v1 = unitMapper.selectByExample(new FireMageNumberUnitExample());
            List<String> v1s = new ArrayList<>();
            List<Integer> v2s = new ArrayList<>();
            for (int i = 0; i < v1.size(); i++) {
                v1s.add(v1.get(i).getValue());
                v2s.add(v1.get(i).getCode());
                if (v1.get(i).getEnable()) {
                    rule1.setDefaultValue(v1.get(i).getValue());
                    rule1.setDefaultCode(v1.get(i).getCode());
                }
            }
            rule1.setValues(v1s);
            rule1.setCodes(v2s);
            ans.add(rule1);
        }
        {
            BatchRuleDTO rule1 = new BatchRuleDTO();
            rule1.setRule("产线");
            rule1.setPosition("9");
            List<FireMageNumberLine> v1 = lineMapper.selectByExample(new FireMageNumberLineExample());
            List<String> v1s = new ArrayList<>();
            List<Integer> v2s = new ArrayList<>();
            for (int i = 0; i < v1.size(); i++) {
                v1s.add(v1.get(i).getValue());
                v2s.add(v1.get(i).getCode());
                if (v1.get(i).getEnable()) {
                    rule1.setDefaultValue(v1.get(i).getValue());
                    rule1.setDefaultCode(v1.get(i).getCode());
                }
            }
            rule1.setValues(v1s);
            rule1.setCodes(v2s);
            ans.add(rule1);
        }
        {
            BatchRuleDTO rule1 = new BatchRuleDTO();
            rule1.setRule("流水");
            rule1.setPosition("10");
            List<FireMageNumberStream> v1 = streamMapper.selectByExample(new FireMageNumberStreamExample());
            List<String> v1s = new ArrayList<>();
            List<Integer> v2s = new ArrayList<>();
            for (int i = 0; i < v1.size(); i++) {
                v1s.add(v1.get(i).getValue());
                v2s.add(v1.get(i).getCode());
                if (v1.get(i).getEnable()) {
                    rule1.setDefaultValue(v1.get(i).getValue());
                    rule1.setDefaultCode(v1.get(i).getCode());
                }
            }
            rule1.setValues(v1s);
            rule1.setCodes(v2s);
            ans.add(rule1);
        }
        return ans;
    }
}
