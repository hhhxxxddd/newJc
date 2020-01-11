package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoExceptionModels;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface TechExceptionService {

    BasicInfoExceptionModels add(BasicInfoExceptionModels basicInfoExceptionModels);

    void delete(Integer id);

    void deleteByIds(Integer[] ids);

    List getAll();

    Page getAllPage(Integer page,Integer size);

    BasicInfoExceptionModels detail(Integer id);

    BasicInfoExceptionModels update(BasicInfoExceptionModels basicInfoExceptionModels);
}
