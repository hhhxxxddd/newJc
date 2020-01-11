package com.jinchi.common.service;

import com.jinchi.common.domain.ProductionBatchRetrospectInfoExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.ProductionBatchRetrospectInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-01 14:06
 * @description:
 **/
@Service
public class ProductionBatchRetrospectServiceImp implements ProductionBatchRetrospectService {

    @Autowired
    ProductionBatchRetrospectInfoMapper retrospectInfoMapper;

    @Override
    public List getAll(String condition) {
        ProductionBatchRetrospectInfoExample example = new ProductionBatchRetrospectInfoExample();
        example.createCriteria().andBatchLike(condition + "%");

        return retrospectInfoMapper.selectByExample(example);
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        Page pageInfo = new Page(getAll(condition), page, size);
        return pageInfo;
    }
}
