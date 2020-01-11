package com.jc.api.entity.dto;

/**
 * @author：XudongHu
 * @className:StorageCheckDTO
 * @description: 库存检查DTO
 * 1.JC仓库的记录需要盘库===>2.发送某种物料的编码给仓库===>3.仓库查询后返回仓库的真实值
 * @date:14:11 2019/3/12
 * @Modifer:
 */
@Deprecated
public class StorageCheckDTO {
    //物料名称
    private String GOODS_NAME;
    //物料批次号
    private String GOODS_BATCH_NO;
    //重量
    private String WEIGHT;
    //操作者
    private String OPERATOR;

    public String getGOODS_NAME() {
        return GOODS_NAME;
    }

    public StorageCheckDTO setGOODS_NAME(String GOODS_NAME) {
        this.GOODS_NAME = GOODS_NAME;
        return this;
    }

    public String getGOODS_BATCH_NO() {
        return GOODS_BATCH_NO;
    }

    public StorageCheckDTO setGOODS_BATCH_NO(String GOODS_BATCH_NO) {
        this.GOODS_BATCH_NO = GOODS_BATCH_NO;
        return this;
    }

    public String getWEIGHT() {
        return WEIGHT;
    }

    public StorageCheckDTO setWEIGHT(String WEIGHT) {
        this.WEIGHT = WEIGHT;
        return this;
    }

    public String getOPERATOR() {
        return OPERATOR;
    }

    public StorageCheckDTO setOPERATOR(String OPERATOR) {
        this.OPERATOR = OPERATOR;
        return this;
    }

    @Override
    public String toString() {
        return "StorageCheckDTO{" +
                "GOODS_NAME='" + GOODS_NAME + '\'' +
                ", GOODS_BATCH_NO='" + GOODS_BATCH_NO + '\'' +
                ", WEIGHT='" + WEIGHT + '\'' +
                ", OPERATOR='" + OPERATOR + '\'' +
                '}';
    }
}
