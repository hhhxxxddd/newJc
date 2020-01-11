package com.jc.api.utils.materialcode;


/**
 * @author：XudongHu
 * @className:DecoderV1
 * @description: 解码器V1
 * @date:17:03 2019/2/21
 * @Modifer:
 */
public class DecoderV1 extends AbstractMaterialDecoder {
    // "正确格式为:MC/批次号+车间号+类型+名称+袋号+厂商代号+重量" +
    // "如:MC/BN180808-WS001-RAW-Fe-0001-QDBX-60KG");
    @Override
    public MaterialCode.DecodedMaterialCode decode(String code) {
        MaterialCode.DecodedMaterialCode decodedMaterialCode = MaterialCode.DecodedMaterialCode.newDecodeMaterialCode();
        String[] content = code.split("/")[1].split("-");
        if (content.length != 7) {
            //默认批号
            decodedMaterialCode.setBatch("BN000000");
            //默认车间号
            decodedMaterialCode.setMaterialWorkShopCode("0");
            //默认物料类型
            decodedMaterialCode.setMaterialTypeStr("RAW");
            //默认物料名称
            decodedMaterialCode.setMaterialNameCode(code.replace("-","?"));
            //默认袋号
            decodedMaterialCode.setBagNo("1");
            //默认供应商
            decodedMaterialCode.setMaterialSupplierNameCode("XinSong");
            //默认重量
            decodedMaterialCode.setWeight("0KG");
        } else {
            decodedMaterialCode.setBatch(content[0]);
            decodedMaterialCode.setMaterialWorkShopCode(content[1]);
            decodedMaterialCode.setMaterialTypeStr((content[2]));
            decodedMaterialCode.setMaterialNameCode(content[3]);
            decodedMaterialCode.setBagNo(content[4]);
            decodedMaterialCode.setMaterialSupplierNameCode(content[5]);
            decodedMaterialCode.setWeight(content[6]);
        }
        return decodedMaterialCode;
    }

}
