package com.jinchi.common.domain;

import java.util.List;

public class BatchRuleDTO {
    String position;
    String rule;
    List<String> values;
    String defaultValue;
    Integer defaultCode;
    List<Integer> codes;

    public Integer getDefaultCode() {
        return defaultCode;
    }

    public void setDefaultCode(Integer defaultCode) {
        this.defaultCode = defaultCode;
    }

    public List<Integer> getCodes() {
        return codes;
    }

    public void setCodes(List<Integer> codes) {
        this.codes = codes;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "BatchRuleDTO{" +
                "rule='" + rule + '\'' +
                ", values=" + values +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}
