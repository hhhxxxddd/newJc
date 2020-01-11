package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoPrecursorProductionLine;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface PrecursorProductionLineService {

    BasicInfoPrecursorProductionLine getDataById(Integer id);

    BasicInfoPrecursorProductionLine add(BasicInfoPrecursorProductionLine basicInfoPrecursorProductionLine);

    void delete(Integer lineId);

    Page page(Integer page,Integer size);

    List getAll();

    BasicInfoPrecursorProductionLine update(BasicInfoPrecursorProductionLine basicInfoPrecursorProductionLine);

    String getNameById(Integer id);
}
