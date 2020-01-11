package com.jinchi.common.service;

import com.jinchi.common.domain.FireMageDept;
import com.jinchi.common.domain.FireMageDeptExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.FireMageDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireMageDeptServiceImp implements FireMageDeptService {

    @Autowired
    FireMageDeptMapper deptMapper;
    @Override
    public FireMageDept add(FireMageDept dept) {
        deptMapper.insertSelective(dept);
        return dept;
    }

    @Override
    public void delete(Integer id) {
        deptMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for(Integer id:ids)
            delete(id);
    }

    @Override
    public FireMageDept detail(Integer id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        return new Page(getAll(condition),page,size);
    }

    @Override
    public FireMageDept update(FireMageDept dept) {
        deptMapper.updateByPrimaryKeySelective(dept);
        return dept;
    }

    @Override
    public List getAll(String condition) {
        FireMageDeptExample example = new FireMageDeptExample();
        example.createCriteria().andDeptNameLike(condition + "%");
        return deptMapper.selectByExample(example);
    }
}
