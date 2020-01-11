package com.jinchi.common.dto;

import com.jinchi.common.domain.ProductionBatchDeviceMap;
import com.jinchi.common.domain.ProductionBatchInstrumentMap;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-23 16:16
 * @description:
 **/

public class BatchDataMapDTO {
    List<ProductionBatchInstrumentMap> batchInstrumentMapList;
    List<ProductionBatchDeviceMap> batchDeviceMapList;
    List<ProductionBatchAssayMapDTO> batchAssayMapList;

    public List<ProductionBatchInstrumentMap> getBatchInstrumentMapList() {
        return batchInstrumentMapList;
    }

    public void setBatchInstrumentMapList(List<ProductionBatchInstrumentMap> batchInstrumentMapList) {
        this.batchInstrumentMapList = batchInstrumentMapList;
    }

    public List<ProductionBatchDeviceMap> getBatchDeviceMapList() {
        return batchDeviceMapList;
    }

    public void setBatchDeviceMapList(List<ProductionBatchDeviceMap> batchDeviceMapList) {
        this.batchDeviceMapList = batchDeviceMapList;
    }

    public List<ProductionBatchAssayMapDTO> getBatchAssayMapList() {
        return batchAssayMapList;
    }

    public void setBatchAssayMapList(List<ProductionBatchAssayMapDTO> batchAssayMapList) {
        this.batchAssayMapList = batchAssayMapList;
    }
}
