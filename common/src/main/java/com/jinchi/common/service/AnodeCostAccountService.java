package com.jinchi.common.service;

import java.util.List;

public interface AnodeCostAccountService {

    List getDate(Integer periodId);

    List confirm(Integer lineCode, Integer periodId, Integer periods);
}
