package com.jinchi.common.service;

import com.jinchi.common.domain.PowerCheckSite;
import com.jinchi.common.domain.PowerCheckSiteExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.PowerCheckSiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-21 13:05
 * @description:
 **/
@Service
public class PowerCheckSiteServiceImp implements PowerCheckSiteService {

    @Autowired
    PowerCheckSiteMapper checkSiteMapper;

    @Override
    public PowerCheckSite add(PowerCheckSite site) {
        checkSiteMapper.insertSelective(site);
        return site;
    }

    @Override
    public List getAll(String condition) {
        PowerCheckSiteExample example = new PowerCheckSiteExample();
        example.createCriteria().andSiteNameLike(condition + "%");
        example.setOrderByClause("code desc");
        return checkSiteMapper.selectByExample(example);
    }

    @Override
    public Page listByPage(String condition, Integer page, Integer size) {
        return new Page(getAll(condition), page, size);
    }

    @Override
    public PowerCheckSite update(PowerCheckSite checkSite) {
        checkSiteMapper.updateByPrimaryKeySelective(checkSite);
        return checkSite;
    }

    @Override
    public void deleteById(Long id) {
        checkSiteMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public PowerCheckSite getOne(Long id) {
        return checkSiteMapper.selectByPrimaryKey(id);
    }
}
