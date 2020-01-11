package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoBatchConfig;
import com.jinchi.common.domain.BasicInfoBatchConfigExample;
import com.jinchi.common.mapper.BasicInfoBatchConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-20 17:02
 * @description:
 **/
@Service
public class BasicInfoBatchConfigServiceImp implements BasicInfoBatchConfigService {

    @Autowired
    BasicInfoBatchConfigMapper configMapper;

    @Override
    public BasicInfoBatchConfig add(BasicInfoBatchConfig config) {
        configMapper.insertSelective(config);
        return config;
    }

    @Override
    public BasicInfoBatchConfig update(BasicInfoBatchConfig config) {
        BasicInfoBatchConfigExample example = new BasicInfoBatchConfigExample();
        example.createCriteria().andCodeEqualTo(config.getCode());
        configMapper.updateByExampleSelective(config, example);
        return config;
    }

    @Override
    public BasicInfoBatchConfig getOne() {
        BasicInfoBatchConfigExample example = new BasicInfoBatchConfigExample();
        example.createCriteria();
        List<BasicInfoBatchConfig> basicInfoBatchConfigs = configMapper.selectByExample(example);
        if (basicInfoBatchConfigs.size() > 0) {
            return basicInfoBatchConfigs.get(0);
        }
        return null;
    }

    @Override
    public Float getValueByProcess(String process) {
        BasicInfoBatchConfigExample example = new BasicInfoBatchConfigExample();
        example.createCriteria();
        List<BasicInfoBatchConfig> basicInfoBatchConfigs = configMapper.selectByExample(example);
        if (basicInfoBatchConfigs.size() > 0) {
            BasicInfoBatchConfig config = basicInfoBatchConfigs.get(0);
            if (process.equals("HC")) {
                return config.getHcValue();
            }
            if (process.equals("XD")) {
                return config.getXdValue();
            }
            if (process.equals("HG")) {
                return config.getHgValue();
            }
            if (process.equals("BG")) {
                return config.getBzValue();
            }
        }
        return null;
    }
}
