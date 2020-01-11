package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodePlcAddress;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface AnodePlcAddressService {

    BasicInfoAnodePlcAddress add(BasicInfoAnodePlcAddress anodePlcAddress);

    BasicInfoAnodePlcAddress update(BasicInfoAnodePlcAddress anodePlcAddress);

    void deleteById(Integer id);

    void deleteByIds(Integer[] ids);

    List getAll(String condition);

    Page page(String condition, Integer page, Integer size);

    BasicInfoAnodePlcAddress getById(Integer id);
}
