package com.jc.api.service.restservice;

import java.util.Map;

public interface ISwmsDullStatisticService {
    /**
     * 在前端界面查询时，如果子类型选择为空，则查询对应大类的呆滞库存，
     * 但此时大类选择不允许为空；如果大类型选择为空，
     * 则查询所有库存中的呆滞库存，此时，子类型不可选择；
     * @param type
     * @param subType
     * @return
     */
    Map<String,Object> query(Integer type,Integer subType);
}
