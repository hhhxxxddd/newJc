package com.jinchi.common.service;

import com.jinchi.common.domain.FireMageTestItems;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface FireMageTestItemsService {
    
    FireMageTestItems add(FireMageTestItems manual);

    void delete(Long id);

    void deleteByIds(Long[] ids);

    FireMageTestItems detail(Long id);

    Page page(String condition, Integer page, Integer size);

    FireMageTestItems update(FireMageTestItems manual);

    List getAll(String condition);

    List getAllByProcessByProdut(Integer processCode,Integer productCode);
}
