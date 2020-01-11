package com.jinchi.common.dto.technique;

import com.jinchi.common.domain.TechniqueRawTestItemStandard;
import com.jinchi.common.domain.TestItem;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author：XudongHu
 * @className:TechniqueRawTestItemDTO
 * @description:
 * @date:0:03 2018/12/28
 */
public class TechniqueRawTestItemDTO {
    @ApiModelProperty("检测项目")
    private TestItem testItem;

    @ApiModelProperty("标准值")
    private TechniqueRawTestItemStandard techniqueRawTestItemStandard;

    public TestItem getTestItem() {
        return testItem;
    }

    public TechniqueRawTestItemDTO setTestItem(TestItem testItem) {
        this.testItem = testItem;
        return this;
    }

    public TechniqueRawTestItemStandard getTechniqueRawTestItemStandard() {
        return techniqueRawTestItemStandard;
    }

    public TechniqueRawTestItemDTO setTechniqueRawTestItemStandard(TechniqueRawTestItemStandard techniqueRawTestItemStandard) {
        this.techniqueRawTestItemStandard = techniqueRawTestItemStandard;
        return this;
    }

    @Override
    public String toString() {
        return "TechniqueRawTestItemDTO{" +
                "testItem=" + testItem +
                ", techniqueRawTestItemStandard=" + techniqueRawTestItemStandard +
                '}';
    }
}
