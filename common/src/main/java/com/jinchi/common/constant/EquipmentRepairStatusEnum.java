package com.jinchi.common.constant;

import com.jinchi.common.domain.EquipmentRepairRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:EquipmentRepairEnum
 * @description: 设备维修状态
 * @date:16:20 2019/3/8
 * @Modifer:
 */
public enum EquipmentRepairStatusEnum {
    WAIT(0, "待接单"),
    ORDER(1, "已接单"),
    FINISHED(2, "已完工"),
    RATED(3, "已评价");

    private Integer status;
    private String description;

    private static final Map<Integer, EquipmentRepairStatusEnum> MAP = new HashMap<>();

    static {
        for (EquipmentRepairStatusEnum e : EquipmentRepairStatusEnum.values()) {
            MAP.put(e.getStatus(), e);
        }
    }

    private static final Map<Object, Object> STATUS_DETAIL_MAP = new HashMap<>();

    static {
        STATUS_DETAIL_MAP.put("time", "");
        STATUS_DETAIL_MAP.put("description", "");
    }

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    EquipmentRepairStatusEnum(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public static Map<Object, Object> getApplyDetail(EquipmentRepairRecord e) {
        if (null != e.getRepairApplyTime()) STATUS_DETAIL_MAP.put("time", SDF.format(e.getRepairApplyTime()));
        STATUS_DETAIL_MAP.put("description", e.getFailureDescription());
        return STATUS_DETAIL_MAP;
    }

    public static Map<Object, Object> getOrderDetail(EquipmentRepairRecord e) {
        if (null != e.getOrderTime()) {
            STATUS_DETAIL_MAP.put("time", SDF.format(e.getOrderTime()));
            long oldTime = e.getRepairApplyTime().getTime();
            long newTime = e.getOrderTime().getTime();
            Long interval = newTime-oldTime;
            STATUS_DETAIL_MAP.put("description",
                    interval/1000*60*60+":"+interval%1000*60*60+":");
        }
        return STATUS_DETAIL_MAP;
    }

    public static Map<Object, Object> getFinshedDetail(EquipmentRepairRecord e) {
        if (null != e.getFinishedTime()) STATUS_DETAIL_MAP.put("time", SDF.format(e.getFinishedTime()));
        STATUS_DETAIL_MAP.put("description", e.getFinishedConclusion());
        return STATUS_DETAIL_MAP;
    }

    public static Map<Object, Object> getRateDetail(EquipmentRepairRecord e) {
        if (null != e.getRateTime()) STATUS_DETAIL_MAP.put("time", SDF.format(e.getRateTime()));
        STATUS_DETAIL_MAP.put("description", e.getRate());
        return STATUS_DETAIL_MAP;
    }

    public Integer getStatus() {
        return status;
    }

    public EquipmentRepairStatusEnum setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EquipmentRepairStatusEnum setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "EquipmentRepairStatusEnum{" +
                "status=" + status +
                ", description='" + description + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Date old = new Date(0);
        Date date = new Date();
        Long Interval = (long)30;
        System.out.println(Interval / 60 + "小时" + Interval % 60 + "分钟");
    }
}
