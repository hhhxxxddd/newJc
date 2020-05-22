package com.jinchi.common.service;

import com.jinchi.common.domain.ProcessTraceability;
import com.jinchi.common.domain.ProcessTraceabilityExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.exceptions.EnumExceptions;
import com.jinchi.common.exceptions.JcExceptions;
import com.jinchi.common.mapper.ProcessTraceabilityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcesstraceabilityServiceImpl implements ProcessTraceabilityService{
    private static final Logger logger = LoggerFactory.getLogger(ProcesstraceabilityServiceImpl.class);
    @Autowired
    private ProcessTraceabilityMapper processTraceabilityMapper;


    @Override
    public ProcessTraceability add(ProcessTraceability processTraceability) {
        processTraceabilityMapper.insertSelective(processTraceability);
        return processTraceability;
    }

    @Override
    public ProcessTraceability update(ProcessTraceability Processtraceability) {
        processTraceabilityMapper.updateByPrimaryKeySelective(Processtraceability);
        return Processtraceability;
    }

    @Override
    public ProcessTraceability getById(Integer id) {
        if (processTraceabilityMapper.selectByPrimaryKey(Long.valueOf(id)) == null) {
            logger.info("此id = " + id + "不存在，查找失败！");
            throw new JcExceptions(EnumExceptions.DELETE_FAILED_NOT_EXISTS);
        } else {
            return processTraceabilityMapper.selectByPrimaryKey(Long.valueOf(id));
        }
    }

    @Override
    public void deleteByIds(Long[] ids) {
           for(Long id:ids)
               deleteById(id);
    }

    @Override
    public void deleteById(Long id) {
        processTraceabilityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        return new Page(getAll(condition),page,size);
    }

    @Override
    public List<ProcessTraceability> getAll(String condition) {
        ProcessTraceabilityExample example = new ProcessTraceabilityExample();
        example.createCriteria().andPositiveandnegativepowderbatchnumberLike(condition + "%");
        return processTraceabilityMapper.selectByExample(example);
    }

}
