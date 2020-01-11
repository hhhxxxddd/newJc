package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoQuesAns;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoQuesAnsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuesAnsServiceImp implements QuesAnsService{
    @Autowired
    BasicInfoQuesAnsMapper mapper;
    @Override
    public BasicInfoQuesAns add(BasicInfoQuesAns basicInfoQuesAns) {
        mapper.insertSelective(basicInfoQuesAns);
        return basicInfoQuesAns;
    }

    @Override
    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        for(Long id:ids){
            delete(id);
        }
    }

    @Override
    public BasicInfoQuesAns getById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        String sql = "select * from basic_info_ques_ans where question_area like '%" + condition + "%' or";
        sql += " question like '%" + condition + "%' or";
        sql += " answer like '%" + condition + "%'";
        List ans = mapper.selectByCondition(sql);
        return new Page(ans,page,size);
    }

    @Override
    public BasicInfoQuesAns update(BasicInfoQuesAns basicInfoQuesAns) {
        mapper.updateByPrimaryKeySelective(basicInfoQuesAns);
        return basicInfoQuesAns;
    }
}
