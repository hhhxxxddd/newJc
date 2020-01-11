package com.jinchi.common.service;

import com.jinchi.common.dto.Page;

import java.util.List;

public interface ProductionBatchRetrospectService {

    List getAll(String condition);

    Page page(String condition, Integer page, Integer size);
}
