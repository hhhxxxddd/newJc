package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface IInOutAcountService {

    Boolean addStatistic(Integer year,Integer month,String startTime,String endTime);

    IPage pages(Integer type, Integer subType, Integer matId, String date, Page page);
}
