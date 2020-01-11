package com.jc.api.entity.dto;

/**
 * @author：XudongHu
 * @className:RedTableDTO
 * @description: 红单通知DTO
 * 1.发现有损失====>2.发送损失单给仓库===>3.仓库确认==>?是否要返回信息
 * @date:14:14 2019/3/12
 * @Modifer:
 */
@Deprecated
public class RedTableDTO {
    //物料编码
    private String GOODS_CODE;
    //损失数量
    private String GOODS_QUANTITY;
    //创建人
    private String OPERATOR;

    public String getGOODS_CODE() {
        return GOODS_CODE;
    }

    public RedTableDTO setGOODS_CODE(String GOODS_CODE) {
        this.GOODS_CODE = GOODS_CODE;
        return this;
    }

    public String getGOODS_QUANTITY() {
        return GOODS_QUANTITY;
    }

    public RedTableDTO setGOODS_QUANTITY(String GOODS_QUANTITY) {
        this.GOODS_QUANTITY = GOODS_QUANTITY;
        return this;
    }

    public String getOPERATOR() {
        return OPERATOR;
    }

    public RedTableDTO setOPERATOR(String OPERATOR) {
        this.OPERATOR = OPERATOR;
        return this;
    }

    @Override
    public String toString() {
        return "RedTableDTO{" +
                "GOODS_CODE='" + GOODS_CODE + '\'' +
                ", GOODS_QUANTITY='" + GOODS_QUANTITY + '\'' +
                ", OPERATOR='" + OPERATOR + '\'' +
                '}';
    }
}
