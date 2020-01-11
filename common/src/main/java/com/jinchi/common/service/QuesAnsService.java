package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoQuesAns;
import com.jinchi.common.dto.Page;

public interface QuesAnsService {

    BasicInfoQuesAns add(BasicInfoQuesAns basicInfoQuesAns);

    void delete(Long id);

    void deleteByIds(Long[] ids);

    BasicInfoQuesAns getById(Long id);

    Page page(String condition,Integer page,Integer size);

    BasicInfoQuesAns update(BasicInfoQuesAns basicInfoQuesAns);
}
