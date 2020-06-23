package com.jinchi.common.service;

import com.jinchi.common.constant.BatchStatusEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.constant.QualitySampleTypeEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.KeyforProRawStandard;
import com.jinchi.common.dto.RawStandardDTO;
import com.jinchi.common.dto.technique.TechniqueBaseRawManufacturerDTO;
import com.jinchi.common.dto.technique.TechniqueRawStandardRecordDTO;
import com.jinchi.common.dto.technique.TechniqueRawTestItemDTO;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.NumberGenerator;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author：XudongHu
 * @className:TechniqueRawStandardRecordServiceImp
 * @description: 原材料标准记录表
 * @date:23:26 2018/12/27
 */
@Service
public class TechniqueRawStandardRecordServiceImp implements TechniqueRawStandardRecordService {
    public static final Integer BATCH_TECH_RAW_STANDARD = BatchTypeEnum.TECH_RAW_STANDARD.typeCode();
    @Autowired
    private TechniqueBaseRawMaterialMapper techniqueBaseRawMaterialMapper;
    @Autowired
    private TechniqueBaseRawManufacturerMapper techniqueBaseRawManufacturerMapper;
    @Autowired
    private TechniqueRawStandardRecordMapper techniqueRawStandardRecordMapper;
    @Autowired
    private TechniqueRawTestItemStandardMapper techniqueRawTestItemStandardMapper;
    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private TestItemMapper testItemMapper;
    @Autowired
    private TechniqueRawItemRecordMapper techniqueRawItemRecordMapper;
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    TechniqueBaseRawBindManufacturerMapper rawBindManufacturerMapper;

    /**
     * 查询所有原材料
     *
     * @param name 名称模糊
     * @return
     */
    @Override
    public List<TechniqueBaseRawMaterial> baseRawMaterialNameLike(String name) {
        return techniqueBaseRawMaterialMapper.getByNameLike(name);
    }

    /**
     * 新增一个原材料
     *
     * @param serialNumberId
     * @param testItemIds 主成分  检测项目
     * @return
     */
    @Override
    @Transactional
    public TechniqueBaseRawMaterial addNewRawMaterial(Integer serialNumberId, List<Integer> testItemIds) {

        RepoBaseSerialNumber byId = repoBaseSerialNumberMapper.findById(serialNumberId);
        String materialName = byId.getMaterialName();

        TechniqueBaseRawMaterial oldValue = techniqueBaseRawMaterialMapper.getByName(materialName);

        Assert.isNull(oldValue, "新增失败,存在名称相同的原材料");

        Assert.notEmpty(testItemIds, "检测项目不能为空");

        TechniqueBaseRawMaterial newValue = new TechniqueBaseRawMaterial();
        newValue.setName(materialName);

        techniqueBaseRawMaterialMapper.insert(newValue);

        Integer id = newValue.getId();

        List<TechniqueRawItemRecord> techniqueRawItemRecords = new ArrayList<>();

        for (int i = 0; i < testItemIds.size(); i++) {
            TechniqueRawItemRecord techniqueRawItemRecord = new TechniqueRawItemRecord();

            TestItem testItem = testItemMapper.find(testItemIds.get(i));

            Assert.notNull(testItem, "该检测项目不存在");

            techniqueRawItemRecord
                    .setRawMaterialId(id)
                    .setTestItemId(testItem.getId());

            techniqueRawItemRecords.add(techniqueRawItemRecord);

        }

        techniqueRawItemRecordMapper.batchInsert(techniqueRawItemRecords);


        return newValue;
    }

    /**
     * 查询所有原材料厂家
     *
     * @param name 名称模糊
     * @return
     */
    @Override
    public List<TechniqueBaseRawManufacturer> baseRawManufacturerNameLike(String name, Integer matreialId) {
        return techniqueBaseRawManufacturerMapper.getByNameLike(name, matreialId);
    }


    /**
     * 新增一个原材料厂家
     *
     * @param newValue 原材料厂家实体
     * @return
     */
    @Override
    public TechniqueBaseRawManufacturer addNewRawManufacturer(TechniqueBaseRawManufacturerDTO newValue) {
        String manufacturer = newValue.getTechniqueBaseRawManufacturer().getName();
        TechniqueBaseRawManufacturer oldValue = techniqueBaseRawManufacturerMapper.getByName(manufacturer);
        TechniqueBaseRawManufacturer oldValueByCode = techniqueBaseRawManufacturerMapper.byCode(newValue.getTechniqueBaseRawManufacturer().getCode());
        Assert.isTrue(null == oldValue && null == oldValueByCode, "新增失败,存在名称或代号相同的原材料");
        techniqueBaseRawManufacturerMapper.insert(newValue.getTechniqueBaseRawManufacturer());
        int manufacturerId = newValue.getTechniqueBaseRawManufacturer().getId();

        //生产厂家与原材料绑定
        TechniqueBaseRawBindManufacturer rawBindManufacturer = new TechniqueBaseRawBindManufacturer();
        rawBindManufacturer.setManufacturerId(manufacturerId);
        rawBindManufacturer.setRawId(newValue.getRawMaterialId());
        rawBindManufacturerMapper.insertSelective(rawBindManufacturer);

        return newValue.getTechniqueBaseRawManufacturer();
    }

    @Override
    public int deleteRawExtra(Integer materialId) {

        TechniqueBaseRawBindManufacturerExample example = new TechniqueBaseRawBindManufacturerExample();
        example.createCriteria().andRawIdEqualTo(materialId);
        List<TechniqueBaseRawBindManufacturer> list = rawBindManufacturerMapper.selectByExample(example);
        if (list.size() > 0) {
            return -1;
        }

        //将对应绑定的主成分记录删除
        techniqueRawItemRecordMapper.deleteByMaterialId(materialId);

        //删除对应的原材料
        techniqueBaseRawMaterialMapper.deleteById(materialId);
        return 0;
    }

    @Override
    public TechniqueBaseRawMaterial updateRaw(TechniqueBaseRawMaterial rawMaterial) {
        techniqueBaseRawMaterialMapper.update(rawMaterial);
        return rawMaterial;
    }

    @Override
    public int deleteManufacturer(Integer manufacturerId) {
        List<TechniqueRawStandardRecord> list = techniqueRawStandardRecordMapper.byFactoryId(manufacturerId);
        if (list.size() > 0) {
            return -1;
        }

        //删除与原材料的绑定记录
        TechniqueBaseRawBindManufacturerExample example = new TechniqueBaseRawBindManufacturerExample();
        example.createCriteria().andManufacturerIdEqualTo(manufacturerId);
        rawBindManufacturerMapper.deleteByExample(example);

        //删除对应的生产厂家
        techniqueBaseRawManufacturerMapper.deleteById(manufacturerId);

        return 0;
    }

    @Override
    public TechniqueBaseRawManufacturer editManufacturer(TechniqueBaseRawManufacturer techniqueBaseRawManufacturer) {
        techniqueBaseRawManufacturerMapper.updateName(techniqueBaseRawManufacturer);
        return techniqueBaseRawManufacturer;
    }

    @Override
    public List<TechniqueBaseRawManufacturer> baseRawManufacturerById(Integer rawMaterialId) {

        TechniqueBaseRawBindManufacturerExample example = new TechniqueBaseRawBindManufacturerExample();
        example.createCriteria().andRawIdEqualTo(rawMaterialId);
        List<TechniqueBaseRawBindManufacturer> rawBindManufacturers = rawBindManufacturerMapper.selectByExample(example);

        List<TechniqueBaseRawManufacturer> manufacturers = new ArrayList<>();

        for (int i = 0; i < rawBindManufacturers.size(); i++) {
            manufacturers.add(techniqueBaseRawManufacturerMapper.getById(rawBindManufacturers.get(i).getManufacturerId()));
        }
        return manufacturers;
    }


    //以上都是基本表======================================================================================


    /**
     * 新增/迭代 原材料标准
     *
     * @param commonBatchNumberDTO 公共批号DTO
     * @return
     */
    @Override
    @Transactional
    public CommonBatchNumberDTO addRawStandard(CommonBatchNumberDTO<TechniqueRawStandardRecordDTO> commonBatchNumberDTO) {
        //==GET 批号
        CommonBatchNumber commonBatchNumber = commonBatchNumberDTO.getCommonBatchNumber();

        Integer createPersonId = commonBatchNumber.getCreatePersonId();

        AuthUserDTO userDTO = authUserMapper.byId(createPersonId);

        Assert.notNull(userDTO, "新增失败,创建人不存在");

        Integer batchNumberId = commonBatchNumber.getId();
        //如果id为空 是新增 否则 是迭代
        if (null != batchNumberId) {
            CommonBatchNumber oldValue = commonBatchNumberMapper.byId(batchNumberId);

            Assert.isTrue(null != oldValue && BATCH_TECH_RAW_STANDARD.equals(oldValue.getDataType()), "迭代失败,不存在此原材料标准");
        }

        commonBatchNumber
                .setDataType(BATCH_TECH_RAW_STANDARD)
                .setStatus(BatchStatusEnum.SAVE.status())
                .setDescription(BatchTypeEnum.TECH_RAW_STANDARD.description())
                .setCreateTime(new Date())
                .setBatchNumber(NumberGenerator.batchNumberGenerator(BATCH_TECH_RAW_STANDARD));
        commonBatchNumberMapper.insert(commonBatchNumber);


        //==GET详情
        TechniqueRawStandardRecordDTO standardRecord = commonBatchNumberDTO.getDetails();

        //==GET标准表
        TechniqueRawStandardRecord rawStandardRecord = standardRecord.getTechniqueRawStandardRecord();

        //======VERIFY 标准表数据
        TechniqueBaseRawManufacturer rawManufacturer = techniqueBaseRawManufacturerMapper.getById(rawStandardRecord.getRawManufacturerId());
        Assert.notNull(rawManufacturer, "新增失败,不存在此原料厂商");

        TechniqueBaseRawMaterial rawMaterial = techniqueBaseRawMaterialMapper.getById(rawStandardRecord.getRawMaterialId());
        Assert.notNull(rawMaterial, "新增失败,不存在此原材料");

        rawStandardRecord.setBatchNumberId(commonBatchNumber.getId());

        Assert.notNull(rawStandardRecord.getEffectiveTime(), "新增失败,生效日期为空");

        techniqueRawStandardRecordMapper.insert(rawStandardRecord);

        //==GET标准表数据
        List<TechniqueRawTestItemDTO> rawStandards = standardRecord.getRawStandards();

        List<TechniqueRawTestItemStandard> testItemStandards = new ArrayList<>();

        int size = rawStandards.size();

        for (int i = 0; i < size; i++) {
            TechniqueRawTestItemDTO testItem = rawStandards.get(i);

            TechniqueRawTestItemStandard standard = testItem.getTechniqueRawTestItemStandard();

            Integer testItemId = standard.getTestItemId();

            Assert.notNull(testItemMapper.find(testItemId), "新增失败,不存在此检测项目");

            standard.setRawStandardRecordId(rawStandardRecord.getId());

            testItemStandards.add(standard);

        }

        if (size > 0) techniqueRawTestItemStandardMapper.batchInsert(testItemStandards);


//        //同步编号表中的数据
//        synchroSN(rawMaterial.getName(), rawManufacturer.getName());

        return commonBatchNumberDTO;
    }

    /**
     * 更新原材料标准
     *
     * @param commonBatchNumberDTO 公共批号DTO
     * @return
     */
    @Override
    @Transactional
    public CommonBatchNumberDTO updateRawStandard(CommonBatchNumberDTO<TechniqueRawStandardRecordDTO> commonBatchNumberDTO) {
        //批号
        CommonBatchNumber commonBatchNumber = commonBatchNumberDTO.getCommonBatchNumber();
        //标准DTO
        TechniqueRawStandardRecordDTO standardRecordDTO = commonBatchNumberDTO.getDetails();
        //标准实体
        TechniqueRawStandardRecord newRecord = standardRecordDTO.getTechniqueRawStandardRecord();
        //标准值DTO
        List<TechniqueRawTestItemDTO> rawStandards = standardRecordDTO.getRawStandards();
        //批号id
        Integer batchNumberId = commonBatchNumber.getId();

        CommonBatchNumber oldValue = commonBatchNumberMapper.byId(batchNumberId);

        Assert.isTrue(null != oldValue &&
                BATCH_TECH_RAW_STANDARD.equals(oldValue.getDataType()), "更新失败,不存在此原材料标准");


        //==更新标准
        TechniqueRawStandardRecord oldRawStandardRecord = techniqueRawStandardRecordMapper.findByBatchNumberId(batchNumberId);

        Assert.notNull(newRecord.getEffectiveTime(), "生效日期不能为空");
        oldRawStandardRecord.setEffectiveTime(newRecord.getEffectiveTime());

        techniqueRawStandardRecordMapper.update(oldRawStandardRecord);

        //标准值实体
        List<TechniqueRawTestItemStandard> testItemStandards = new ArrayList<>();

        for (TechniqueRawTestItemDTO testItemDTO : rawStandards) {
            testItemDTO.getTechniqueRawTestItemStandard().setRawStandardRecordId(oldRawStandardRecord.getId());
            testItemStandards.add(testItemDTO.getTechniqueRawTestItemStandard());
        }

        //==更新标准值
        techniqueRawTestItemStandardMapper.deleteByRecordId(oldRawStandardRecord.getId());

        techniqueRawTestItemStandardMapper.batchInsert(testItemStandards);

        return commonBatchNumberDTO;
    }

    /**
     * 查询所有
     *
     * @param personName 名称模糊
     * @return
     */
    @Override
    public List<CommonBatchNumberDTO> allRawStandardPersonNameLike(String personName, Integer materialId, Integer factoryId) {
        List<TechniqueRawStandardRecord> recordList = techniqueRawStandardRecordMapper.byMaterialIdAndFactoryId(materialId, factoryId);

        if (null == recordList || 0 == recordList.size()) return null;

        //拿到最新标准
        TechniqueRawStandardRecord lastedStandard = techniqueRawStandardRecordMapper.lastedStandard(materialId, factoryId);


        List<Integer> ids = new ArrayList<>();
        recordList.stream().forEach(e -> ids.add(e.getBatchNumberId()));


        List<CommonBatchNumberDTO> dtos = commonBatchNumberMapper.createDTOByIdsAndPersonName(ids, personName);
        if (null == dtos || dtos.isEmpty()) return null;
        if (null != lastedStandard) {
            Optional<CommonBatchNumberDTO> numberDTO = dtos.stream().filter(e -> e.getCommonBatchNumber().getId().equals(lastedStandard.getBatchNumberId())).findFirst();
            numberDTO.get().getCommonBatchNumber().setIsPublished(1);
        }

        return dtos;
    }

    /**
     * 查看 原材料标准详情
     *
     * @param batchNumberId 批号id
     * @return
     */
    @Override
    public CommonBatchNumberDTO rawStandardDetail(Integer batchNumberId) {
        CommonBatchNumberDTO commonBatchNumber = commonBatchNumberMapper.createDTOByIdAndDataType(batchNumberId, null);

        if (null == commonBatchNumber || !BATCH_TECH_RAW_STANDARD.equals(commonBatchNumber.getCommonBatchNumber().getDataType()))
            return null;

        //构建DTO
        TechniqueRawStandardRecordDTO techniqueRawStandardRecordDTO = new TechniqueRawStandardRecordDTO();

        TechniqueRawStandardRecord rawStandardRecord = techniqueRawStandardRecordMapper.findByBatchNumberId(batchNumberId);


        TechniqueBaseRawMaterial rawMaterial = techniqueBaseRawMaterialMapper.getById(rawStandardRecord.getRawMaterialId());

        TechniqueBaseRawManufacturer rawManufacturer = techniqueBaseRawManufacturerMapper.getById(rawStandardRecord.getRawManufacturerId());


        //设置标准表
        techniqueRawStandardRecordDTO.setTechniqueRawStandardRecord(rawStandardRecord);

        List<TechniqueRawTestItemStandard> itemStandards = techniqueRawTestItemStandardMapper.findByRecordId(rawStandardRecord.getId());

        List<TechniqueRawTestItemDTO> testItemDTOS = new ArrayList<>();

        for (int i = 0; i < itemStandards.size(); i++) {
            TechniqueRawTestItemStandard itemStandard = itemStandards.get(i);

            TestItem testItem = testItemMapper.find(itemStandard.getTestItemId());

            TechniqueRawTestItemDTO testItemDTO = new TechniqueRawTestItemDTO();

            testItemDTO
                    .setTestItem(testItem)
                    .setTechniqueRawTestItemStandard(itemStandard);

            testItemDTOS.add(testItemDTO);

        }

        //设置标准值
        techniqueRawStandardRecordDTO.setRawStandards(testItemDTOS)
                .setRawManufacturerName(rawManufacturer.getName())
                .setRawMaterialName(rawMaterial.getName());

        commonBatchNumber.setDetails(techniqueRawStandardRecordDTO);


        return commonBatchNumber;
    }

    /**
     * 原材料主成分
     *
     * @param rawId 技术中心原材料主键
     * @return
     */
    @Override
    public List<TestItem> rawIngredient(Integer rawId) {
        List<TechniqueRawItemRecord> techniqueRawItemRecords = techniqueRawItemRecordMapper.byRawId(rawId);
        if (null == techniqueRawItemRecords || 0 == techniqueRawItemRecords.size()) return null;

        List<TestItem> testItems = new ArrayList<>();
        for (TechniqueRawItemRecord techniqueRawItemRecord : techniqueRawItemRecords) {

            Integer testItemId = techniqueRawItemRecord.getTestItemId();

            TestItem testItem = testItemMapper.find(testItemId);

            testItems.add(testItem);
        }

        return testItems;
    }

    /**
     * 查询最新标准
     *
     * @param rawId 原材料编号id
     * @return
     */
    @Override
    public List<String> lastStandard(Integer rawId, String materialName, String manufacturerName) {
        if (null != rawId) {
            RepoBaseSerialNumber serialNumber = repoBaseSerialNumberMapper.findById(rawId);
            Assert.notNull(serialNumber, String.format("不存在id为%d的编号信息", rawId));
            if (serialNumber.getMaterialClass() != QualitySampleTypeEnum.SAMPLE_RAWMATERIAL.get()) return null;

            manufacturerName = serialNumber.getManufacturerName();
            materialName = serialNumber.getMaterialName();
        }
        TechniqueBaseRawManufacturer manufacturer = techniqueBaseRawManufacturerMapper.getByName(manufacturerName);
        Assert.notNull(manufacturer, String.format("%s-此厂商还没有建立可用标准", manufacturerName));
        TechniqueBaseRawMaterial rawMaterial = techniqueBaseRawMaterialMapper.getByName(materialName);
        Assert.notNull(rawMaterial, String.format("%s-此原料还没有建立可用标准", materialName));


        TechniqueRawStandardRecord standardRecord = techniqueRawStandardRecordMapper.lastedStandard(rawMaterial.getId(), manufacturer.getId());
        Assert.notNull(standardRecord, String.format("没有可用的%s-%s标准", materialName, manufacturerName));

        List<TechniqueRawTestItemStandard> results = techniqueRawTestItemStandardMapper.findByRecordId(standardRecord.getId());
        results.sort(Comparator.comparing(TechniqueRawTestItemStandard::getTestItemId));

        List<String> standardResults = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            TechniqueRawTestItemStandard result = results.get(i);
            TestItem testItem = testItemMapper.find(result.getTestItemId());
            Assert.notNull(testItem,String.format("id为%d的检测项目不存在",result.getId()));
            StringBuffer resultString = new StringBuffer(testItem.getName());
            resultString.append(",");
            resultString.append(testItem.getUnit());
            resultString.append(",");
            resultString.append(result.getValue());

            standardResults.add(resultString.toString());

        }

        return standardResults;
    }

    /**
     * 同步基本编号表
     *
     * @param rawName          材料名
     * @param manufacturerName 厂商名
     */
    @Transactional
    public void synchroSN(String rawName, String manufacturerName) {
        //根据材料名和厂商名查询基本编号表
        RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.byStandard(rawName, manufacturerName);
        if (null != repoBaseSerialNumber) return;
        Integer type = QualitySampleTypeEnum.SAMPLE_RAWMATERIAL.get();
        repoBaseSerialNumberMapper.insert(new RepoBaseSerialNumber()
                .setMaterialClass(type)
                .setSerialNumber(NumberGenerator.serialNumberGenerator(type))
                .setMaterialName(rawName)
                .setManufacturerName(manufacturerName));
    }

    @Override
    public TechniqueBaseRawMaterial addRowExtra(String materialName, Integer[] ids) {
        TechniqueBaseRawMaterial oldValue = techniqueBaseRawMaterialMapper.getByName(materialName);

        Assert.isNull(oldValue, "新增失败,存在名称相同的原材料");

        Assert.notEmpty(ids, "检测项目不能为空");

        TechniqueBaseRawMaterial newValue = new TechniqueBaseRawMaterial();
        newValue.setName(materialName);

        techniqueBaseRawMaterialMapper.insert(newValue);

        Integer id = newValue.getId();

        List<TechniqueRawItemRecord> techniqueRawItemRecords = new ArrayList<>();

        for (Integer i:ids) {
            TechniqueRawItemRecord techniqueRawItemRecord = new TechniqueRawItemRecord();

            TestItem testItem = testItemMapper.find(i);

            Assert.notNull(testItem, "该检测项目不存在");

            techniqueRawItemRecord
                    .setRawMaterialId(id)
                    .setTestItemId(testItem.getId());

            techniqueRawItemRecords.add(techniqueRawItemRecord);

        }

        techniqueRawItemRecordMapper.batchInsert(techniqueRawItemRecords);

        return newValue;
    }

    @Override
    public List getItemsByRawId(Integer rawId) {
        List<Integer> ans = new ArrayList<>();
        List<TechniqueRawItemRecord> records = techniqueRawItemRecordMapper.byRawId(rawId);
        for (int i = 0; i < records.size(); i++) {
            ans.add(records.get(i).getTestItemId());
        }
        return ans;
    }

    @Override
    public List<RawStandardDTO> getCurrentRawStandard() {
        List<RawStandardDTO> ans = new ArrayList<>();
        Map<KeyforProRawStandard,List<Integer>> map = new HashMap<>();
        List<TechniqueRawStandardRecord> records = techniqueRawStandardRecordMapper.getAll();

        for(int i=0;i<records.size();i++){
            TechniqueRawStandardRecord temp = records.get(i);
            KeyforProRawStandard key = new KeyforProRawStandard(temp.getRawMaterialId(),temp.getRawManufacturerId());
            if(map.containsKey(key)){
                List<Integer> ids = map.get(key);
                ids.add(temp.getBatchNumberId());
                map.put(key,ids);
            }else{
                List<Integer> ids = new ArrayList<>();
                ids.add(temp.getBatchNumberId());
                map.put(key,ids);
            }
        }

        for(KeyforProRawStandard key:map.keySet()){
            List<Integer> cIds = map.get(key);
            List<CommonBatchNumber> commonBatchNumbers = new ArrayList<>();
            for(int i=0;i<cIds.size();i++){
                commonBatchNumbers.add(commonBatchNumberMapper.byId(cIds.get(i)));
            }
            CommonBatchNumber temp = commonBatchNumbers.get(0);
            for(int i=1;i<commonBatchNumbers.size();i++){
                if(temp.getCreateTime().getTime()<commonBatchNumbers.get(i).getCreateTime().getTime())
                    temp = commonBatchNumbers.get(i);
            }
            RawStandardDTO standardDTO = new RawStandardDTO();
            standardDTO.setManufacturer(techniqueBaseRawManufacturerMapper.getById(key.getClassId()));
            standardDTO.setMaterial(techniqueBaseRawMaterialMapper.getById(key.getProductId()));
            standardDTO.setStandandId(techniqueRawStandardRecordMapper.findByBatchNumberId(temp.getId()).getId());
            ans.add(standardDTO);
        }
        return ans;
    }
}
