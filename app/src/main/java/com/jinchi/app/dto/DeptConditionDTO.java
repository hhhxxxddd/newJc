package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeptConditionDTO {

    Integer deptId;

    String condition;

    Integer size;

    Integer page;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getSize() {
        return size;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setPage(Integer page) {
        this.page = page;
    }
}
