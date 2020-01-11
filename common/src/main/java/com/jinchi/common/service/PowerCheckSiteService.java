package com.jinchi.common.service;

import com.jinchi.common.domain.PowerCheckSite;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface PowerCheckSiteService {

    PowerCheckSite add(PowerCheckSite site);

    List getAll(String condition);

    Page listByPage(String condition, Integer page, Integer size);

    PowerCheckSite update(PowerCheckSite checkSite);

    void deleteById(Long id);

    void deleteByIds(Long[] ids);

    PowerCheckSite getOne(Long id);
}
