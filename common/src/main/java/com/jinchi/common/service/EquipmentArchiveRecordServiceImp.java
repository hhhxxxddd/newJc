package com.jinchi.common.service;

import com.jinchi.common.constant.AddressEnum;
import com.jinchi.common.constant.EquipmentManufacturerEnum;
import com.jinchi.common.domain.EquipmentArchiveRecord;
import com.jinchi.common.domain.EquipmentBaseInstrument;
import com.jinchi.common.domain.EquipmentBaseManufacturer;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.equipment.EquipmentArchiveDTO;
import com.jinchi.common.mapper.EquipmentArchiveRecordMapper;
import com.jinchi.common.mapper.EquipmentBaseInstrumentMapper;
import com.jinchi.common.mapper.EquipmentBaseManufacturerMapper;
import com.jinchi.common.utils.UploadUtil;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author：XudongHu
 * @className:EquipmentArchiveRecordServiceImp
 * @description: 设备档案
 * @date:16:14 2019/1/11
 * @Modifer:
 */
@Service
public class EquipmentArchiveRecordServiceImp implements EquipmentArchiveRecordService {
    @Autowired
    private EquipmentBaseManufacturerMapper equipmentBaseManufacturerMapper;
    @Autowired
    private EquipmentBaseInstrumentMapper equipmentBaseInstrumentMapper;
    @Autowired
    private EquipmentArchiveRecordMapper equipmentArchiveRecordMapper;

    /**
     * 新增
     *
     * @param equipmentArchiveRecord
     * @return
     */
    @Override
    @Transactional
    public EquipmentArchiveRecord add(EquipmentArchiveRecord equipmentArchiveRecord, MultipartFile file) throws IOException {
        EquipmentBaseInstrument baseInstrument = equipmentBaseInstrumentMapper.byId(equipmentArchiveRecord.getInstrumentId());
        EquipmentBaseManufacturer supply = equipmentBaseManufacturerMapper.byId(equipmentArchiveRecord.getSupplyManufacturerId());
        EquipmentBaseManufacturer repair = equipmentBaseManufacturerMapper.byId(equipmentArchiveRecord.getRepairManufacturerId());
        Assert.isTrue(null != baseInstrument &&
                null != supply &&
                supply.getType().equals(EquipmentManufacturerEnum.SUPLLY_MANU.getCode()) &&
                null != repair &&
                repair.getType().equals(EquipmentManufacturerEnum.REPAIR_MANU.getCode()), "设备或者厂商不存在");

        //档案名称的同名验证
        String recordName = equipmentArchiveRecord.getName();
        Assert.isNull(equipmentArchiveRecordMapper.byName(recordName, null), "新增失败,存在同名档案");
        //仅能上传pdf文件
        String fileCode = UploadUtil.upload(AddressEnum.getCurrentPath(AddressEnum.EQUIPMENT_ARCHIVE_ABS_ADDRESS.getCode()), file, 10,".pdf");
        //设置地址
        equipmentArchiveRecord.setManualName(fileCode);
        equipmentArchiveRecordMapper.insert(equipmentArchiveRecord);



        return equipmentArchiveRecord;
    }

    /**
     * 更新
     *
     * @param equipmentArchiveRecord
     * @return
     */
    @Override
    @Transactional
    public EquipmentArchiveRecord update(EquipmentArchiveRecord equipmentArchiveRecord, MultipartFile file) throws IOException {
        Integer id = equipmentArchiveRecord.getId();
        EquipmentArchiveRecord oldValue = equipmentArchiveRecordMapper.byId(id);
        Assert.notNull(oldValue, "更新失败,不存在该设备档案");
        EquipmentBaseInstrument baseInstrument = equipmentBaseInstrumentMapper.byId(equipmentArchiveRecord.getInstrumentId());
        EquipmentBaseManufacturer supply = equipmentBaseManufacturerMapper.byId(equipmentArchiveRecord.getSupplyManufacturerId());
        EquipmentBaseManufacturer repair = equipmentBaseManufacturerMapper.byId(equipmentArchiveRecord.getRepairManufacturerId());
        Assert.isTrue(null != baseInstrument &&
                null != supply &&
                supply.getType().equals(EquipmentManufacturerEnum.SUPLLY_MANU.getCode()) &&
                null != repair &&
                repair.getType().equals(EquipmentManufacturerEnum.REPAIR_MANU.getCode()), "设备或者厂商不存在");

        String name = equipmentArchiveRecord.getName();
        //同名验证
        equipmentArchiveRecordMapper.byName(name, id);

        Assert.isNull(equipmentArchiveRecordMapper.byName(name, id), "更新失败,存在相同的档案");

        String manualName=file==null?oldValue.getManualName():UploadUtil.upload(AddressEnum.getCurrentPath(AddressEnum.EQUIPMENT_ARCHIVE_ABS_ADDRESS.getCode()), file, 10,".pdf");

        equipmentArchiveRecord.setManualName(manualName);

        equipmentArchiveRecordMapper.update(equipmentArchiveRecord);

        return equipmentArchiveRecord;
    }

    /**
     * 详情
     * @param id 主键
     * @return
     */
    @Override
    public EquipmentArchiveDTO byId(Integer id) {
        EquipmentArchiveRecord equipmentArchiveRecord = equipmentArchiveRecordMapper.byId(id);
        if(null==equipmentArchiveRecord) return null;
        return convert2DTO(equipmentArchiveRecord);
    }

    @Override
    public String manual(Integer id) {
        EquipmentArchiveRecord equipmentArchiveRecord = equipmentArchiveRecordMapper.byId(id);
        Assert.isTrue(null!=equipmentArchiveRecord&& !StringUtils.isEmpty(equipmentArchiveRecord.getManualName()),
                "查看失败,手册不存在");
        return equipmentArchiveRecord.getManualName();
    }


    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<EquipmentArchiveDTO> all(PageBean pageBean, String manualName) {
        List<EquipmentArchiveRecord> equipmentArchiveRecords = equipmentArchiveRecordMapper.all(pageBean, manualName);
        if (null == equipmentArchiveRecords || equipmentArchiveRecords.isEmpty()) return null;

        List<EquipmentArchiveDTO> dtos = new ArrayList<>();

        equipmentArchiveRecords.stream().forEach(e -> dtos.add(convert2DTO(e)));

        return dtos;
    }

    /**
     * 分页
     *
     * @param pageBean 分页
     * @return
     */
    @Override
    public PageBean pages(PageBean pageBean, String manualName) {
        List<EquipmentArchiveDTO> dtos = all(pageBean, manualName);

        pageBean.setList(dtos);
        pageBean.setTotal(equipmentArchiveRecordMapper.countSum(manualName));
        return pageBean;
    }


    /**
     * 根据id删除
     *
     * @param id 主键
     */
    @Override
    public void deleteById(Integer id) {
        equipmentArchiveRecordMapper.deleteById(id);
        // TODO 文件也要删除
    }

    /**
     * 删除所有
     *
     * @param ids
     */
    @Override
    public void batchDelete(List<Integer> ids) {
        equipmentArchiveRecordMapper.batchDelete(ids);
        // TODO 文件也要删除
    }

    /**
     * 转换成DTO
     *
     * @param equipmentArchiveRecord 设备档案实体
     * @Return EquipmentArchiveDTO 设备档案DTO
     */
    public EquipmentArchiveDTO convert2DTO(EquipmentArchiveRecord equipmentArchiveRecord) {
        EquipmentArchiveDTO archiveDTO = new EquipmentArchiveDTO();

        //设备本体
        EquipmentBaseInstrument baseInstrument = equipmentBaseInstrumentMapper.byId(equipmentArchiveRecord.getInstrumentId());
        //供应商
        EquipmentBaseManufacturer supply = equipmentBaseManufacturerMapper.byId(equipmentArchiveRecord.getSupplyManufacturerId());
        //维修商
        EquipmentBaseManufacturer repair = equipmentBaseManufacturerMapper.byId(equipmentArchiveRecord.getRepairManufacturerId());

        archiveDTO
                .setBaseInstrument(baseInstrument)
                .setEquipmentArchiveRecord(equipmentArchiveRecord)
                .setSupplyManufacturer(supply)
                .setRepairManufacturer(repair);

        return archiveDTO;
    }

}
