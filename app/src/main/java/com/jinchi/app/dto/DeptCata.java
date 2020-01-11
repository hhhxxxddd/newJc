package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class DeptCata {

    Integer parentId;
    String parentName;
    List<SonDept> sons;

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<SonDept> getSons() {
        return sons;
    }

    public void setSons(List<SonDept> sons) {
        this.sons = sons;
    }
}
