package com.jinchi.common.service;

import com.jinchi.common.constant.BatchStatusEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.KeyforProRawStandard;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.ProductStandardDTO;
import com.jinchi.common.dto.TechniqueProductStandardDTO;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.NumberGenerator;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class TechniqueProductServiceImp implements TechniqueProductService {
    @Autowired
    TechniqueBaseProductMaterialMapper materialMapper;
    @Autowired
    TechniqueProductNewStandardRecordMapper standardRecordMapper;
    @Autowired
    TechniqueProductItemRecordMapper itemRecordMapper;
    @Autowired
    TestItemMapper testItemMapper;
    @Autowired
    CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    TechniqueProductNewStandardRecordMapper techniqueProductNewStandardRecordMapper;
    @Autowired
    TechniqueProductTestItemStandardMapper techniqueProductTestItemStandardMapper;
    @Autowired
    TechniqueBaseProductMaterialMapper techniqueBaseProductMaterialMapper;
    @Autowired
    TechniqueBaseProductClassMapper techniqueBaseProductClassMapper;
    @Autowired
    AuthUserService authUserService;
    @Autowired
    TechniqueBaseProductBindManufacturerMapper productBindManufacturerMapper;

    @Override
    public void add(String productName) {
        TechniqueBaseProductMaterial techniqueBaseProductMaterial = new TechniqueBaseProductMaterial();
        techniqueBaseProductMaterial.setName(productName);
        materialMapper.insertSelective(techniqueBaseProductMaterial);
    }

    @Override
    public List getItemsByProductId(Integer id) {
        List<TestItem> ans = new ArrayList<>();
        TechniqueProductItemRecordExample example = new TechniqueProductItemRecordExample();
        example.createCriteria().andProductMaterialIdEqualTo(id);
        List<TechniqueProductItemRecord> records = itemRecordMapper.selectByExample(example);
        for(TechniqueProductItemRecord record:records){
            TestItem testItem = testItemMapper.find(record.getTestItemId());
            ans.add(testItem);
        }
        return ans;
    }

    @Override
    public Integer addProductStandard(Integer productId, Integer classId, Integer createUser,List<TestItemsPlus> items,String date) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date eff = new Date();
        try {
            eff = dataFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CommonBatchNumber commonBatchNumber = new CommonBatchNumber();
        commonBatchNumber
                .setDataType(BatchTypeEnum.TECH_PRODUCT_STANDARD.typeCode())
                .setStatus(BatchStatusEnum.SAVE.status())
                .setDescription(BatchTypeEnum.TECH_PRODUCT_STANDARD.description())
                .setCreateTime(new Date())
                .setBatchNumber(NumberGenerator.batchNumberGenerator(BatchTypeEnum.TECH_PRODUCT_STANDARD.typeCode()))
                .setCreatePersonId(createUser);
        commonBatchNumberMapper.insert(commonBatchNumber);

        //插入新标准
        TechniqueProductNewStandardRecord newStandardRecord = new TechniqueProductNewStandardRecord();
        newStandardRecord.setProductClassId(classId);
        newStandardRecord.setProductMaterialId(productId);
        newStandardRecord.setBatchNumberId(commonBatchNumber.getId());
        newStandardRecord.setEffectiveTime(eff);
        techniqueProductNewStandardRecordMapper.insertSelective(newStandardRecord);

        for(int i=0;i<items.size();i++){
            TechniqueProductTestItemStandard standard = new TechniqueProductTestItemStandard();
            standard.setProductStandardId(newStandardRecord.getId());
            standard.setTestItemId(items.get(i).getId());
            standard.setValue(""+items.get(i).getCount());
            techniqueProductTestItemStandardMapper.insert(standard);
        }
        return commonBatchNumber.getId();
    }

    @Override
    public TechniqueProductStandardDTO detailByCommonBatchId(Integer commonBatchId) {
        TechniqueProductStandardDTO ans = new TechniqueProductStandardDTO();
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(commonBatchId);
        ans.setCommonBatchNumber(commonBatchNumber);
        ans.setCreatePeople(authUserService.findById(commonBatchNumber.getCreatePersonId()).getName());

        TechniqueProductNewStandardRecordExample example = new TechniqueProductNewStandardRecordExample();
        example.createCriteria().andBatchNumberIdEqualTo(commonBatchId);
        List<TechniqueProductNewStandardRecord> records = techniqueProductNewStandardRecordMapper.selectByExample(example);
        TechniqueProductNewStandardRecord record = records.size()==0?null:records.get(0);

        Assert.notNull(record,"不存在该条标准记录");
        ans.setTechniqueProductNewStandardRecord(record);

        Integer pId = record.getProductMaterialId();
        Integer cId = record.getProductClassId();

        TechniqueBaseProductMaterialExample example1 = new TechniqueBaseProductMaterialExample();
        example1.createCriteria().andIdEqualTo(pId);
        List<TechniqueBaseProductMaterial> materials = techniqueBaseProductMaterialMapper.selectByExample(example1);
        TechniqueBaseProductMaterial material = materials.size()==0?null:materials.get(0);

        Assert.notNull(material,"不存在该成品");
        ans.setProductName(material.getName());

        TechniqueBaseProductClass classs = techniqueBaseProductClassMapper.findById(cId);
        Assert.notNull(classs,"不存在这一成品类型");
        ans.setMeterialClass(classs.getName());

        List<TechniqueProductTestItemStandard> standards = techniqueProductTestItemStandardMapper.findByRecordId(record.getId());
        List<TestItemsPlus> items = new ArrayList<>();
        for(TechniqueProductTestItemStandard t:standards){
            TestItemsPlus temp = new TestItemsPlus();
            temp.setId(t.getTestItemId());
            temp.setCount(t.getValue());
            TestItem temp2 = testItemMapper.find(temp.getId());
            temp.setName(temp2.getName());
            temp.setUnit(temp2.getUnit());
            items.add(temp);
        }
        ans.setItems(items);
        return ans;
    }

    @Override
    public List<TechniqueProductStandardDTO> getAllProductCommonBatch() {
        List<TechniqueProductStandardDTO> ans = new ArrayList<>();
        TechniqueProductNewStandardRecordExample example = new TechniqueProductNewStandardRecordExample();
        example.createCriteria();
        List<TechniqueProductNewStandardRecord> records = techniqueProductNewStandardRecordMapper.selectByExample(example);

        //获取产品+型号组合的所有批号id
        Map<KeyforProRawStandard,List<Integer>> map = new HashMap<>();
        for(int i=0;i<records.size();i++){
            TechniqueProductNewStandardRecord temp = records.get(i);
            KeyforProRawStandard key = new KeyforProRawStandard(temp.getProductMaterialId(),temp.getProductClassId());
            if(!map.containsKey(key)){
                List<Integer> cIds = new ArrayList<>();
                cIds.add(temp.getBatchNumberId());
                map.put(key,cIds);
            }else{
                List<Integer> cIds = map.get(key);
                cIds.add(temp.getBatchNumberId());
                map.put(key,cIds);
            }
        }

        //找时间最近的
        for(KeyforProRawStandard key:map.keySet()) {
            List<Integer> cIds = map.get(key);
            CommonBatchNumber commonBatchNumber = new CommonBatchNumber();
            commonBatchNumber.setPassTime(new Date(0));
            commonBatchNumber.setId(-1);
            for (int i = 0; i < cIds.size(); i++) {
                CommonBatchNumber temp = commonBatchNumberMapper.byId(cIds.get(i));
                if (temp.getPassTime()!=null&&(temp.getPassTime().getTime() > commonBatchNumber.getPassTime().getTime())) {
                    commonBatchNumber = temp;
                }
            }
            example.clear();
            example.createCriteria().andProductMaterialIdEqualTo(key.getProductId()).andProductClassIdEqualTo(key.getClassId()).andBatchNumberIdEqualTo(commonBatchNumber.getId());
            List<TechniqueProductNewStandardRecord> recordList =  techniqueProductNewStandardRecordMapper.selectByExample(example);
            TechniqueProductNewStandardRecord record = recordList.size()==0?null:recordList.get(0);
            TechniqueProductStandardDTO standardDTO = new TechniqueProductStandardDTO();

            if(record!=null) {
                /*List<TechniqueProductTestItemStandard> itemStandards = techniqueProductTestItemStandardMapper.findByRecordId(record.getId());
                List<TestItemsPlus> itemsPluses = new ArrayList<>();
                for (int i = 0; i < itemStandards.size(); i++) {
                    TestItemsPlus temp = new TestItemsPlus();
                    temp.setCount(itemStandards.get(i).getValue());
                    TestItem item = testItemMapper.find(itemStandards.get(i).getTestItemId());
                    temp.setUnit(item.getUnit());
                    temp.setName(item.getName());
                    temp.setId(item.getId());
                    itemsPluses.add(temp);
                }*/
               // standardDTO.setItems(itemsPluses);

                TechniqueBaseProductMaterialExample example1 = new TechniqueBaseProductMaterialExample();
                example1.createCriteria().andIdEqualTo(record.getProductMaterialId());
                List<TechniqueBaseProductMaterial> materials = techniqueBaseProductMaterialMapper.selectByExample(example1);
                TechniqueBaseProductMaterial material = materials.size() == 0 ? null : materials.get(0);

                Assert.notNull(material, "不存在该成品");
                TechniqueBaseProductClass classs = techniqueBaseProductClassMapper.findById(record.getProductClassId());
                Assert.notNull(classs, "不存在这一成品类型");

                standardDTO.setProductName(material.getName());
                standardDTO.setMeterialClass(classs.getName());

               // standardDTO.setCommonBatchNumber(commonBatchNumber);
                standardDTO.setTechniqueProductNewStandardRecord(record);
                ans.add(standardDTO);
            }
        }
        return ans;
    }

    @Override
    public List<ProductStandardDTO> getAllStandardByPIdandCId(Integer productId, Integer classId,String userName,Integer page,Integer size) {
        List<ProductStandardDTO> ans = new ArrayList<>();

        TechniqueProductNewStandardRecordExample example = new TechniqueProductNewStandardRecordExample();
        example.createCriteria().andProductMaterialIdEqualTo(productId).andProductClassIdEqualTo(classId);
        List<TechniqueProductNewStandardRecord> records = techniqueProductNewStandardRecordMapper.selectByExample(example);

        for(int i=0;i<records.size();i++){
            TechniqueProductNewStandardRecord temp = records.get(i);
            ProductStandardDTO standardDTO = new ProductStandardDTO();
            Integer commonBatchId = temp.getBatchNumberId();
            CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(commonBatchId);

            Assert.notNull(commonBatchNumber,"不存在该批号");

            standardDTO.setCommonBatchId(commonBatchId);
            standardDTO.setCreateTime(commonBatchNumber.getCreateTime());
            standardDTO.setAudit(commonBatchNumber.getStatus());
            standardDTO.setCreatePeople(authUserService.findById(commonBatchNumber.getCreatePersonId()).getName());
            standardDTO.setCommonBathch(commonBatchNumber.getBatchNumber());
            if("".equals(userName)||standardDTO.getCreatePeople().contains(userName)) {
                ans.add(standardDTO);
            }
        }
        Page pageInfo = new Page(ans,page,size);
        return pageInfo.getList();
    }

    @Override
    public List<TechniqueBaseProductMaterial> getAllProduct() {
        TechniqueBaseProductMaterialExample example = new TechniqueBaseProductMaterialExample();
        example.createCriteria();
        return techniqueBaseProductMaterialMapper.selectByExample(example);
    }

    @Transactional
    @Override
    public Integer updateByCommonBatchId(Integer commonBatchId,String effTime, List<TestItemsPlus> items) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date eff = new Date();
        try {
            eff = dataFormat.parse(effTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TechniqueProductNewStandardRecord record = new TechniqueProductNewStandardRecord();
        record.setBatchNumberId(commonBatchId);
        record.setEffectiveTime(eff);

        TechniqueProductNewStandardRecordExample example = new TechniqueProductNewStandardRecordExample();
        example.createCriteria().andBatchNumberIdEqualTo(record.getBatchNumberId());
        techniqueProductNewStandardRecordMapper.updateByExampleSelective(record,example);

        TechniqueProductNewStandardRecord temp = techniqueProductNewStandardRecordMapper.selectByExample(example).get(0);
        Integer standardId = temp.getId();

        techniqueProductTestItemStandardMapper.deleteByRecordId(standardId);

        for(int i=0;i<items.size();i++){
            TestItemsPlus plus = items.get(i);
            TechniqueProductTestItemStandard standard = new TechniqueProductTestItemStandard();
            standard.setProductStandardId(standardId);
            standard.setTestItemId(plus.getId());
            standard.setValue(plus.getCount());
            techniqueProductTestItemStandardMapper.insert(standard);
        }
        return record.getBatchNumberId();
    }

    @Override
    public List getItemsByProductStandardId(Integer standardId) {
        List<Integer> ans = new ArrayList<>();
        List<TechniqueProductTestItemStandard> standards = techniqueProductTestItemStandardMapper.findByRecordId(standardId);
        for (int i = 0; i < standards.size(); i++) {
            ans.add(standards.get(i).getTestItemId());
        }
        return ans;
    }

    @Override
    public TechniqueBaseProductMaterial editProductName(TechniqueBaseProductMaterial techniqueBaseProductMaterial) {
        TechniqueBaseProductMaterialExample example = new TechniqueBaseProductMaterialExample();
        example.createCriteria().andIdEqualTo(techniqueBaseProductMaterial.getId());
        techniqueBaseProductMaterialMapper.updateByExampleSelective(techniqueBaseProductMaterial, example);
        return techniqueBaseProductMaterial;
    }

    @Override
    public Integer deleteById(Integer productId) {
        TechniqueBaseProductBindManufacturerExample example = new TechniqueBaseProductBindManufacturerExample();
        example.createCriteria().andProductIdEqualTo(productId);
        List<TechniqueBaseProductBindManufacturer> list = productBindManufacturerMapper.selectByExample(example);

        if (list.size() > 0) {
            return -1;
        }

        TechniqueBaseProductMaterialExample example1 = new TechniqueBaseProductMaterialExample();
        example1.createCriteria().andIdEqualTo(productId);
        techniqueBaseProductMaterialMapper.deleteByExample(example1);

        return 0;
    }
}
