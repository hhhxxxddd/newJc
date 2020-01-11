package com.jinchi.app.utils.itemCode;

import io.jsonwebtoken.lang.Assert;

/**
 * @author：XudongHu
 * @className:DecoderV1
 * @description:
 * @date:17:03 2019/2/21
 * @Modifer:
 */
public class DecoderV1 extends AbstractMaterialDecoder {
    @Override
    public MaterialCode.DecodedMaterialCode decode(String code) {
        String[] content = code.split("/")[1].split("-");
        Integer length = content.length;

        //成品的厂商 统一为CYLK
        Assert.isTrue(length==7,"物料编码无法识别," +
                "正确格式为:MC/批次号+车间号+类型+名称+袋号+厂商代号+重量" +
                "如:MC/BN180808-WS001-RAW-Fe-0001-QDBX-60KG");
        MaterialCode.DecodedMaterialCode decodedMaterialCode = MaterialCode.DecodedMaterialCode.newDecodedMaterialCode();

        decodedMaterialCode.setBatch(content[0]);
        decodedMaterialCode.setWorkshop(content[1]);
        decodedMaterialCode.setMaterialType(content[2]);
        decodedMaterialCode.setMaterialName(content[3]);
        decodedMaterialCode.setBagNumber(content[4]);
        decodedMaterialCode.setManufacturer(content[5]);
        decodedMaterialCode.setWeight(content[6]);
        return decodedMaterialCode;
    }
}
