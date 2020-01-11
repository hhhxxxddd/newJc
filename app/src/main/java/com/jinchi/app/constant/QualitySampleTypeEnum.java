package com.jinchi.app.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:QualitySampleStatusEnum
 * @description:
 * @date:22:58 2018/12/15
 */
public enum QualitySampleTypeEnum {
    /**
     * 样品检测类型,对应SampleDeliveringRecord中的type
     * 同时也对应库存RepoStock中的3中物料类型materialType
     */
    SAMPLE_RAWMATERIAL("原材料", 1,"RAW"),
    SAMPLE_INTERMEDIATE("中间品", 2,"MED"),
    SAMPLE_ENDPRODUCT("成品", 3,"PRO");

    private String sampleType;

    private static Map<String, Integer> cache = new HashMap<>();
    static {
        for(QualitySampleTypeEnum ITEM : QualitySampleTypeEnum.values()) {
            cache.put(ITEM.sampleCode, ITEM.sampleTypeCode);
        }
    }
    private Integer sampleTypeCode;

    private String sampleCode;

    QualitySampleTypeEnum(String sampleType, Integer sampleTypeCode,String sampleCode) {
        this.sampleType = sampleType;
        this.sampleTypeCode = sampleTypeCode;
        this.sampleCode = sampleCode;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public Integer get() {
        return sampleTypeCode;
    }

    public String getSampleCode(){
        return sampleCode;
    }

    public void setCode(Integer sampleTypeCode) {
        this.sampleTypeCode = sampleTypeCode;
    }

    public static QualitySampleTypeEnum getByType(Integer type){
        for(QualitySampleTypeEnum qualitySampleTypeEnum:QualitySampleTypeEnum.values()){
            if(type.equals(qualitySampleTypeEnum.get())) return qualitySampleTypeEnum;
        }
        return null;
    }

    public static Integer getTypeCodeByCode(String code){
        return cache.getOrDefault(code, null);
    }
}
