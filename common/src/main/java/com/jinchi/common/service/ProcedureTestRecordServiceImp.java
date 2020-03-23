package com.jinchi.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinchi.common.constant.BatchStatusEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.constant.QualitySampleTypeEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.ProcedureTestRecordDTO;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author：XudongHu
 * @className:ProcedureTestRecordServiceImp
 * @description: 制程检验
 * @date:12:35 2018/11/23
 */
@Service
public class ProcedureTestRecordServiceImp implements ProcedureTestRecordService {
    private final static Logger logger = LoggerFactory.getLogger(ProcedureTestRecordService.class);

    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    private ProcedureTestRecordMapper procedureTestRecordMapper;
    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private TestItemMapper testItemMapper;
    @Autowired
    private ProductionProcessMapper productionProcessMapper;
    @Autowired
    private DeliveryFactoryMapper deliveryFactoryMapper;
    @Autowired
    private DataTaskRecordMapper dataTaskRecordMapper;
    @Autowired
    private AuthRoleMapper authRoleMapper;
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    /*@Autowired
    private QualityBaseDetectItemMapper detectItemMapper;*/

    /**
     * 新增制程检测
     *
     * @param procedureBatchNumber
     * @return
     */
    @Override
    @Transactional
    public CommonBatchNumberDTO add(CommonBatchNumberDTO<List<ProcedureTestRecordDTO>> procedureBatchNumber) {
        /**
         * 验证和设置批号
         */
        CommonBatchNumber commonBatchNumber = procedureBatchNumber.getCommonBatchNumber();

        AuthUserDTO authUserDTO = authUserMapper.byId(commonBatchNumber.getCreatePersonId());

        Assert.notNull(authUserDTO, "批号创建人不存在");

        //录入编号数据
        commonBatchNumber
                .setStatus(BatchStatusEnum.SAVE.status())
                .setDataType(BatchTypeEnum.QUALITY_PRODUCT.typeCode())
                .setCreateTime(new Date())
                .setDescription("制程检验数据")
                .setBatchNumber(NumberGenerator.batchNumberGenerator(BatchTypeEnum.QUALITY_PRODUCT.typeCode()));

        commonBatchNumberMapper.insert(commonBatchNumber);

        procedureBatchNumber.convertDTO(commonBatchNumber, authUserDTO.getName());

        /**
         * 设置details
         */
        //批量存储制程检验
        List<ProcedureTestRecordDTO> detailsDTOS = procedureBatchNumber.getDetails();

        Integer batchNumberId = commonBatchNumber.getId();

        this.addDetails(detailsDTOS, batchNumberId);


        return procedureBatchNumber;
    }

    /**
     * 更新制程检测
     *
     * @param procedureBatchNumber
     * @return
     */
    @Override
    @Transactional
    public CommonBatchNumberDTO update(CommonBatchNumberDTO<List<ProcedureTestRecordDTO>> procedureBatchNumber) {
        //验证批号
        CommonBatchNumber commonBatchNumber = procedureBatchNumber.getCommonBatchNumber();

        Assert.notNull(commonBatchNumber.getId(), "更新id不能为空");

        CommonBatchNumber oldValue = commonBatchNumberMapper.byId(commonBatchNumber.getId());
        Assert.notNull(oldValue, "更新失败,批号不存在");

        //非保存数据不可更新
        Assert.isTrue(oldValue.getStatus().equals(BatchStatusEnum.SAVE.status()), "非保存数据不可更新");

        //首先更新批号
        oldValue.setMemo(commonBatchNumber.getMemo());
        commonBatchNumberMapper.update(oldValue);

        //先要删除 制程检测的检测项目
        List<ProcedureTestRecord> procedureTestRecords = procedureTestRecordMapper.findByBatchNumberId(commonBatchNumber.getId());
        if (null != procedureTestRecords && 0 != procedureTestRecords.size()) {
            List<Integer> testRecordIds = new ArrayList<>();
            for (ProcedureTestRecord procedureTestRecord : procedureTestRecords) {
                testRecordIds.add(procedureTestRecord.getId());
            }
            procedureTestRecordMapper.deleteTestItemRecord(testRecordIds);
        }

        //再删除所有制程检测
        procedureTestRecordMapper.deleteByBatchNumberId(commonBatchNumber.getId());

        //再新增新的制程检测
        List<ProcedureTestRecordDTO> detailsDTOS = procedureBatchNumber.getDetails();

        this.addDetails(detailsDTOS, commonBatchNumber.getId());

        return procedureBatchNumber;
    }

    /**
     * 迭代
     * <p>
     * 迭代相当于是新增,在已存在的版本上新增一个新的版本
     *
     * @param procedureBatchNumber
     * @return
     */
    @Override
    @Transactional
    public CommonBatchNumberDTO iteration(CommonBatchNumberDTO<List<ProcedureTestRecordDTO>> procedureBatchNumber) {
        CommonBatchNumber newValue = procedureBatchNumber.getCommonBatchNumber();

        List<ProcedureTestRecordDTO> procedureTestRecordDTOs = procedureBatchNumber.getDetails();

        Assert.notNull(newValue, "迭代请输入对应的批号");

        Integer lastIterationBatchId = newValue.getId();

        Assert.notNull(lastIterationBatchId, "迭代的批号id不存在");

        CommonBatchNumber oldValue = commonBatchNumberMapper.byId(lastIterationBatchId);

        Assert.isTrue(null != oldValue
                && oldValue.getDataType().equals(BatchTypeEnum.QUALITY_PRODUCT.typeCode())
                && oldValue.getStatus().equals(BatchStatusEnum.PASS.status()), "迭代失败,制程检测数据不存在或无法迭代");

        newValue.setBatchNumber(oldValue.getBatchNumber());

        Assert.notEmpty(procedureTestRecordDTOs, "迭代内容不能为空");

        //把old数据设置为迭代数据
        List<ProcedureTestRecord> lastIterations = procedureTestRecordMapper.findByBatchNumberId(lastIterationBatchId);
        Assert.notEmpty(lastIterations, "迭代数据不存在");

        procedureTestRecordMapper.updateIteration(lastIterations);

        //新增此数据
        this.add(procedureBatchNumber);

        return procedureBatchNumber;
    }


    /**
     * 根据批号id查询
     *
     * @param batchNumberId
     * @return
     */
    @Override
    public CommonBatchNumberDTO findByBatchNumberId(Integer batchNumberId) {
        CommonBatchNumberDTO commonBatchNumberDTO = commonBatchNumberMapper.createDTOByIdAndDataType(batchNumberId, BatchTypeEnum.QUALITY_PRODUCT.typeCode());

        if (commonBatchNumberDTO == null) return null;

        List<ProcedureTestRecord> procedureTestRecords = procedureTestRecordMapper.findByBatchNumberId(batchNumberId);

        commonBatchNumberDTO.setDetails(this.convert2DTO(procedureTestRecords));

        return commonBatchNumberDTO;
    }

    /**
     * 根据批号id删除
     *
     * @param batchNumberId
     */
    @Override
    @Transactional
    public void deleteByBatchNumberId(Integer batchNumberId) {

        CommonBatchNumber byId = commonBatchNumberMapper.byId(batchNumberId);

        Assert.isTrue(null != byId && byId.getDataType().equals(BatchTypeEnum.QUALITY_PRODUCT.typeCode()), "删除失败,制程检测数据不存在");

        //已送审的信息不能删除
        Assert.isNull(dataTaskRecordMapper.findByDataBatchNumberId(batchNumberId), "已送审的信息不能删除");

        //删除检测项目
        procedureTestRecordMapper.deleteOneTestItemRecord(batchNumberId);

        //删除制程检测信息
        procedureTestRecordMapper.deleteByBatchNumberId(batchNumberId);

        //删除批号信息
        commonBatchNumberMapper.deleteById(batchNumberId);
    }

    /**
     * 根据批号ids删除
     *
     * @param batchNumberIds
     */
    @Override
    @Transactional
    public void deleteByBatchNumberIds(List<Integer> batchNumberIds) {
        //已送审的信息不能删除
        List<DataTaskRecord> records = dataTaskRecordMapper.findByDataBatchNumberIds(batchNumberIds);

        Assert.isTrue(null == records || 0 == records.size(), "已送审的信息不能删除");

        for (int i = 0; i < batchNumberIds.size(); i++) {
            Integer id = batchNumberIds.get(i);

            CommonBatchNumber byId = commonBatchNumberMapper.byId(id);

            Assert.isTrue(null != byId && byId.getDataType().equals(BatchTypeEnum.QUALITY_PRODUCT.typeCode()), "删除失败,制程检测数据不存在");
        }

        //删除检测项目
        procedureTestRecordMapper.deleteTestItemRecord(batchNumberIds);

        //参考单项删除 同上
        procedureTestRecordMapper.deleteByBatchNumberIds(batchNumberIds);

        commonBatchNumberMapper.deleteByIds(batchNumberIds);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<CommonBatchNumberDTO> findAll() {
        return commonBatchNumberMapper.createDTOsByCommonDTO(new CommonBatchNumberDTO().
                setCommonBatchNumber(new CommonBatchNumber().setDataType(BatchTypeEnum.QUALITY_PRODUCT.typeCode())));
    }

    /**
     * 创建人名称模糊查询所有-分页
     *
     * @param personName
     * @param page
     * @param size
     * @param fieldName
     * @param orderType
     * @return
     */
    @Override
    public PageInfo findAllByPage(String personName, Integer page, Integer size, String fieldName, String orderType) {
        String orderBy = fieldName + " " + orderType;
        PageHelper.startPage(page, size, orderBy);
        List<CommonBatchNumberDTO> commonBatchNumberDTOS = commonBatchNumberMapper
                .createDTOsByCommonDTO(new CommonBatchNumberDTO().setCreatePersonName(personName)
                        .setCommonBatchNumber(new CommonBatchNumber().setDataType(BatchTypeEnum.QUALITY_PRODUCT.typeCode())));
        PageInfo<CommonBatchNumberDTO> pageInfo = new PageInfo<>(commonBatchNumberDTOS);
        return pageInfo;
    }

    /**
     * 转换成DTO
     */
    public List<ProcedureTestRecordDTO> convert2DTO(List<ProcedureTestRecord> procedureTestRecords) {
        if (procedureTestRecords == null || procedureTestRecords.size() == 0) return null;
        List<ProcedureTestRecordDTO> procedureTestRecordDTOS = new ArrayList<>();

        for (ProcedureTestRecord procedureTestRecord : procedureTestRecords) {
            ProcedureTestRecordDTO procedureTestRecordDTO = new ProcedureTestRecordDTO();


            //设置检测项目名称
            List<ProcedureTestItemRecord> testItemRecords = procedureTestRecordMapper.testItemsOfProcedureTest(procedureTestRecord.getId());

            Assert.notEmpty(testItemRecords, "数据库错误,制程检测的检测项目不存在,请删除此数据");

            StringBuffer testItemRecordString = new StringBuffer();
            for (ProcedureTestItemRecord record : testItemRecords) {
                TestItem testItem = testItemMapper.find(record.getTestItemId());
                testItemRecordString.append(testItem == null ? "" : testItem.getName() + ",");
            }
            testItemRecordString.deleteCharAt(testItemRecordString.length() - 1);

            //构建DTO
            //QualityBaseDetectItem detectItem = detectItemMapper.selectByPrimaryKey(procedureTestRecord.getSerialNumberId().longValue());
            RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(procedureTestRecord.getSerialNumberId());
            procedureTestRecordDTO
                    .setProcedureTestRecord(procedureTestRecord)                                               //详情
                    .setTester(authRoleMapper.findById(procedureTestRecord.getTester()).getDescription())      //角色名
                    .setSampler(authRoleMapper.findById(procedureTestRecord.getSampler()).getDescription())    //角色名
                    .setProductionProcess(productionProcessMapper.findById(procedureTestRecord.getProcedureId())) //工序
                    .setDeliveryFactory(deliveryFactoryMapper.findById(procedureTestRecord.getDeliveryFactoryId())) //送样工厂
                    .setTestMaterialName(repoBaseSerialNumber==null?"未知名称":repoBaseSerialNumber.getMaterialName())  //物料名称
                    //.setTestMaterialName(detectItem == null?"未知名称":detectItem.getName())
                    .setTestItemString(testItemRecordString.toString());   //检测项目名


            procedureTestRecordDTOS.add(procedureTestRecordDTO);
        }

        return procedureTestRecordDTOS;
    }


    /**
     * 新增详情
     */
    @Transactional
    public void addDetails(List<ProcedureTestRecordDTO> detailsDTOS, Integer batchNumberId) {

        Assert.notEmpty(detailsDTOS, "制程检测信息不存在");

        StringBuilder builder = new StringBuilder();
        List<String> uniqueSamplePoint = new ArrayList<>();
        for (ProcedureTestRecordDTO detailDTO : detailsDTOS) {
            //查看制程检测表
            ProcedureTestRecord pr = detailDTO.getProcedureTestRecord();
            Assert.notNull(pr, "制程检测详情不存在");
            builder.append("-")
                    .append(pr.getDeliveryFactoryId())
                    .append(",")
                    .append(pr.getProcedureId())
                    .append(",")
                    .append(pr.getSamplePointName())
                    .append(",")
                    .append(pr.getSerialNumberId());

            uniqueSamplePoint.add(pr.getSamplePointName()+"-"+pr.getSerialNumberId());

            //查看检测项目
            List<Integer> listIds = detailDTO.getTestItemIds();
            logger.info("检测项目{}", listIds);
            Set<Integer> setIds = new HashSet<>();
            listIds.forEach(e -> setIds.add(e));
            Assert.notEmpty(setIds, "检测项目不能为空");

            //验证检测项目是否存在
            for (Integer id : setIds) {
                TestItem dataItem = testItemMapper.find(id);
                //QualityBaseDetectItem dataItem = detectItemMapper.selectByPrimaryKey((long)id);
                Assert.notNull(dataItem, String.format("找不到id为%d的检测项目", id));
            }

            //设置批号id
            pr.setBatchNumberId(batchNumberId);
            //设置为非迭代数据
            pr.setIsIteration(0);

            //验证工序
            Assert.notNull(productionProcessMapper.findById(pr.getProcedureId()), String.format("Id为%d的工序不存在", pr.getProcedureId()));
            //验证送货工厂
            Assert.notNull(deliveryFactoryMapper.findById(pr.getDeliveryFactoryId()), String.format("Id为%d的工厂不存在", pr.getDeliveryFactoryId()));
            //验证受检物料
            RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(pr.getSerialNumberId());
            Assert.notNull(repoBaseSerialNumber, "不存在该物料编号");
            Assert.isTrue(repoBaseSerialNumber.getMaterialClass().equals(QualitySampleTypeEnum.SAMPLE_INTERMEDIATE.get()), "该物料不是中间品");
           // QualityBaseDetectItem detectItem = detectItemMapper.selectByPrimaryKey((long)pr.getSerialNumberId());
            //Assert.notNull(detectItem, "不存在该物料编号");
            //验证取样人
            Assert.notNull(authRoleMapper.findById(pr.getSampler()), "送样人不存在");
            //验证检测人
            Assert.notNull(authRoleMapper.findById(pr.getTester()), "检测人不存在");

            //先新增制程检测
            procedureTestRecordMapper.insert(pr);

            //新增检测项目
            List<ProcedureTestItemRecord> itemRecords = new ArrayList<>();

            setIds.forEach(e -> {
                ProcedureTestItemRecord itemRecord = new ProcedureTestItemRecord();
                itemRecord.setTestItemId(e);
                itemRecord.setProcedureTestRecordId(pr.getId());
                itemRecords.add(itemRecord);
            });

            procedureTestRecordMapper.insertAllTestItems(itemRecords);
        }

        String isUnique = builder.deleteCharAt(0).toString();
        logger.info("制程检测录入值:{}", isUnique);
        for (String s : isUnique.split("-")) {
            String[] split = s.split(",");
            Assert.isTrue(isUnique.indexOf(s) == isUnique.lastIndexOf(s), String.format("存在工厂{%s},工序{%s},取样点{%s},受检物料{%s}全部一致的重复数据", split[0], split[1], split[2], split[3]));
        }

        int[] ids = new int[uniqueSamplePoint.size()];
        String[] samplePoint = new String[uniqueSamplePoint.size()];

        for(int i = 0;i<uniqueSamplePoint.size();i++){
            String[] split = uniqueSamplePoint.get(i).split("-");
            samplePoint[i] = split[0];
            ids[i] = Integer.valueOf(split[1]);
        }

        for (int i = 0; i < ids.length ; i++) {
            for (int j = i+1; j < ids.length ; j++) {
                Assert.isTrue(!(ids[i]==ids[j]&&samplePoint[i].equals(samplePoint[j])),String.format("取样点{%s},受检物料{%s}为一一对应关系",ids[i],samplePoint[i]));
            }
        }
    }


    /**
     * 所有返回的数据都是审核通过的最新迭代数据
     * <p>
     * 全为空
     * 返回所有送样工厂
     * <p>
     * 只有送样工厂不为空
     * 返回制程检测送样工厂下的所有工序
     * <p>
     * 工厂和工序不为空
     * 返回 工厂工序下的所有取样点
     * <p>
     * 工厂工序取样点不为空
     * 返回 其下所有受检物料
     * <p>
     * 全为非空
     * 则返回 检测项目集合
     *
     * @param deliveryId
     * @param procedureId
     * @return
     */
    @Override
    public Object queryTestItems(Integer deliveryId, Integer procedureId, String samplePointName) {

        List<ProcedureTestRecord> procedureTestRecords = procedureTestRecordMapper.isUnique(deliveryId, procedureId, samplePointName, null, 0, BatchStatusEnum.PASS.status(), null);

        if (null == procedureTestRecords || 0 == procedureTestRecords.size()) return null;

        //全为空则返回所有的工厂
        if (null == deliveryId && null == procedureId && null == samplePointName) {
            Set<Object> productLines = new HashSet<>();
            for (ProcedureTestRecord procedureTestRecord : procedureTestRecords) {

                Integer testDeliveryId = procedureTestRecord.getDeliveryFactoryId();

                productLines.add(deliveryFactoryMapper.findById(testDeliveryId));
            }
            return productLines;

            //第一项不为空则返回工厂下的工序
        } else if (null == procedureId && null == samplePointName) {
            Set<Object> processes = new HashSet<>();

            for (ProcedureTestRecord procedureTestRecord : procedureTestRecords) {

                Integer testProcedureId = procedureTestRecord.getProcedureId();

                processes.add(productionProcessMapper.findById(testProcedureId));
            }
            return processes;

            //前两项不为空则返回工厂工序下的取样点
        } else if (null == samplePointName) {
            Set<Object> samplePointNames = new HashSet<>();

            for (ProcedureTestRecord procedureTestRecord : procedureTestRecords) {

                String testPointName = procedureTestRecord.getSamplePointName();
                logger.info("=>取样点名称为" + testPointName);

                samplePointNames.add(testPointName);
            }

            return samplePointNames;

            //前三项不为空则返回 对应的受检物料和检测项目的ids和检测频率
        } else {
            List<RepoBaseSerialNumber> materials = new ArrayList<>();
            //List<QualityBaseDetectItem> materials = new ArrayList<>();
            for (ProcedureTestRecord procedureTestRecord : procedureTestRecords) {

                Integer testMaterialId = procedureTestRecord.getSerialNumberId();

                materials.add(repoBaseSerialNumberMapper.findById(testMaterialId));
                //materials.add(detectItemMapper.selectByPrimaryKey(testMaterialId.longValue()));
            }
            logger.info("受检物料数量:"+materials.size());
            RepoBaseSerialNumber sn = materials.size()>0?materials.get(0):null;
            //QualityBaseDetectItem sn = materials.get(0);
            //List<RepoBaseSerialNumber> sn = materials;
            Map<Object,Object> map = new HashMap<>();

            ProcedureTestRecord procedureTestRecord = procedureTestRecords.get(0);
            Integer id = procedureTestRecord.getId();

            map.put("物料",sn);
            map.put("检测频率",procedureTestRecord.getTestFrequency());
            map.put("检测项目","");

            List<ProcedureTestItemRecord> itemRecords = procedureTestRecordMapper.testItemsOfProcedureTest(id);
            if (null == itemRecords || 0 == itemRecords.size()) return map;

            Set<Object> testItemIds = new HashSet<>();

            for (ProcedureTestItemRecord procedureTestItemRecord : itemRecords) {

                Integer itemId = procedureTestItemRecord.getTestItemId();

                testItemIds.add(itemId);
            }

            map.put("检测项目",testItemIds);
            return map;

        }
    }


    /**
     * 审核通过后  将之前的数据设置为过期
     */
    public void setIteration(Integer batchNumberId) {
        List<ProcedureTestRecord> testRecords = procedureTestRecordMapper.findByBatchNumberId(batchNumberId);

        testRecords.forEach(e -> {
            List<ProcedureTestRecord> unique = procedureTestRecordMapper.isUnique(e.getDeliveryFactoryId(),
                    e.getProcedureId(),
                    e.getSamplePointName(),
                    e.getSerialNumberId(),
                    e.getIsIteration(),
                    BatchStatusEnum.PASS.status(),
                    e.getId());

            if (unique != null && !unique.isEmpty()) procedureTestRecordMapper.updateIteration(unique);
        });
    }
}
