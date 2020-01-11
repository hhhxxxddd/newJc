package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PatrolItemsDTO {
    Long patrolItemsId;

    String item;

    String content;

    Integer result;

    String memo;

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Long getPatrolItemsId() {
        return patrolItemsId;
    }

    public void setPatrolItemsId(Long patrolItemsId) {
        this.patrolItemsId = patrolItemsId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
