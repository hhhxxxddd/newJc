package com.jinchi.app.service;

import com.jinchi.app.domain.AuthDepartment;
import com.jinchi.app.domain.AuthDepartmentExample;
import com.jinchi.app.domain.BasicInfoDept;
import com.jinchi.app.domain.BasicInfoDeptExample;
import com.jinchi.app.mapper.AuthDepartmentMapper;
import com.jinchi.app.mapper.BasicInfoDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImp implements DeptService{

    @Autowired
    AuthDepartmentMapper authDepartmentMapper;
    @Autowired
    BasicInfoDeptMapper basicInfoDeptMapper;

    @Override
    public List<AuthDepartment> getAll() {
        AuthDepartmentExample example = new AuthDepartmentExample();
        example.createCriteria();
        return authDepartmentMapper.selectByExample(example);
    }

    @Override
    public BasicInfoDept getById(Integer id) {
        BasicInfoDeptExample example = new BasicInfoDeptExample();
        example.createCriteria().andCodeEqualTo(id);
        BasicInfoDept basicInfoDept = basicInfoDeptMapper.selectByExample(example).get(0);
        return basicInfoDept;
    }
}
