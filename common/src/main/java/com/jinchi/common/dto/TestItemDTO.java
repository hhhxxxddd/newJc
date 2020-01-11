package com.jinchi.common.dto;

import java.util.List;

/**
 * @author：YuboWu
 * @description: 项目检验
 */
public class TestItemDTO {
    private Integer id;
    private List<TestItemResultRecordDTO> testItemResultRecordDTOS;
    private Integer isQualified;
    private Integer auditStatus;
    private Integer isUrgent;


    public List<TestItemResultRecordDTO> getTestItemResultRecordDTOS() {
        return testItemResultRecordDTOS;
    }

    public void setTestItemResultRecordDTOS(List<TestItemResultRecordDTO> testItemResultRecordDTOS) {
        this.testItemResultRecordDTOS = testItemResultRecordDTOS;
    }

    public Integer getIsQualified() {
        return isQualified;
    }

    public void setIsQualified(Integer isQualified) {
        this.isQualified = isQualified;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(Integer isUrgent) {
        this.isUrgent = isUrgent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
