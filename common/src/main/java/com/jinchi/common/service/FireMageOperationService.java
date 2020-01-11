package com.jinchi.common.service;

import com.jinchi.common.domain.FireMageOperationManual;
import com.jinchi.common.dto.Page;

public interface FireMageOperationService {

    FireMageOperationManual add(FireMageOperationManual manual);

    void delete(Long id);

    void deleteByIds(Long[] ids);

    FireMageOperationManual detail(Long id);

    Page page(String condition,Integer page,Integer size);

    FireMageOperationManual update(FireMageOperationManual manual);
}
