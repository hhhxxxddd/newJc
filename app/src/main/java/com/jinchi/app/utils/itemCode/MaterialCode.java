package com.jinchi.app.utils.itemCode;

/**
 * @author：XudongHu
 * @className:ItemCode
 * @description: 物料编码类
 * @date:16:54 2019/2/21
 * @Modifer:
 */
public class MaterialCode {
    private String code;

    public MaterialCode() {
    }

    private MaterialCode(String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static MaterialCode newMaterialCode(String code) {
        return new MaterialCode(code);
    }

    public static class DecodedMaterialCode {
        private String batch;//批次
        private String workshop;//车间
        private String materialType;//物料类型
        private String materialName;//物料名字
        private String bagNumber;//袋号
        private String manufacturer;//厂商
        private String weight;//重量

        public DecodedMaterialCode() {
        }

        public DecodedMaterialCode(String batch, String workshop, String materialType, String materialName, String bagNumber, String manufacturer, String weight) {
            this();
            this.batch = batch;
            this.workshop = workshop;
            this.materialType = materialType;
            this.materialName = materialName;
            this.bagNumber = bagNumber;
            this.manufacturer = manufacturer;
            this.weight = weight;
        }

        public static DecodedMaterialCode newDecodedMaterialCode() {
            return new DecodedMaterialCode();
        }

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
        }

        public String getWorkshop() {
            return workshop;
        }

        public void setWorkshop(String workshop) {
            this.workshop = workshop;
        }

        public String getMaterialType() {
            return materialType;
        }

        public void setMaterialType(String materialType) {
            this.materialType = materialType;
        }

        public String getMaterialName() {
            return materialName;
        }

        public void setMaterialName(String materialName) {
            this.materialName = materialName;
        }

        public String getBagNumber() {
            return bagNumber;
        }

        public void setBagNumber(String bagNumber) {
            this.bagNumber = bagNumber;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
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
