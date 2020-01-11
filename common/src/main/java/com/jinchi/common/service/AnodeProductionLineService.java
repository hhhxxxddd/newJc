package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodeProductionLine;

import java.util.List;

public interface AnodeProductionLineService {

    BasicInfoAnodeProductionLine add(BasicInfoAnodeProductionLine line);

    void delete(Integer id);

    BasicInfoAnodeProductionLine update(BasicInfoAnodeProductionLine line);

    List getAll();

    List getByFlag(Boolean flag);
}
