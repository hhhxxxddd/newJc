package com.jinchi.common.service;

import com.jinchi.common.dto.Page;

public interface UserProcessService {

    void update(Integer userId,Integer[] processDeptIds);

    Page page(Integer deptId,String condition,Integer page,Integer size);

}
