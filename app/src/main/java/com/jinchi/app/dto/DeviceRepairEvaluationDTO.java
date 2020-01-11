package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceRepairEvaluationDTO {
    Long repairId;
    List<EvaluItem> evaluItems;
    String comment;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Long getRepairId() {
        return repairId;
    }

    public void setRepairId(Long repairId) {
        this.repairId = repairId;
    }

    public List<EvaluItem> getEvaluItems() {
        return evaluItems;
    }


    public void setEvaluItems(List<EvaluItem> evaluItems) {
        this.evaluItems = evaluItems;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
