package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface IOutStockService {

    IPage getPage(Integer deptCode, String date, Page page);

    List detail(Long headId);
}
