package com.jc.api.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author XudongHu
 * @apiNote 物料状态枚举
 * @className MaterialStatusEnum
 * @modifier
 * @since 19.11.7日21:14
 */
@Getter
@AllArgsConstructor
public enum MaterialStatusEnum {
    HAVE_OUT(0, "已出库"),
    IN_REPO(1, "在库中"),
    WAIT_FOR_OUT(2, "待出库");

    private Integer code;
    private String description;
}
