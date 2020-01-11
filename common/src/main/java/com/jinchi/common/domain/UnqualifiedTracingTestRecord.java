package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author：XudongHu
 * @className:UnqualifiedTracingResultRecord
 * @description: 不合格追踪 关系表     UnqualifiedTestReportRecord:UnqualifiedTracingRecord
 *                                                         N:1
 * @date:22:30 2019/1/24
 * @Modifer:
 */
@ApiModel(description = "不合格追踪 关系表")
public class UnqualifiedTracingTestRecord {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("不合格评审表")
    private Integer unqualifiedTestReportRecordId;

    @ApiModelProperty("不合格追踪表")
    private Integer unqualifiedTracingRecordId;

    public Integer getId() {
        return id;
    }

    public UnqualifiedTracingTestRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUnqualifiedTestReportRecordId() {
        return unqualifiedTestReportRecordId;
    }

    public UnqualifiedTracingTestRecord setUnqualifiedTestReportRecordId(Integer unqualifiedTestReportRecordId) {
        this.unqualifiedTestReportRecordId = unqualifiedTestReportRecordId;
        return this;
    }

    public Integer getUnqualifiedTracingRecordId() {
        return unqualifiedTracingRecordId;
    }

    public UnqualifiedTracingTestRecord setUnqualifiedTracingRecordId(Integer unqualifiedTracingRecordId) {
        this.unqualifiedTracingRecordId = unqualifiedTracingRecordId;
        return this;
    }

    @Override
    public String toString() {
        return "UnqualifiedTracingTestRecord{" +
                "id=" + id +
                ", unqualifiedTestReportRecordId=" + unqualifiedTestReportRecordId +
                ", unqualifiedTracingRecordId=" + unqualifiedTracingRecordId +
                '}';
    }
}
