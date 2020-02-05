package com.jc.api.service.restservice;

import java.util.List;

public interface ISwmsStreamStatisticService {

    List monthView(Integer year, Integer month);

    List yearView(Integer year);

}
