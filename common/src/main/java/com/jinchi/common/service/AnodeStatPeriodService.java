package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodePeriod;

import java.util.List;

public interface AnodeStatPeriodService {

    BasicInfoAnodePeriod add(BasicInfoAnodePeriod anodePeriod);

    void delete(Integer id);

    BasicInfoAnodePeriod update(BasicInfoAnodePeriod anodePeriod);

    List getAll();

    String getNameById(Integer id);
}
