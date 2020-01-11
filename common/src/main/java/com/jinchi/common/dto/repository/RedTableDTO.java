package com.jinchi.common.dto.repository;

import com.jinchi.common.domain.CommonBatchNumber;
import com.jinchi.common.domain.RepoBaseSerialNumber;
import com.jinchi.common.domain.RepoRedTable;
import io.swagger.annotations.ApiModelProperty;

public class RedTableDTO {
    @ApiModelProperty("红单实体")
    private RepoRedTable repoRedTable;
    @ApiModelProperty("公共批号实体")
    private CommonBatchNumber commonBatchNumber;
    @ApiModelProperty("材料实体")
    private RepoBaseSerialNumber repoBaseSerialNumber;
    @ApiModelProperty("申请人名称")
    private String createPersonName;

    public String getCreatePersonName() {
        return createPersonName;
    }

    public void setCreatePersonName(String createPersonName) {
        this.createPersonName = createPersonName;
    }

    public RepoRedTable getRepoRedTable() {
        return repoRedTable;
    }

    public void setRepoRedTable(RepoRedTable repoRedTable) {
        this.repoRedTable = repoRedTable;
    }

    public CommonBatchNumber getCommonBatchNumber() {
        return commonBatchNumber;
    }

    public void setCommonBatchNumber(CommonBatchNumber commonBatchNumber) {
        this.commonBatchNumber = commonBatchNumber;
    }

    public RepoBaseSerialNumber getRepoBaseSerialNumber() {
        return repoBaseSerialNumber;
    }

    public void setRepoBaseSerialNumber(RepoBaseSerialNumber repoBaseSerialNumber) {
        this.repoBaseSerialNumber = repoBaseSerialNumber;
    }
}
