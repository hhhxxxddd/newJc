package com.jinchi.common.service;


import com.jinchi.common.domain.TraceabilityBeforeDisassembly;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface TraceabilityBeforeDisassemblyService {

    /**
     * 添加拆解前溯源
     *
     * @param traceabilitybeforedisassembly 产品线对象
     * @return ProductLine
     */
    TraceabilityBeforeDisassembly add(TraceabilityBeforeDisassembly traceabilitybeforedisassembly);

    /**
     * 更新拆解前溯源
     *
     * @param traceabilitybeforedisassembly 产品线对象
     * @return ProductLine
     */
    TraceabilityBeforeDisassembly update(TraceabilityBeforeDisassembly traceabilitybeforedisassembly);


    TraceabilityBeforeDisassembly getById(Integer id);


    void deleteByIds(Long[] ids);


    void deleteById(Long id);

    Page page(String condition, Integer page, Integer size);


    List<TraceabilityBeforeDisassembly> getAll(String condition);
}

