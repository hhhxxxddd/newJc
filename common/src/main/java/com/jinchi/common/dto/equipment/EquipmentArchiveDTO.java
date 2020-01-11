package com.jinchi.common.dto.equipment;

import com.jinchi.common.domain.EquipmentArchiveRecord;
import com.jinchi.common.domain.EquipmentBaseInstrument;
import com.jinchi.common.domain.EquipmentBaseManufacturer;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author：XudongHu
 * @className:EquipmentArchiveDTO
 * @description:
 * @date:19:16 2019/1/11
 * @Modifer:
 */
public class EquipmentArchiveDTO {
    @ApiModelProperty("设备档案本体")
    private EquipmentArchiveRecord equipmentArchiveRecord;

    @ApiModelProperty("设备实体")
    private EquipmentBaseInstrument baseInstrument;

    @ApiModelProperty("供应商")
    private EquipmentBaseManufacturer supplyManufacturer;

    @ApiModelProperty("维修商")
    private EquipmentBaseManufacturer repairManufacturer;

    public EquipmentArchiveRecord getEquipmentArchiveRecord() {
        return equipmentArchiveRecord;
    }

    public EquipmentArchiveDTO setEquipmentArchiveRecord(EquipmentArchiveRecord equipmentArchiveRecord) {
        this.equipmentArchiveRecord = equipmentArchiveRecord;
        return this;
    }

    public EquipmentBaseInstrument getBaseInstrument() {
        return baseInstrument;
    }

    public EquipmentArchiveDTO setBaseInstrument(EquipmentBaseInstrument baseInstrument) {
        this.baseInstrument = baseInstrument;
        return this;
    }

    public EquipmentBaseManufacturer getSupplyManufacturer() {
        return supplyManufacturer;
    }

    public EquipmentArchiveDTO setSupplyManufacturer(EquipmentBaseManufacturer supplyManufacturer) {
        this.supplyManufacturer = supplyManufacturer;
        return this;
    }

    public EquipmentBaseManufacturer getRepairManufacturer() {
        return repairManufacturer;
    }

    public EquipmentArchiveDTO setRepairManufacturer(EquipmentBaseManufacturer repairManufacturer) {
        this.repairManufacturer = repairManufacturer;
        return this;
    }

    @Override
    public String toString() {
        return "EquipmentArchiveDTO{" +
                "equipmentArchiveRecord=" + equipmentArchiveRecord +
                ", baseInstrument=" + baseInstrument +
                ", supplyManufacturer=" + supplyManufacturer +
                ", repairManufacturer=" + repairManufacturer +
                '}';
    }
}
