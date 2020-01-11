package com.jinchi.common.service;

import com.jinchi.common.domain.CommonBatchNumber;
import com.jinchi.common.mapper.CommonBatchNumberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：XudongHu
 * @className:CommonBatchNumberMapperServiceImp
 * @description:
 * @date:11:01 2018/11/20
 */
@Service
public class CommonBatchNumberServiceImp implements CommonBatchNumberService {
    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;

    /**
     * 根据批号找到任意批号数据
     * @param batchNumber
     * @return
     */
    @Override
    public Object byBatchNumber(String batchNumber) {
        CommonBatchNumber batch = commonBatchNumberMapper.byBatchNumber(batchNumber);
        if(null==batch) return String.format("没有批号为%s的数据",batchNumber);
        return null;
    }
}
