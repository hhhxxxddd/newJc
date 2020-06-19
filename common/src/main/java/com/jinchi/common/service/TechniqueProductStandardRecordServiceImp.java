package com.jinchi.common.service;

import com.jinchi.common.constant.BatchStatusEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.technique.TechniqueBaseProductClassDTO;
import com.jinchi.common.dto.technique.TechniqueProductStandardRecordDTO;
import com.jinchi.common.dto.technique.TechniqueProductTestItemDTO;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.NumberGenerator;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author：XudongHu
 * @className:TechniqueProductStandardRecordServiceImp
 * @description: 技术 成品标准
 * @date:15:31 2018/12/28
 */
@Service
public class TechniqueProductStandardRecordServiceImp implements TechniqueProductStandardRecordService {
    private final static Logger logger = LoggerFactory.getLogger(TechniqueProductStandardRecordServiceImp.class);
    private static final Integer BATCH_TECH_PRODUCT_STANDARD = BatchTypeEnum.TECH_PRODUCT_STANDARD.typeCode();
    @Autowired
    private TechniqueBaseProductClassMapper techniqueBaseProductClassMapper;
    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private TechniqueProductStandardRecordMapper techniqueProductStandardRecordMapper;
    @Autowired
    private TechniqueProductTestItemStandardMapper techniqueProductTestItemStandardMapper;
    @Autowired
    private TestItemMapper testItemMapper;
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    private TechniqueBaseProductBindManufacturerMapper productBindManufacturerMapper;
    @Autowired
    private TechniqueProductNewStandardRecordMapper productNewStandardRecordMapper;

    /**
     * 新增型号
     *
     * @param techniqueBaseProductClassDTO 型号实体
     * @return
     */
    @Override
    @Transactional
    public TechniqueBaseProductClass newClass(TechniqueBaseProductClassDTO techniqueBaseProductClassDTO) {

        TechniqueBaseProductClass techniqueBaseProductClass = techniqueBaseProductClassDTO.getTechniqueBaseProductClass();

        //判断是否为父子节点
        Integer parentId = techniqueBaseProductClass.getParent();

        //-1为最上层
        if (null == parentId || parentId.equals(-1)) {
            techniqueBaseProductClass.setParent(-1);
            techniqueBaseProductClassMapper.insert(techniqueBaseProductClass);

            TechniqueBaseProductBindManufacturer bind = new TechniqueBaseProductBindManufacturer();
            bind.setProductId(techniqueBaseProductClassDTO.getProductId());
            bind.setClassId(techniqueBaseProductClass.getId());
            productBindManufacturerMapper.insertSelective(bind);

            return techniqueBaseProductClass;
        }
        TechniqueBaseProductClass parentClass = techniqueBaseProductClassMapper.findById(parentId);
        Assert.notNull(parentClass, "新增失败,不存在父节点");

        //父节点如果之前为根节点 将其设置为非根节点
        if (parentClass.getIsLeaf().equals(1)) {
            parentClass.setIsLeaf(0);
            techniqueBaseProductClassMapper.update(parentClass);
        }

        techniqueBaseProductClassMapper.insert(techniqueBaseProductClass);

        return techniqueBaseProductClass;
    }

    /**
     * 查询所有型号
     *
     * @param name     型号名称
     * @param parentId 父id -1为最上层
     * @return
     */
    @Override
    public List<TechniqueBaseProductClass> findAllClass(String name, Integer parentId) {
        if (null == parentId || parentId.equals(-1)) parentId = -1;
        List<TechniqueBaseProductClass> techniqueBaseProductClasses = techniqueBaseProductClassMapper.nameLikeAndParentIs(name, parentId);
        return techniqueBaseProductClasses;
    }

    @Override
    public List<TechniqueBaseProductClass> byProductId(Integer productId) {
        TechniqueBaseProductBindManufacturerExample example = new TechniqueBaseProductBindManufacturerExample();
        example.createCriteria().andProductIdEqualTo(productId);
        List<TechniqueBaseProductBindManufacturer> list = productBindManufacturerMapper.selectByExample(example);

        List<TechniqueBaseProductClass> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            res.add(techniqueBaseProductClassMapper.findById(list.get(i).getClassId()));
        }
        return res;
    }

    @Override
    public TechniqueBaseProductClass editClass(TechniqueBaseProductClass techniqueBaseProductClass) {
        techniqueBaseProductClassMapper.updateName(techniqueBaseProductClass);
        return techniqueBaseProductClass;
    }

    @Override
    public Integer deleteClass(Integer classId) {
        TechniqueProductNewStandardRecordExample example = new TechniqueProductNewStandardRecordExample();
        example.createCriteria().andProductClassIdEqualTo(classId);
        List<TechniqueProductNewStandardRecord> recordList = productNewStandardRecordMapper.selectByExample(example);

        if (recordList.size() > 0) {
            return -1;
        }

        TechniqueBaseProductBindManufacturerExample example1 = new TechniqueBaseProductBindManufacturerExample();
        example1.createCriteria().andClassIdEqualTo(classId);
        productBindManufacturerMapper.deleteByExample(example1);

        techniqueBaseProductClassMapper.deleteById(classId);
        return 0;
    }

    //=======================================================================================================

    /**
     * 新增/迭代   成品标准
     *
     * @param commonBatchNumberDTO 批号dto
     * @return
     */
    @Override
    @Transactional
    public CommonBatchNumberDTO newProductStandard(CommonBatchNumberDTO<TechniqueProductStandardRecordDTO> commonBatchNumberDTO) {
        CommonBatchNumber commonBatchNumber = commonBatchNumberDTO.getCommonBatchNumber();

        Integer batchNumberId = commonBatchNumber.getId();

        Integer createPersonId = commonBatchNumber.getCreatePersonId();

        AuthUserDTO userDTO = authUserMapper.byId(createPersonId);

        Assert.notNull(userDTO, "新增/迭代失败,创建人不存在");


        if (null != batchNumberId) {
            CommonBatchNumber batchNumber = commonBatchNumberMapper.byId(batchNumberId);
            Assert.isTrue(null != batchNumber && batchNumber.getDataType().equals(BATCH_TECH_PRODUCT_STANDARD)
                    , "迭代失败,不存在迭代之前的数据");
        }


        commonBatchNumber
                .setBatchNumber(NumberGenerator.batchNumberGenerator(BATCH_TECH_PRODUCT_STANDARD))
                .setStatus(BatchStatusEnum.SAVE.status())
                .setCreateTime(new Date())
                .setDescription(BatchTypeEnum.getByDataType(BATCH_TECH_PRODUCT_STANDARD).description())
                .setDataType(BATCH_TECH_PRODUCT_STANDARD);

        commonBatchNumberMapper.insert(commonBatchNumber);

        //==>GET 详情
        TechniqueProductStandardRecordDTO standardRecordDTO = commonBatchNumberDTO.getDetails();

        TechniqueProductStandardRecord record = standardRecordDTO.getTechniqueProductStandardRecord();

        Integer productClassId = record.getProductClassId();

        TechniqueBaseProductClass productClass = techniqueBaseProductClassMapper.findById(productClassId);

        Assert.isTrue(null != productClass && productClass.getIsLeaf().equals(1), "产品型号不是基本型号");

        record.setBatchNumberId(commonBatchNumber.getId());

        techniqueProductStandardRecordMapper.insert(record);

        List<TechniqueProductTestItemDTO> itemDTOs = standardRecordDTO.getTechniqueProductTestItemDTOs();

        Assert.notEmpty(itemDTOs, "标准值不能为空");

        List<TechniqueProductTestItemStandard> itemStandards = new ArrayList<>();

        int size = itemDTOs.size();

        for (int i = 0; i < size; i++) {
            TechniqueProductTestItemDTO testItemDTO = itemDTOs.get(i);

            testItemDTO.getTechniqueProductTestItemStandard().setProductStandardId(record.getId());

            itemStandards.add(testItemDTO.getTechniqueProductTestItemStandard());

        }

        if (size > 0)
            techniqueProductTestItemStandardMapper.batchInsert(itemStandards);


        return commonBatchNumberDTO;
    }

    /**
     * 更新       成品标准
     *
     * @param commonBatchNumberDTO 批号dto
     * @return
     */
    @Override
    @Transactional
    public CommonBatchNumberDTO updateProductStandard(CommonBatchNumberDTO<TechniqueProductStandardRecordDTO> commonBatchNumberDTO) {
        //批号
        CommonBatchNumber commonBatchNumber = commonBatchNumberDTO.getCommonBatchNumber();
        //标准DTO
        TechniqueProductStandardRecordDTO recordDTO = commonBatchNumberDTO.getDetails();
        //标准实体
        TechniqueProductStandardRecord newRecord = recordDTO.getTechniqueProductStandardRecord();
        //标准值DTO
        List<TechniqueProductTestItemDTO> itemDTOs = recordDTO.getTechniqueProductTestItemDTOs();
        //批号id
        Integer batchNumberId = commonBatchNumber.getId();

        CommonBatchNumber oldValue = commonBatchNumberMapper.byId(batchNumberId);

        Assert.isTrue(null != oldValue &&
                BATCH_TECH_PRODUCT_STANDARD.equals(oldValue.getDataType()), "更新失败,不存在此产品标准");


        //更新标准
        TechniqueProductStandardRecord oldRecord = techniqueProductStandardRecordMapper.byBatchNumberId(batchNumberId);

        oldRecord.setEffectiveTime(newRecord.getEffectiveTime());

        techniqueProductStandardRecordMapper.update(oldRecord);

        Integer oldRecordId = oldRecord.getId();

        //标准值实体
        List<TechniqueProductTestItemStandard> itemStandards = new ArrayList<>();
        for(TechniqueProductTestItemDTO testItemDTO:itemDTOs){
            testItemDTO.getTechniqueProductTestItemStandard().setProductStandardId(oldRecordId);
            itemStandards.add(testItemDTO.getTechniqueProductTestItemStandard());
        }

        //更新标准值
        techniqueProductTestItemStandardMapper.deleteByRecordId(oldRecord.getId());

        techniqueProductTestItemStandardMapper.batchInsert(itemStandards);

        return commonBatchNumberDTO;
    }

    /**
     * 详情
     *
     * @param batchNumberId 批号id
     * @return
     */
    @Override
    public CommonBatchNumberDTO byBatchNumberId(Integer batchNumberId) {
        CommonBatchNumberDTO commonBatchNumber = commonBatchNumberMapper.createDTOByIdAndDataType(batchNumberId, null);

        if (null == commonBatchNumber || !BATCH_TECH_PRODUCT_STANDARD.equals(commonBatchNumber.getCommonBatchNumber().getDataType()))
            return null;

        //构建DTO
        TechniqueProductStandardRecordDTO productStandardRecordDTO = new TechniqueProductStandardRecordDTO();

        //产品标准记录
        TechniqueProductStandardRecord record = techniqueProductStandardRecordMapper.byBatchNumberId(batchNumberId);

        //产品信号
        TechniqueBaseProductClass productClass = techniqueBaseProductClassMapper.findById(record.getProductClassId());

        RepoBaseSerialNumber baseSerialNumber = repoBaseSerialNumberMapper.findById(record.getSerialNumberId());

        //设置标准表
        productStandardRecordDTO.setTechniqueProductStandardRecord(record);

        List<TechniqueProductTestItemStandard> itemStandards = techniqueProductTestItemStandardMapper.findByRecordId(record.getId());

        List<TechniqueProductTestItemDTO> testItemDTOS = new ArrayList<>();


        for (int i = 0; i < itemStandards.size(); i++) {
            TechniqueProductTestItemStandard itemStandard = itemStandards.get(i);

            TestItem testItem = testItemMapper.find(itemStandard.getTestItemId());

            TechniqueProductTestItemDTO testItemDTO = new TechniqueProductTestItemDTO();


            testItemDTO
                    .setTestItem(testItem)
                    .setTechniqueProductTestItemStandard(itemStandard);

            testItemDTOS.add(testItemDTO);

        }

        //设置标准值
        productStandardRecordDTO
                .setTechniqueProductTestItemDTOs(testItemDTOS)
                .setClassName(productClass.getName())
                .setProductName(baseSerialNumber.getMaterialName());

        commonBatchNumber.setDetails(productStandardRecordDTO);


        return commonBatchNumber;
    }

    /**
     * 查询所有
     *
     * @param name      创建人名称 模糊
     * @param productId 产品id NN
     * @param classId   型号id NN
     * @return
     */
    @Override
    public List<CommonBatchNumberDTO> byNameLikeAndMaterialIdAndClassId(String name, Integer productId, Integer classId) {
        List<TechniqueProductStandardRecord> records = techniqueProductStandardRecordMapper.byProductIdAndClassId(productId, classId);

        if (null == records || records.isEmpty()) return null;

        //批号ids
        List<Integer> batchNumberIds = new ArrayList<>();
        records.stream().forEach(e -> batchNumberIds.add(e.getBatchNumberId()));

        //拿到最新标准
        TechniqueProductStandardRecord lastedStandard = techniqueProductStandardRecordMapper.lastedStandard(productId);

        List<CommonBatchNumberDTO> dtos = commonBatchNumberMapper.createDTOByIdsAndPersonName(batchNumberIds, name);

        if (null == dtos || dtos.isEmpty()) return null;
        if(null!=lastedStandard){
            for(CommonBatchNumberDTO commonBatchNumberDTO:dtos){
                CommonBatchNumber commonBatchNumber = commonBatchNumberDTO.getCommonBatchNumber();
                if(commonBatchNumber.getId().equals(lastedStandard.getBatchNumberId())){
                    commonBatchNumber.setIsPublished(1);
                    break;
                }
            }
        }

        return dtos;
    }

    /**
     * 返回最新的标准
     * @param productId 成品编号id
     * @return
     */
    @Override
    public List<String> lastStandard(Integer productId,List<Integer> testItemIds) {
        TechniqueProductStandardRecord techniqueProductStandardRecord = techniqueProductStandardRecordMapper.lastedStandard(productId);
        if(null==techniqueProductStandardRecord) return null;
        List<TechniqueProductTestItemStandard> standardResults = techniqueProductTestItemStandardMapper.findByRecordId(techniqueProductStandardRecord.getId());
        Assert.notEmpty(standardResults,"数据库数据错误,标准值不存在");
        standardResults.sort(Comparator.comparing(TechniqueProductTestItemStandard::getTestItemId));

        List<String> standardString = new ArrayList<>();

        Boolean isExistFlag = testItemIds==null||testItemIds.isEmpty()?false:true;

        for (int i = 0; i < standardResults.size() ; i++) {
            TechniqueProductTestItemStandard standard = standardResults.get(i);
            Integer testItemId = standard.getTestItemId();

            if(isExistFlag&&!testItemIds.contains(testItemId)) continue;

            StringBuffer resultString = new StringBuffer();

            TestItem testItem = testItemMapper.find(testItemId);

            Assert.notNull(testItem,"id为"+testItemId+"的检测项目不存在");

            resultString
                    .append(testItem.getName())
                    .append(",")
                    .append(testItem.getUnit())
                    .append(",")
                    .append(standard.getValue());

            standardString.add(resultString.toString());
        }
        return standardString;
    }

}
