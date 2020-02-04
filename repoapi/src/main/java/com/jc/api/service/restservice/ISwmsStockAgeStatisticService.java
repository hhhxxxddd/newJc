package com.jc.api.service.restservice;

import com.jc.api.entity.SwmsBasicStockAgingRange;

import java.util.Map;

public interface ISwmsStockAgeStatisticService {

    Map turnoverRate(Integer type,Integer subType,String time);

    Map distribution(SwmsBasicStockAgingRange range);
}
