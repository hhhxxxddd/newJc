package com.jinchi.app.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：XudongHu
 * @description: 质量模块 数据类型枚举
 * @date:16:55 2018/11/19
 */
public enum BatchTypeEnum {
    /**
     * 数据类型,对应CommonBatchNumber中的dataType
     */
    QUALITY_AUDIT_TASK("流程管理数据", 1, "LCGL",1),
    QUALITY_PRODUCT("制程检测数据", 2, "ZCGL",1),
    QUALITY_SAMPLE_TEST("样品检测数据", 3, "YPJC",1),
    REPO_OUT_APPLY_RAW("原材料出库申请数据", 4, "YLCK",1),
    REPO_OUT_APPLY_END("成品出库申请数据", 5, "CPCK",1),
    REPO_RED_TABLE("红单申请数据", 6, "HDSQ",1),
    QUALITY_PURCHASE("进货检验数据", 7, "JHJC",1),
    QUALITY_PRODUCT_TEST("成品检验数据", 8, "CPJC",1),
    QUALITY_RAW_TEST("原材料检测数据", 9, "YLJC",1),
    QUALITY_INTERMEDIATE_TEST("中间品检测数据", 10, "ZJJC",1),
    UNQUALIFIED_RECORD("不合格评审数据", 11, "BHGP",1),
    UNQUALIFIED_TRACING("不合格追踪数据", 12, "BHGZ",1),
    TECH_RAW_STANDARD("原材料标准数据",13,"YLBZ",1),
    TECH_PRODUCT_STANDARD("成品标准数据",14,"CPBZ",1),
    EQUIPMENT_INSTRUCTOR("设备指导数据",15,"SBZD",1);

    private String description; //描述
    private Integer typeCode;   //类型值
    private String markCode;   //代号
    private Integer count;

    BatchTypeEnum(String description, Integer typeCode, String markCode, Integer count) {
        this.typeCode = typeCode;
        this.description = description;
        this.markCode = markCode;
        this.count = count;
    }

    //计数器设置
    public Integer getCount() {
        return count;
    }

    public BatchTypeEnum setCount(Integer count) {
        this.count = count;
        return this;
    }

    //出库map——————————————————————————————————————————————————————————————————————————————————————————
    private static final Map<Integer, BatchTypeEnum> OutMap = new HashMap<>();

    static {
        OutMap.put(1, REPO_OUT_APPLY_RAW);
        OutMap.put(3, REPO_OUT_APPLY_END);
    }

    public static BatchTypeEnum getOutTypeByMaterialType(Integer materialType) {
        return OutMap.getOrDefault(materialType, null);
    }

    //样品送检map————————————————————————————————————————————————————————————————————————————————————————
    private static final Map<Integer, BatchTypeEnum> SampleMap = new HashMap<>();

    static {
        SampleMap.put(1, QUALITY_RAW_TEST);
        SampleMap.put(2, QUALITY_INTERMEDIATE_TEST);
        SampleMap.put(3, QUALITY_PRODUCT_TEST);
    }

    public static BatchTypeEnum getSampleTypeByMaterialType(Integer materialType) {
        return SampleMap.getOrDefault(materialType, null);
    }
    //————————————————————————————————————————————————————————————————————————————————————————————————--



    //通过typeCode获取枚举类
    public static BatchTypeEnum getByDataType(Integer dataType) {
        for (BatchTypeEnum batchTypeEnum : BatchTypeEnum.values()) {
            if (batchTypeEnum.typeCode.equals(dataType))
                return batchTypeEnum;
        }
        return null;
    }

    public Integer typeCode() {
        return typeCode;
    }

    public String description() {
        return description;
    }

    public String markCode() {
        return markCode;
    }

    @Override
    public String toString() {
        return "BatchTypeEnum{" +
                "typeCode=" + typeCode +
                ", description='" + description + '\'' +
                ", markCode='" + markCode + '\'' +
                '}';
    }
}
