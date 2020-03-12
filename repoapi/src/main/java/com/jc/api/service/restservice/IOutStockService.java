package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

public interface IOutStockService {

    IPage getPage(Integer deptCode, String date, Page page);

    List detail(Long headId);

    Map getByCommonBatchId(Integer cId);
}
