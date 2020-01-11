package com.jinchi.common.service;

import com.jinchi.common.domain.FireMageOperationManual;
import com.jinchi.common.domain.FireMageOperationManualExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.FireMageOperationManualMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FireMageOperationServiceImp implements FireMageOperationService {

    @Autowired
    FireMageOperationManualMapper mapper;

    @Override
    public FireMageOperationManual add(FireMageOperationManual manual) {
        mapper.insertSelective(manual);
        return manual;
    }

    @Override
    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        for(Long id:ids)
            delete(id);
    }

    @Override
    public FireMageOperationManual detail(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        FireMageOperationManualExample example = new FireMageOperationManualExample();
        example.createCriteria().andTitleLike(condition + "%");
        return new Page(mapper.selectByExample(example),page,size);
    }

    @Override
    public FireMageOperationManual update(FireMageOperationManual manual) {
        mapper.updateByPrimaryKeySelective(manual);
        return manual;
    }
}
