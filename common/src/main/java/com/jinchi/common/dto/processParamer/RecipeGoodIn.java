package com.jinchi.common.dto.processParamer;

import com.jinchi.common.domain.ProcessParametersListHead;

public class RecipeGoodIn {
    ProcessParametersListHead head;

    Long paramId;

    Float Ni;

    Float Co;

    Float Mn;

    Float solidContent;

    String product;

    String processName; //工序名称

    String deptName;

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public ProcessParametersListHead getHead() {
        return head;
    }

    public void setHead(ProcessParametersListHead head) {
        this.head = head;
    }

    public Float getNi() {
        return Ni;
    }

    public void setNi(Float ni) {
        Ni = ni;
    }

    public Float getCo() {
        return Co;
    }

    public void setCo(Float co) {
        Co = co;
    }

    public Float getMn() {
        return Mn;
    }

    public void setMn(Float mn) {
        Mn = mn;
    }

    public Float getSolidContent() {
        return solidContent;
    }

    public void setSolidContent(Float solidContent) {
        this.solidContent = solidContent;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }
}
