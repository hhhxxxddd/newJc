package com.jinchi.common.dto;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @time: 2020/7/12 16:37
 * @description:
 */
public class SampleDeliveringBatchDTO {

    private Integer sampleId;

    private String mainBatch;

    private List<String> subBatches;

    public Integer getSampleId() {
        return sampleId;
    }

    public void setSampleId(Integer sampleId) {
        this.sampleId = sampleId;
    }

    public String getMainBatch() {
        return mainBatch;
    }

    public void setMainBatch(String mainBatch) {
        this.mainBatch = mainBatch;
    }

    public List<String> getSubBatches() {
        return subBatches;
    }

    public void setSubBatches(List<String> subBatches) {
        this.subBatches = subBatches;
    }
}
