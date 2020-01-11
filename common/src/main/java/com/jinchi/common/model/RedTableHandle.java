package com.jinchi.common.model;

import com.jinchi.common.constant.BatchTypeEnum;

/**
 * @author：XudongHu
 * @className:RedTableHandle 红单 处理者
 * @description:
 * @date:14:53 2019/2/27
 * @Modifer:
 */
public class RedTableHandle extends TaskObserver {
    private Integer type = BatchTypeEnum.REPO_RED_TABLE.typeCode();
    @Override
    public void receive(Object o) {

    }

    @Override
    public Integer getType() {
        return type;
    }

    @Override
    public RedTableHandle setType(Integer type) {
        this.type = type;
        return this;
    }
}
