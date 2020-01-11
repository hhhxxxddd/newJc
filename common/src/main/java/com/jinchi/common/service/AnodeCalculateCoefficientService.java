package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodeCalculateCoefficient;

public interface AnodeCalculateCoefficientService {

    BasicInfoAnodeCalculateCoefficient add(BasicInfoAnodeCalculateCoefficient record);

    BasicInfoAnodeCalculateCoefficient update(BasicInfoAnodeCalculateCoefficient record);

    BasicInfoAnodeCalculateCoefficient getOne();
}
