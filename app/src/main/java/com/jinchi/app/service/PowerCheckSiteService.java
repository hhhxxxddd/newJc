package com.jinchi.app.service;

import com.jinchi.app.dto.QueryDTO;

import java.util.List;

public interface PowerCheckSiteService {

    List getAll(String condition);

    List getRealAll();

    List listByPage(QueryDTO dto);
}
