package com.jc.api.utils.materialcode;

import java.util.List;

/**
 * @author：XudongHu
 * @className:EncoderV1
 * @description: 编码器v1
 * @date:15:23 2019/3/16
 * @Modifer:
 */
public class EncoderV1 extends AbstractMaterialEncoder {

    //"正确格式为:MC/批次号+车间号+类型+名称+袋号+厂商代号+重量" +
    //"如:MC/BN180808-WS001-RAW(TS)-Fe-1-QDBX-60KG";
    @Override
    public MaterialCode encode(MaterialCode.DecodedMaterialCode decodedCode) {
        //==>批号
        String batch = decodedCode.getBatch();
        //==>车间代号
        String materialWorkShopCode = decodedCode.getMaterialWorkShopCode();
        //==>物料厂商代号
        String materialSupplierNameCode = decodedCode.getMaterialSupplierNameCode();
        //==>物料名称代号
        String materialNameCode = decodedCode.getMaterialNameCode();
        //==>物料类型
        String materialTypeList = decodedCode.getMaterialTypeStr();
        //==>袋号
        String bagNo = decodedCode.getBagNo();
        //==>重量
        String weight = decodedCode.getWeight();
        String code = "MC/" + batch + "-" + materialWorkShopCode + "-" + materialTypeList + "-" + materialNameCode + "-" + bagNo + "-" + materialSupplierNameCode + "-" + weight+"KG";
        return MaterialCode.newMaterialCode(code);
    }

    //编码物料类型
    private  static String encodeType(List<String> typeList) {
        StringBuilder typeStrBuilder = new StringBuilder();
        typeStrBuilder.append(typeList.get(0));
        for (int i = 1; i < typeList.size(); i++) {
            typeStrBuilder.append("(").append(typeList.get(i));
        }
        for (int i = 0; i < typeList.size()-1 ; i++) {
            typeStrBuilder.append(")");
        }
        return typeStrBuilder.toString();
    }
}
