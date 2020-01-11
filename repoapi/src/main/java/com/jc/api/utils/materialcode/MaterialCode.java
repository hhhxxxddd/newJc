package com.jc.api.utils.materialcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author：XudongHu
 * @className:ItemCode
 * @description: 物料编码类
 * @date:16:54 2019/2/21
 * @Modifer:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MaterialCode {
    private String code;

    public static MaterialCode newMaterialCode(String code) {
        return new MaterialCode(code);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class DecodedMaterialCode {
        private String batch;//批次
        private String materialNameCode;//物料名称代号
        private String materialSupplierNameCode;//厂商代号
        private String materialWorkShopCode;//车间代号
        private String materialTypeStr;//物料类型
        private String bagNo;//袋号
        private String weight;//重量

        public static DecodedMaterialCode newDecodeMaterialCode(){
            return new DecodedMaterialCode();
        }

    }

    //编码器
    public interface Encoder {
        MaterialCode encode(DecodedMaterialCode decodedCode);
    }

    //解码器
    public interface Decoder {
        DecodedMaterialCode decode(MaterialCode code);
        DecodedMaterialCode decode(String code);
    }
}
