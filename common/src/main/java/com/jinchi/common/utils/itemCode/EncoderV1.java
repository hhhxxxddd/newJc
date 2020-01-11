package com.jinchi.common.utils.itemCode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：XudongHu
 * @className:EncoderV1
 * @description:
 * @date:15:23 2019/3/16
 * @Modifer:
 */
public class EncoderV1 extends AbstractMaterialEncoder {
    private AtomicInteger bagNumber = new AtomicInteger(0);

    @Override
    public MaterialCode encode(MaterialCode.DecodedMaterialCode decodedCode) {
        String weight = decodedCode.getWeight();

        String manufacturer = decodedCode.getManufacturer();

        String materialType = decodedCode.getMaterialType();//只能是Raw Med Pro

        String batch = decodedCode.getBatch();
        String bagNumber = decodedCode.getBagNumber();
        Integer bagNo = this.bagNumber.incrementAndGet();
        String materialName = decodedCode.getMaterialName();
        String workshop = decodedCode.getWorkshop();

        return MaterialCode.newMaterialCode("s");
    }
}
