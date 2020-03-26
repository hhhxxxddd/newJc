package com.jinchi.app.service;

import com.jinchi.app.dto.PowerCheckModelDTO;

import java.util.List;

public interface PowerCheckModelService {


    PowerCheckModelDTO detail(Long id);

    Integer getNumsOfModelsBySiteCode(Long code);

    List getModelBySiteCode(Long siteCode);
}
