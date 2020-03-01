package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoProcessBaseValues;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @time: 2020/3/1 11:25
 * @description:
 */
public interface BasicInfoProcessBaseValuesService {

    BasicInfoProcessBaseValues update(BasicInfoProcessBaseValues values);

    List getAll();
}
