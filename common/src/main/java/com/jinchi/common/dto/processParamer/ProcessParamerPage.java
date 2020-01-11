package com.jinchi.common.dto.processParamer;

import com.jinchi.common.domain.ProcessParametersListHead;

public class ProcessParamerPage {

    ProcessParametersListHead head;

    String deptName;

    String processName;

    String prepareName;

    public ProcessParametersListHead getHead() {
        return head;
    }

    public void setHead(ProcessParametersListHead head) {
        this.head = head;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getPrepareName() {
        return prepareName;
    }

    public void setPrepareName(String prepareName) {
        this.prepareName = prepareName;
    }
}
