package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoCompoundCellVolumes;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface PrecursorCompoundCellVolumesService {

    BasicInfoCompoundCellVolumes add(BasicInfoCompoundCellVolumes basicInfoCompoundCellVolumes);

    BasicInfoCompoundCellVolumes getOne(Integer id);

    void deleteById(Integer id);

    void deleteByIds(Integer[] ids);

    List getAll(String condition);

    Page page(String condition, Integer page, Integer size);

    BasicInfoCompoundCellVolumes update(BasicInfoCompoundCellVolumes basicInfoCompoundCellVolumes);

    Float getSumOfVolumesValue();
}
