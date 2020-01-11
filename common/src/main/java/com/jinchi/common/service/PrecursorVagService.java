package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoVgaPoint;
import com.jinchi.common.dto.Page;
import io.swagger.models.auth.In;

import java.util.List;

public interface PrecursorVagService {

    BasicInfoVgaPoint add(BasicInfoVgaPoint basicInfoVgaPoint);

    void delete(Integer id);

    void deleteByIds(Integer[] ids);

    List getAll(String condition);

    Page getAllByPage(String condition, Integer page, Integer size);

    BasicInfoVgaPoint update(BasicInfoVgaPoint basicInfoVgaPoint);

    BasicInfoVgaPoint getById(Integer id);
}
