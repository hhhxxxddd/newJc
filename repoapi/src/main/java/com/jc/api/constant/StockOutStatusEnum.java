package com.jc.api.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author XudongHu
 * @apiNote 出库单状态枚举
 * @className StockOutStatusEnum
 * @modifier
 * @since 19.12.10日21:54
 */
@Getter
@AllArgsConstructor
public enum StockOutStatusEnum {
    SAVE(0, "已保存"),
    WAITING(1, "已提交"),
    OUT_START(2, "审核通过,开始出库"),
    OUT_REFUSE(3,"审核不通过,中止出库"),
    OUT_COMPLETE(4,"出库完成");

    private Integer code;
    private String description;
}
