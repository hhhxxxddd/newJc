package com.jinchi.common.service;

import java.util.List;
import java.util.Map;

public interface AnodeCostAccountService {

    List getDate(Integer periodId);

    Map getRecordByPeriod(Integer periodId, Integer periods);

    List confirm(Integer lineCode, Integer periodId, Integer periods);
}
