package com.jinchi.common.service;

import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.processParamer.ProcessParamerMainDTO;

import java.util.List;

public interface ProcessParamerService {

    Integer saveOrCommit(ProcessParamerMainDTO processParamerMainDTO, Integer flag);

    ProcessParamerMainDTO detail(Long id);

    Page page(String condition,Integer status,Integer page,Integer size);

    void delete(Long id);

    void deleteByIds(Long[] ids);

    Page mixRecipe(String condition,Integer page,Integer size);

    Page compoundRecipe(String condition,Integer page,Integer size);

    List mixRecipeList(String condition);

    List compoundRecipeList(String condition);

    ProcessParamerMainDTO detailByBatch(Integer batchId);

    void publish(Long id);

    List getProcessParam();
}
