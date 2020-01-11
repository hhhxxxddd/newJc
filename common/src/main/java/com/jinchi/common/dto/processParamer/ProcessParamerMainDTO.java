package com.jinchi.common.dto.processParamer;

import com.jinchi.common.domain.ProcessParametersListHead;

public class ProcessParamerMainDTO {

    private ProcessParametersListHead head;

    private Hc hc;

    private Zy zy;

    private Ch ch;

    private ProAndLine proAndLine;

    private String processName;

    private Integer isUrgent;

    private Integer auditId;

    private String deptName;

    private String prepareName;

    private String auditName;

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getPrepareName() {
        return prepareName;
    }

    public void setPrepareName(String prepareName) {
        this.prepareName = prepareName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(Integer isUrgent) {
        this.isUrgent = isUrgent;
    }

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public ProcessParametersListHead getHead() {
        return head;
    }

    public void setHead(ProcessParametersListHead head) {
        this.head = head;
    }

    public Hc getHc() {
        return hc;
    }

    public void setHc(Hc hc) {
        this.hc = hc;
    }

    public Zy getZy() {
        return zy;
    }

    public void setZy(Zy zy) {
        this.zy = zy;
    }

    public Ch getCh() {
        return ch;
    }

    public void setCh(Ch ch) {
        this.ch = ch;
    }

    public ProAndLine getProAndLine() {
        return proAndLine;
    }

    public void setProAndLine(ProAndLine proAndLine) {
        this.proAndLine = proAndLine;
    }
}
