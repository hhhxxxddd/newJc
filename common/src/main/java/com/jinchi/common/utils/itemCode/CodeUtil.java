package com.jinchi.common.utils.itemCode;

/**
 * @author：XudongHu
 * @className:CodeUtil
 * @description:
 * @date:16:58 2019/2/21
 * @Modifer:
 */
public class CodeUtil {
    public static MaterialCode encode(MaterialCode.Encoder encoder, MaterialCode.DecodedMaterialCode decodedMaterialCode){
        return encoder.encode(decodedMaterialCode);
    }

    public static MaterialCode.DecodedMaterialCode decode(MaterialCode.Decoder decoder, MaterialCode code){
        return decoder.decode(code);
    }
}
