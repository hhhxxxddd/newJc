package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoProcessInitValues;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @time: 2020/2/26 17:58
 * @description:
 */
public interface BasicInfoProcessInitValuesService {

    List getAll();

    BasicInfoProcessInitValues update(BasicInfoProcessInitValues basicInfoProcessInitValues);
}
