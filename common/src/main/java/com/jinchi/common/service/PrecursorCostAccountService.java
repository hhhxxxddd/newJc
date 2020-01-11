package com.jinchi.common.service;

import java.util.List;

public interface PrecursorCostAccountService {

    List getStartDate(Integer periodCode);

    List mainMaterialConfirm(Integer lineCode, Integer periodCode, String startTime);

    List auxiliaryConfirm(Integer lineCode, Integer periodCode, String startTime);
}
