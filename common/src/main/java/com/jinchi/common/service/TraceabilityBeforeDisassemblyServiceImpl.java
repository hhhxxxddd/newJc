package com.jinchi.common.service;

import com.jinchi.common.domain.TraceabilityBeforeDisassembly;
import com.jinchi.common.domain.TraceabilityBeforeDisassemblyExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.exceptions.EnumExceptions;
import com.jinchi.common.exceptions.JcExceptions;
import com.jinchi.common.mapper.TraceabilityBeforeDisassemblyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TraceabilityBeforeDisassemblyServiceImpl implements TraceabilityBeforeDisassemblyService{
    private static final Logger logger = LoggerFactory.getLogger(TraceabilityBeforeDisassemblyServiceImpl.class);
    @Autowired
    private TraceabilityBeforeDisassemblyMapper TraceabilitybeforedisassemblyMapper;


    @Override
    public TraceabilityBeforeDisassembly add(TraceabilityBeforeDisassembly traceabilitybeforedisassembly) {
        TraceabilitybeforedisassemblyMapper.insertSelective(traceabilitybeforedisassembly);
        return traceabilitybeforedisassembly;
    }

    @Override
    public TraceabilityBeforeDisassembly update(TraceabilityBeforeDisassembly traceabilitybeforedisassembly) {
        TraceabilitybeforedisassemblyMapper.updateByPrimaryKeySelective(traceabilitybeforedisassembly);
        return traceabilitybeforedisassembly;
    }

    @Override
    public TraceabilityBeforeDisassembly getById(Integer id) {
        if (TraceabilitybeforedisassemblyMapper.selectByPrimaryKey(Long.valueOf(id)) == null) {
            logger.info("此id = " + id + "不存在，查找失败！");
            throw new JcExceptions(EnumExceptions.DELETE_FAILED_NOT_EXISTS);
        } else {
            return TraceabilitybeforedisassemblyMapper.selectByPrimaryKey(Long.valueOf(id));
        }
    }

    @Override
    public void deleteByIds(Long[] ids) {
           for(Long id:ids)
               deleteById(id);
    }

    @Override
    public void deleteById(Long id) {
        TraceabilitybeforedisassemblyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        return new Page(getAll(condition),page,size);
    }

    @Override
    public List<TraceabilityBeforeDisassembly> getAll(String condition) {
        TraceabilityBeforeDisassemblyExample example = new TraceabilityBeforeDisassemblyExample();
        example.createCriteria().andBatterypacktracecodeLike(condition + "%");
        return TraceabilitybeforedisassemblyMapper.selectByExample(example);
    }

}
