package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoDept;
import com.jinchi.common.domain.BasicInfoDeptExample;
import com.jinchi.common.dto.DeptManageDTO;
import com.jinchi.common.mapper.BasicInfoDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeptManageServiceImp implements DeptManageService {
    @Autowired
    BasicInfoDeptMapper basicInfoDeptMapper;
    public BasicInfoDept addOne(BasicInfoDept basicInfoDept){
        basicInfoDeptMapper.insertSelective(basicInfoDept);
        return basicInfoDept;
    }

    @Override
    public BasicInfoDept update(BasicInfoDept basicInfoDept) {
        BasicInfoDeptExample example = new BasicInfoDeptExample();
        example.createCriteria().andCodeEqualTo(basicInfoDept.getCode());
        basicInfoDeptMapper.updateByExampleSelective(basicInfoDept,example);
        return basicInfoDept;
    }

    @Override
    public void deleteById(Integer id) {
        //这里待解决，部门下有设备就不能删除，需要先删除设备
        //部门下有部门不删除
        BasicInfoDeptExample example = new BasicInfoDeptExample();
        example.createCriteria().andCodeEqualTo(id);
        basicInfoDeptMapper.deleteByExample(example);
    }

    @Override
    public BasicInfoDept getDeptById(Integer id) {
        BasicInfoDeptExample example = new BasicInfoDeptExample();
        example.createCriteria().andCodeEqualTo(id);
        return basicInfoDeptMapper.selectByExample(example).get(0);
    }

    @Override
    public List<DeptManageDTO> getCata() {
        ArrayList<DeptManageDTO> rst = new ArrayList<>();
        BasicInfoDeptExample example = new BasicInfoDeptExample();
        example.createCriteria().andParentCodeIsNull();
        List<BasicInfoDept> bs = basicInfoDeptMapper.selectByExample(example);
        for(BasicInfoDept b:bs){
            DeptManageDTO temp = new DeptManageDTO();
            temp.setParent(b);
            BasicInfoDeptExample example2 = new BasicInfoDeptExample();
            example2.createCriteria().andParentCodeEqualTo(b.getCode());
            temp.setSon(basicInfoDeptMapper.selectByExample(example2));
            rst.add(temp);
        }
        return rst;
    }

    @Override
    public BasicInfoDept getDetailById(Integer id){
        BasicInfoDeptExample example = new BasicInfoDeptExample();
        example.createCriteria().andCodeEqualTo(id);
        List<BasicInfoDept> Temp = basicInfoDeptMapper.selectByExample(example);
        BasicInfoDept bid = Temp.get(0); //获取第一个， 不考虑没有的情况和 重复
        return bid;
    }

  
}
