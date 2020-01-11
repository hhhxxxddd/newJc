package com.jinchi.common.domain;

/**
 * @author XudongHu
 * @className CommonBatchNumberChild
 * @apiNote 批号的子id列表
 * @modifer
 * @since 2019/10/23 0:45
 */
public class CommonBatchNumberChild {
    private Integer id;
    private Integer commonBatchNumberId;
    private Integer childId;

    public Integer getId() {
        return id;
    }

    public CommonBatchNumberChild setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getCommonBatchNumberId() {
        return commonBatchNumberId;
    }

    public CommonBatchNumberChild setCommonBatchNumberId(Integer commonBatchNumberId) {
        this.commonBatchNumberId = commonBatchNumberId;
        return this;
    }

    public Integer getChildId() {
        return childId;
    }

    public CommonBatchNumberChild setChildId(Integer childId) {
        this.childId = childId;
        return this;
    }

    @Override
    public String toString() {
        return "CommonBatchNumberChild{" +
                "id=" + id +
                ", commonBatchNumberId=" + commonBatchNumberId +
                ", childId=" + childId +
                '}';
    }
}
