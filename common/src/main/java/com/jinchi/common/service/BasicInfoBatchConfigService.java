package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoBatchConfig;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-20 17:00
 * @description:
 **/

public interface BasicInfoBatchConfigService {

    BasicInfoBatchConfig add(BasicInfoBatchConfig config);

    BasicInfoBatchConfig update(BasicInfoBatchConfig config);

    BasicInfoBatchConfig getOne();

    Float getValueByProcess(String process);
}
