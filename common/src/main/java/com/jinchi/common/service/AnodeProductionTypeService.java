package com.jinchi.common.service;


import com.jinchi.common.domain.BasicInfoAnodeProductionType;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface AnodeProductionTypeService {

    BasicInfoAnodeProductionType add(BasicInfoAnodeProductionType productionType);

    BasicInfoAnodeProductionType update(BasicInfoAnodeProductionType productionType);

    List getAll(String Condition);

    void delete(Integer id);

    Page page(String condition, Integer page, Integer size);
}
