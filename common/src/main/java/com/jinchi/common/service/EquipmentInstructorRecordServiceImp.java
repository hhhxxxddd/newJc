package com.jinchi.common.service;

import com.jinchi.common.constant.AddressEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.domain.CommonBatchNumber;
import com.jinchi.common.domain.EquipmentCheckPointRecord;
import com.jinchi.common.domain.EquipmentInstructorRecord;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.equipment.EquipmentInstructorDTO;
import com.jinchi.common.mapper.AuthUserMapper;
import com.jinchi.common.mapper.CommonBatchNumberMapper;
import com.jinchi.common.mapper.EquipmentCheckPointRecordMapper;
import com.jinchi.common.mapper.EquipmentInstructorRecordMapper;
import com.jinchi.common.model.factorypattern.CommonBatchFactory;
import com.jinchi.common.utils.UploadUtil;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:EquipmentInstructorRecordServiceImp
 * @description: 设备指导实现类
 * @date:23:22 2019/3/2
 */
@Service
public class EquipmentInstructorRecordServiceImp implements EquipmentInstructorRecordService {
    @Autowired
    private EquipmentInstructorRecordMapper equipmentInstructorRecordMapper;
    @Autowired
    private EquipmentCheckPointRecordMapper equipmentCheckPointRecordMapper;
    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    private AuthUserMapper authUserMapper;

    private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");

    /**
     * 上传图片并回显
     *
     * @return
     */
    @Override
    public String uploadPic(MultipartFile file) throws IOException {
        //图片最多3MB
        return UploadUtil.uploadPic(AddressEnum.getCurrentPath(AddressEnum.EQUIPMENT_INSTRUCTOR_ABS_ADDRESS.getCode()), file, 20);
    }

    /**
     * 取消上传
     *
     * @return
     */
    @Override
    public String cancelUpload(List<String> fileNames) throws IOException {
        for (String fileName : fileNames) {
            UploadUtil.deleteFile(AddressEnum.getCurrentPath(AddressEnum.EQUIPMENT_INSTRUCTOR_ABS_ADDRESS.getCode()), fileName);
        }
        return "图片已取消上传";
    }

    /**
     * 新增/迭代
     *
     * @param equipmentInstructorDTO
     * @return
     */
    @Override
    @Transactional
    public EquipmentInstructorDTO insert(EquipmentInstructorDTO equipmentInstructorDTO) {
        Integer id = equipmentInstructorDTO.getInstructorRecord().getId();
        //有id是迭代没有id是新增
        EquipmentInstructorRecord oldValue = equipmentInstructorRecordMapper.byId(id);
        Assert.notNull(equipmentInstructorDTO.getCreatePersonId(),"NullPointerException createPersonId:"+id);

        if (null != oldValue) {
            oldValue.setObsolete(0);
            equipmentInstructorRecordMapper.update(oldValue);
        }

        EquipmentInstructorRecord instructorRecord = equipmentInstructorDTO.getInstructorRecord();
        //新增批号
        CommonBatchNumber commonBatchNumber = CommonBatchFactory
                .initialize(BatchTypeEnum.EQUIPMENT_INSTRUCTOR.typeCode())
                .setCreatePersonId(equipmentInstructorDTO.getCreatePersonId());
        commonBatchNumberMapper.insert(commonBatchNumber);

        //新增指导
        instructorRecord
                .setBatchNumberId(commonBatchNumber.getId())
                .setObsolete(1);
        equipmentInstructorRecordMapper.insert(instructorRecord);
        //新增拍照点
        List<EquipmentCheckPointRecord> pointRecordList = equipmentInstructorDTO.getPointRecordList();
        if (null != pointRecordList) insertContent(pointRecordList, instructorRecord.getId());

        return equipmentInstructorDTO;
    }

    /**
     * 新增指导书内容
     *
     * @param equipmentCheckPointRecords
     * @param id
     */
    @Transactional
    public void insertContent(List<EquipmentCheckPointRecord> equipmentCheckPointRecords, Integer id) {
        if (!equipmentCheckPointRecords.isEmpty()) {
            equipmentCheckPointRecords.forEach(e -> e.setInstructorId(id));
            equipmentCheckPointRecordMapper.batchInsert(equipmentCheckPointRecords);
        }
    }

    /**
     * 更新
     *
     * @param equipmentInstructorDTO
     * @return
     */
    @Override
    @Transactional
    public EquipmentInstructorDTO update(EquipmentInstructorDTO equipmentInstructorDTO) {
        EquipmentInstructorRecord newValue = equipmentInstructorDTO.getInstructorRecord();
        Integer id = newValue.getId();
        //先更新本体
        EquipmentInstructorRecord oldValue = equipmentInstructorRecordMapper.byId(id);
        Assert.notNull(oldValue, "更新失败,此设备指导不存在");
        equipmentInstructorRecordMapper.update(newValue);
        //找出已上传的图片 不删除这些
        List<EquipmentCheckPointRecord> pointRecordList = equipmentInstructorDTO.getPointRecordList();
        List<String> pictures = new ArrayList<>();
        pointRecordList.forEach(e -> pictures.add(e.getCheckPointPicName()));

        deleteContent(id, pictures);
        insertContent(equipmentInstructorDTO.getPointRecordList(), id);

        return equipmentInstructorDTO.setInstructorRecord(oldValue);
    }


    /**
     * 详情
     *
     * @param batchNumberId 主键
     * @return
     */
    @Override
    public Map<Object, Object> byBatchNumberId(Integer batchNumberId) {
        EquipmentInstructorRecord instructorRecord = equipmentInstructorRecordMapper.byBatchNumberId(batchNumberId);
        List<EquipmentCheckPointRecord> checkPointRecords = equipmentCheckPointRecordMapper.byInstructorId(instructorRecord.getId());
        Assert.notNull(instructorRecord, String.format("Id为%d的设备指导不存在", batchNumberId));
        Assert.notEmpty(checkPointRecords, "该设备指导下内容为空");

        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(instructorRecord.getBatchNumberId());

        Map<Object, Object> map = new HashMap<>();

        map.put("id", instructorRecord.getId());
        map.put("batchNumberId", commonBatchNumber.getId());
        map.put("status", commonBatchNumber.getStatus());
        map.put("batchNumber", commonBatchNumber.getBatchNumber());
        map.put("instructorName", instructorRecord.getName());
        map.put("createPerson", authUserMapper.byId(commonBatchNumber.getCreatePersonId()).getName());
        map.put("effectiveDate", SDF.format(commonBatchNumber.getCreateTime()));
        map.put("content", checkPointRecords);

        return map;
    }

    /**
     * 查询所有-分页
     *
     * @param instructorName 指导书名
     * @param pageBean       分页类
     * @return
     */
    @Override
    public PageBean byNameLikeByPage(String instructorName, PageBean pageBean) {
        pageBean.setSortType("DESC");
        pageBean.setSortField("id");
        Integer sum = equipmentInstructorRecordMapper.countSum(instructorName, pageBean);
        if (!(sum > 0)) return null;
        List<EquipmentInstructorRecord> records = equipmentInstructorRecordMapper.byNameLikeByPage(instructorName, pageBean);

        List<Map> listMap = new ArrayList<>();
        for (EquipmentInstructorRecord record : records) {
            Map<Object, Object> map = new HashMap<>();
            CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(record.getBatchNumberId());

            map.put("batchNumberId", commonBatchNumber.getId());
            map.put("batchNumber", commonBatchNumber.getBatchNumber());
            map.put("status", commonBatchNumber.getStatus());
            map.put("instructorName", record.getName());
            map.put("createPerson", authUserMapper.byId(commonBatchNumber.getCreatePersonId()).getName());
            map.put("effectiveDate", SDF.format(commonBatchNumber.getCreateTime()));
            listMap.add(map);
        }
        pageBean.setList(listMap);
        pageBean.setTotal(sum);
        return pageBean;
    }

    /**
     * 根据批号id删除 上传的图片都要删除
     *
     * @param batchNumberId
     * @return
     */
    @Override
    @Transactional
    public String delete(Integer batchNumberId) {
        EquipmentInstructorRecord record = equipmentInstructorRecordMapper.byBatchNumberId(batchNumberId);
        Assert.notNull(record, "删除失败,找不到该指导书");
        equipmentCheckPointRecordMapper.deleteByInstructorId(record.getId());
        Integer subject = equipmentInstructorRecordMapper.delete(record.getId());
        commonBatchNumberMapper.deleteById(batchNumberId);
        if (!(subject > 0)) return "删除失败,没有此设备指导或者数据库错误";
        return String.format("批号id为%d的设备指导书下%d条数据已被删除", batchNumberId, deleteContent(record.getId(), new ArrayList<>()));
    }

    /**
     * 删除指导书中内容
     *
     * @param id
     * @return
     */
    @Transactional
    public Integer deleteContent(Integer id, List<String> listNotRemovePic) {
        List<EquipmentCheckPointRecord> checkPointRecords = equipmentCheckPointRecordMapper.byInstructorId(id);

        List<String> listRemovePic = new ArrayList<>();
        if (null != checkPointRecords && !checkPointRecords.isEmpty()) {
            checkPointRecords.forEach(e -> listRemovePic.add(e.getCheckPointPicName()));
            listRemovePic.removeAll(listNotRemovePic);

            listRemovePic.forEach(p -> {
                UploadUtil.deleteFile(AddressEnum.getCurrentPath(AddressEnum.EQUIPMENT_INSTRUCTOR_ABS_ADDRESS.getCode()), p);
            });
        }
        Integer contentSum = equipmentCheckPointRecordMapper.deleteByInstructorId(id);

        return contentSum;
    }

    /**
     * 批量删除
     *
     * @param ids 主键数组
     * @return
     */
    @Override
    @Transactional
    public String batchDelete(List<Integer> ids) {
        return null;
    }

}
