package com.jinchi.common.service;

import com.jinchi.common.domain.FireMagePpItems;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.fireMage.FireMagePpItemsDTO;

import java.util.List;

public interface FireMageBatchItemsService {

    FireMagePpItems add(Integer processId,Integer productId,List<Long> itemIds);

    void delete(Long id);

    void deleteByIds(Long[] ids);

    FireMagePpItemsDTO detail(Long id);

    Page page(String condition, Integer page, Integer size);

    void update(Long id,List<Long> itemIds);

    List getAll(String condition);
}
