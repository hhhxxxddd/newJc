package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodeProductionType;

import java.util.List;

public interface AnodeProductionTypeService {

    BasicInfoAnodeProductionType add(BasicInfoAnodeProductionType productionType);

    BasicInfoAnodeProductionType update(BasicInfoAnodeProductionType productionType);

    List getAll();

    void delete(Integer id);
}
