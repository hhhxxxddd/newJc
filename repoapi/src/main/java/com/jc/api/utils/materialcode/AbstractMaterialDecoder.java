package com.jc.api.utils.materialcode;

/**
 * @author：XudongHu
 * @className:AbstractMaterialDecoder
 * @description:   抽象物料解码器
 * @date:14:32 2019/2/25
 * @Modifer:
 */
public abstract class AbstractMaterialDecoder implements MaterialCode.Decoder {
    @Override
    public MaterialCode.DecodedMaterialCode decode(MaterialCode code) {
        return decode(code.getCode());
    }

    @Override
    public MaterialCode.DecodedMaterialCode decode(String code){return decode(code);}
}
