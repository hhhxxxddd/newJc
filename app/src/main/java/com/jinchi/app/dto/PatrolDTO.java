package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PatrolDTO {
    Long planId;

    String planName;

    String deptName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    Date planDate;

    Integer checkType;//0:机械，1:电气

    Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date reveiveDate;

    List<PatrolItemsDTO> pis;

    List<PatrolLocationDTO> pls;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date finishDate;

    String patrolComment;

    Integer flag;//1：暂存，2，待接单->已接单，3，已接单->已完成

    Integer people;

    public String getPatrolComment() {
        return patrolComment;
    }

    public void setPatrolComment(String patrolComment) {
        this.patrolComment = patrolComment;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Integer getCheckType() {
        return checkType;
    }

    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getReveiveDate() {
        return reveiveDate;
    }

    public void setReveiveDate(Date reveiveDate) {
        this.reveiveDate = reveiveDate;
    }

    public List<PatrolItemsDTO> getPis() {
        return pis;
    }

    public void setPis(List<PatrolItemsDTO> pis) {
        this.pis = pis;
    }

    public List<PatrolLocationDTO> getPls() {
        return pls;
    }

    public void setPls(List<PatrolLocationDTO> pls) {
        this.pls = pls;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
