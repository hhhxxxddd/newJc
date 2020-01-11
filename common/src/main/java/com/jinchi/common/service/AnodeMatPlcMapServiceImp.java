package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodeMaterialPlcMap;
import com.jinchi.common.dto.AnodeMatPlcMapDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoAnodeMaterialPlcMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 22:29
 * @description:
 **/

@Service
public class AnodeMatPlcMapServiceImp implements AnodeMatPlcMapService {

    @Autowired
    BasicInfoAnodeMaterialPlcMapMapper mapMapper;

    @Override
    public BasicInfoAnodeMaterialPlcMap add(BasicInfoAnodeMaterialPlcMap map) {
        mapMapper.insertSelective(map);
        return map;
    }

    @Override
    public BasicInfoAnodeMaterialPlcMap update(BasicInfoAnodeMaterialPlcMap map) {
        mapMapper.updateByPrimaryKeySelective(map);
        return map;
    }

    @Override
    public void deleteById(Integer id) {
        mapMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List getAll(String condition) {

        return mapMapper.selectByCondition(condition);
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        return new Page(getAll(condition), page, size);
    }

    @Override
    public AnodeMatPlcMapDTO getById(Integer id) {
        return mapMapper.selectById(id);
    }

    @Override
    public BasicInfoAnodeMaterialPlcMap getOne(Integer id) {
        return mapMapper.selectByPrimaryKey(id);
    }
}

