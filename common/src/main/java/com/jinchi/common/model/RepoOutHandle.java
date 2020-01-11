package com.jinchi.common.model;

import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.service.RepoOutApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author：XudongHu
 * @className:RepoOutHandle 出库 处理者
 * @description:
 * @date:14:52 2019/2/27
 * @Modifer:
 */
@Component
public class RepoOutHandle extends TaskObserver {
    @Autowired
    private RepoOutApplyService repoOutApplyService;



    private Integer type = BatchTypeEnum.REPO_OUT_APPLY_RAW.typeCode();

    @Override
    public void receive(Object o) {
        TaskMessage t = (TaskMessage) o;
        repoOutApplyService.sendOutMessage(t.getBatchNumberId());
    }

    @Override
    public Integer getType() {
        return type;
    }

    @Override
    public RepoOutHandle setType(Integer type) {
        this.type = type;
        return this;
    }
}
