package com.jinchi.common.service;

import com.jinchi.common.domain.FirePosition;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface FirePositionService {

    FirePosition add(String name);

    void delete(Integer id);

    void deleteIds(Integer[] ids);

    Page page(String name,Integer page,Integer size);

    FirePosition update(FirePosition firePosition);

    void assign(Integer positionId,Integer[] userIds);

    void assion(Integer positionId,Long[] itemIds);

    List getUser(Integer positionId);

    List getItems(Integer positionId);

}
