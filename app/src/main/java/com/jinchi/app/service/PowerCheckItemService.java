package com.jinchi.app.service;

import com.jinchi.app.domain.PowerCheckItem;
import com.jinchi.app.dto.Page;

import java.util.List;
import java.util.Map;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-21 14:36
 * @description:
 **/

public interface PowerCheckItemService {

    PowerCheckItem add(PowerCheckItem item);

    PowerCheckItem update(PowerCheckItem item);

    List listPlaceBySite(Long siteCode);

    void deleteById(Long id);

    void deleteByIds(Long[] ids);

    List getAll(String condition);

    Page listByPage(String condition, Integer page, Integer size);

    Map<String, Object> getOne(Long id);

    List getByIds(Long[] ids);

    List getItemByConditions(Long siteCode, String place);
}
