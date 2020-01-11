package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoPrecursorPeriod;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface PrecursorStatPeriodService {

    BasicInfoPrecursorPeriod getDataById(Integer id);

    BasicInfoPrecursorPeriod add(BasicInfoPrecursorPeriod basicInfoPrecursorPeriod);

    void delete(Integer id);

    BasicInfoPrecursorPeriod update(BasicInfoPrecursorPeriod basicInfoPrecursorPeriod);

    Page page(Integer page, Integer size);

    List getAll();
}
