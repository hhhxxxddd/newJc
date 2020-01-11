package com.jinchi.common.dto.quality.unqualified;

import java.util.List;

/**
 * @author：XudongHu
 * @className:UnqualifiedDetailDTO
 * @description:
 * @date:0:04 2019/1/25
 * @Modifer:
 */
public class UnqualifiedDetailDTO {
    private Integer testerId;

    private List<UnqualifiedResultDTO> resultDTOList;

    public Integer getTesterId() {
        return testerId;
    }

    public UnqualifiedDetailDTO setTesterId(Integer testerId) {
        this.testerId = testerId;
        return this;
    }

    public List<UnqualifiedResultDTO> getResultDTOList() {
        return resultDTOList;
    }

    public UnqualifiedDetailDTO setResultDTOList(List<UnqualifiedResultDTO> resultDTOList) {
        this.resultDTOList = resultDTOList;
        return this;
    }
}
