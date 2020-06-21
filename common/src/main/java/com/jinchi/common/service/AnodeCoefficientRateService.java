package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodeCoefficientRate;
import com.jinchi.common.dto.Page;

/**
 * @author: LiuTaoYi
 * @time: 2020/6/20 19:08
 * @description:
 */
public interface AnodeCoefficientRateService {

    /**
     * 新增
     *
     * @param anodeCoefficientRate
     * @return
     */
    BasicInfoAnodeCoefficientRate add(BasicInfoAnodeCoefficientRate anodeCoefficientRate);

    /**
     * 编辑
     *
     * @param anodeCoefficientRate
     * @return
     */
    BasicInfoAnodeCoefficientRate update(BasicInfoAnodeCoefficientRate anodeCoefficientRate);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Integer id);

    Page page(Integer page, Integer size);
}
