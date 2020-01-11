package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoPrecursorProcessType;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface PrecursorProcessTypeService {

    BasicInfoPrecursorProcessType add(BasicInfoPrecursorProcessType basicInfoPrecursorProcessType);

    void delete(Integer id);

    BasicInfoPrecursorProcessType update(BasicInfoPrecursorProcessType basicInfoPrecursorProcessType);

    Page page(Integer page,Integer size);

    List getAll();

    List getDataByTypes(Byte types);

    String getProcessNameById(Integer id);

    List getByType(Integer flag);
}
