package com.jinchi.app.service;

import java.util.List;

public interface PowerCheckSiteService {

    List getAll(String condition);

    List getRealAll();

    List listByPage(String condition, Integer page, Integer size);
}
