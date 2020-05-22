package com.jinchi.common.service;



import com.jinchi.common.domain.ProcessTraceability;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface ProcessTraceabilityService {

    /**
     * 添加过程溯源
     *
     * @param traceabilitybeforedisassembly 过程溯源对象
     * @return ProductLine
     */
    ProcessTraceability add(ProcessTraceability traceabilitybeforedisassembly);

    /**
     * 更新过程溯源
     *
     * @param traceabilitybeforedisassembly 过程溯源对象
     * @return ProductLine
     */
    ProcessTraceability update(ProcessTraceability traceabilitybeforedisassembly);


    ProcessTraceability getById(Integer id);


    void deleteByIds(Long[] ids);


    void deleteById(Long id);

    Page page(String condition, Integer page, Integer size);


    List<ProcessTraceability> getAll(String condition);
}

