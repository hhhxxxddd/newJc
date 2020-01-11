package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoPrecursorVgaLineWeight;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.VgaWeightDTO;

import java.util.List;

public interface PrecursorVagWeightService {

    String add(List<BasicInfoPrecursorVgaLineWeight> weightList);

    void delete(Integer id);

    void deleteByIds(Integer[] ids);

    List getAll(String condition);

    Page getAllByPage(String condition,Integer page,Integer size);

    List<BasicInfoPrecursorVgaLineWeight> update(List<BasicInfoPrecursorVgaLineWeight> weightList);

    VgaWeightDTO getInfoByVgaId(Integer vgaId);
}
