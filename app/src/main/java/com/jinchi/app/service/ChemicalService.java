package com.jinchi.app.service;

import com.jinchi.app.dto.ChemicalAppDTO;
import com.jinchi.app.dto.Page;

import java.util.List;

public interface ChemicalService {

    List getAll(Integer userId, String condition);

    Page page(Integer userId,String condition,Integer page,Integer size);

    ChemicalAppDTO detail(Integer id);
}
