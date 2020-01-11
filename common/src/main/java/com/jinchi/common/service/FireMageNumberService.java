package com.jinchi.common.service;

import com.jinchi.common.dto.FireNumberBase;

import java.util.List;

public interface FireMageNumberService {

    List getHead();

    void isEnable(Integer position);

    List detail(Integer position);

    void save(Integer position, List<FireNumberBase> rules, String strs);

    List getAllInfos();
}
