package com.jc.api.utils.materialcode;

/**
 * @author：XudongHu
 * @className:AbstractMaterialEncoder
 * @description: 抽象编码器
 * @date:15:20 2019/3/16
 * @Modifer:
 */
public class AbstractMaterialEncoder implements MaterialCode.Encoder{
    @Override
    public MaterialCode encode(MaterialCode.DecodedMaterialCode decodedCode) {
        return  encode(decodedCode);
    }
}
