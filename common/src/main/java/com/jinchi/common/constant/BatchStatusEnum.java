package com.jinchi.common.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author：XudongHu
 * @description: 状态枚举
 * @date:17:28 2018/11/19
 */
public enum BatchStatusEnum {


    /**
     * 保存状态相关 CommonBatchNumber 中的status
     */
    SAVE("保存待提交", -1),
    COMMIT("提交待审核", 0),
    AUDITING("审核中", 1),
    PASS("审核通过", 2),
    NOT_PASS("审核未通过", 3),
    HALF_PASS("部分审核",12),

    //未知
    QUALIFIED("合格", 4),
    UNQUALIFIED("不合格", 5),
    UN_ORDER("未接单", 6),
    ORDER("已接单", 7),
    FINISHED("已完成", 8),
    UNFINISHED("未完成", 9),
    DOING("进行中", 10),
    RATED("已评价", 11);

    private String description;
    private Integer code;

    static Map<Integer, String> MAP = new HashMap<>();

    static {
        Arrays.stream(BatchStatusEnum.values()).forEach(
                e -> MAP.put(e.code, e.description)
        );
    }

    BatchStatusEnum(String description, Integer code) {
        this.description = description;
        this.code = code;
    }

    public Integer status() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static String getDescription(Integer code){
        return MAP.getOrDefault(code,"");
    }

    @Override
    public String toString() {
        return "BatchStatusEnum{" +
                "code=" + code +
                '}';
    }
}
