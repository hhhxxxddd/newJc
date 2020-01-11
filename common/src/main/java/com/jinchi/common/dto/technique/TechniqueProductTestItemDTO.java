package com.jinchi.common.dto.technique;

import com.jinchi.common.domain.TechniqueProductTestItemStandard;
import com.jinchi.common.domain.TestItem;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author：XudongHu
 * @className:TechniqueProductTestItemDTO
 * @description:
 * @date:15:12 2018/12/28
 */
public class TechniqueProductTestItemDTO {
    @ApiModelProperty("检测项目")
    private TestItem testItem;

    @ApiModelProperty("标准值")
    private TechniqueProductTestItemStandard techniqueProductTestItemStandard;

    public TestItem getTestItem() {
        return testItem;
    }

    public TechniqueProductTestItemDTO setTestItem(TestItem testItem) {
        this.testItem = testItem;
        return this;
    }

    public TechniqueProductTestItemStandard getTechniqueProductTestItemStandard() {
        return techniqueProductTestItemStandard;
    }

    public TechniqueProductTestItemDTO setTechniqueProductTestItemStandard(TechniqueProductTestItemStandard techniqueProductTestItemStandard) {
        this.techniqueProductTestItemStandard = techniqueProductTestItemStandard;
        return this;
    }

    @Override
    public String toString() {
        return "TechniqueProductTestItemDTO{" +
                "testItem=" + testItem +
                ", techniqueProductTestItemStandard=" + techniqueProductTestItemStandard +
                '}';
    }
}
