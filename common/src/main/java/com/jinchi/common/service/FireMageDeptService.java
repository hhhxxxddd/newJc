package com.jinchi.common.service;

import com.jinchi.common.domain.FireMageDept;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface FireMageDeptService {

    FireMageDept add(FireMageDept dept);

    void delete(Integer id);

    void deleteByIds(Integer[] ids);

    FireMageDept detail(Integer id);

    Page page(String condition, Integer page, Integer size);

    FireMageDept update(FireMageDept dept);

    List getAll(String condition);
}
