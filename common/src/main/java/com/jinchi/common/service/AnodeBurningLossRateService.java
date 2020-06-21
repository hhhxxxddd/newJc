package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodeBurningLossRate;
import com.jinchi.common.dto.Page;

/**
 * @author: LiuTaoYi
 * @time: 2020/6/21 10:33
 * @description:
 */
public interface AnodeBurningLossRateService {

    BasicInfoAnodeBurningLossRate add(BasicInfoAnodeBurningLossRate value);

    void delete(Integer id);

    void batchDelete(Integer[] ids);

    BasicInfoAnodeBurningLossRate update(BasicInfoAnodeBurningLossRate value);

    Page page(String condition, Integer page, Integer size);
}
