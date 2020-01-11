package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodeProcessName;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface AnodeProcessNameService {

    BasicInfoAnodeProcessName add(BasicInfoAnodeProcessName processName);

    BasicInfoAnodeProcessName update(BasicInfoAnodeProcessName processName);

    void delete(Integer id);

    List getAll();

    Page page(Integer page, Integer size);
}
