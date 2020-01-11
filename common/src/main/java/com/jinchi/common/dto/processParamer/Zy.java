package com.jinchi.common.dto.processParamer;

import com.jinchi.common.domain.ProcessParametersJqjhDetail;
import com.jinchi.common.domain.ProcessParametersJqjhPrincipalComponent;

import java.util.List;

public class Zy {

    private List<ProcessParametersJqjhPrincipalComponent> components;

    private ProcessParametersJqjhDetail detail;

    public List<ProcessParametersJqjhPrincipalComponent> getComponents() {
        return components;
    }

    public void setComponents(List<ProcessParametersJqjhPrincipalComponent> components) {
        this.components = components;
    }

    public ProcessParametersJqjhDetail getDetail() {
        return detail;
    }

    public void setDetail(ProcessParametersJqjhDetail detail) {
        this.detail = detail;
    }
}
