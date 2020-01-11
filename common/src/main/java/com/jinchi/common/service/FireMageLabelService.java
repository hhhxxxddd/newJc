package com.jinchi.common.service;

import com.jinchi.common.domain.FireMageDept;
import com.jinchi.common.domain.FireMageLabelItems;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.fireMage.FireMagePpItemsDTO;
import com.jinchi.common.dto.fireMage.FireMageTestItemsDTO;

import java.util.List;

public interface FireMageLabelService {

    FireMageLabelItems add(List<Long> items);

    void delete(Long id);

    void deleteByIds(Long[] ids);

    List<FireMageTestItemsDTO> detail(Long id);

    void update(Long id,List<Long> items);

    List getAll(String condition);
}
