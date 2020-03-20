package com.jinchi.common.service;


import com.jinchi.common.constant.BatchStatusEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.constant.QualitySampleStatusEnum;
import com.jinchi.common.constant.QualitySampleTypeEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.SampleDeliveringRecordDTO;
import com.jinchi.common.mapper.*;
import com.jinchi.common.model.factorypattern.CommonBatchFactory;
import com.jinchi.common.utils.NumberGenerator;
import com.jinchi.common.utils.TestItemUtil;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 * modified:huxudong
 */

@Service
public class SampleDeliveringRecordServiceImp implements SampleDeliveringRecordService {
    @Autowired
    private TestItemMapper testItemMapper;
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    private DeliveryFactoryMapper deliveryFactoryMapper;
    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private SampleDeliveringRecordMapper sampleDeliveringRecordMapper;
    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    private TestReportRecordMapper testReportRecordMapper;
    @Autowired
    private TestItemResultRecordMapper testItemResultRecordMapper;
    @Autowired
    private TechniqueBaseRawMaterialMapper techniqueBaseRawMaterialMapper;
    @Autowired
    private TechniqueRawItemRecordMapper techniqueRawItemRecordMapper;
    @Autowired
    private TestItemUtil testItemUtil;
    @Autowired
    private QualityCommonBatchNumberExtraMapper qualityCommonBatchNumberExtraMapper;
    @Autowired
    private QualitySampleDeliveringRecordRawMapper qualitySampleDeliveringRecordRawMapper;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 新增样品送检
     *
     * @param sampleDeliveringRecordDTO
     * @return
     */
    @Override
    @Transactional
    public SampleDeliveringRecordDTO add(SampleDeliveringRecordDTO sampleDeliveringRecordDTO) {
        //取出检测项目详情
        SampleDeliveringRecord sampleDeliveringRecord = this.convert2Data(sampleDeliveringRecordDTO);
        //新增
        Assert.isTrue(sampleDeliveringRecord.getAcceptStatus().equals(QualitySampleStatusEnum.SAMPLE_SAVE.get()) || sampleDeliveringRecord.getAcceptStatus().equals(QualitySampleStatusEnum.SAMPLE_WAIT.get()),
                "请选择保存还是提交");

        //提交才生成批号;
        Integer dataType = BatchTypeEnum.getSampleTypeByMaterialType(sampleDeliveringRecord.getType()).typeCode();
        String tempBatchNumber = NumberGenerator.batchNumberGenerator(dataType);
        sampleDeliveringRecord.setTempBatchNumber(tempBatchNumber);
        sampleDeliveringRecordMapper.insert(sampleDeliveringRecord);

        QualityCommonBatchNumberExtra qualityCommonBatchNumberExtra = new QualityCommonBatchNumberExtra();
        qualityCommonBatchNumberExtra.setCommonBatchId(sampleDeliveringRecord.getId());
        qualityCommonBatchNumberExtra.setBatch(sampleDeliveringRecordDTO.getBatch());
        qualityCommonBatchNumberExtraMapper.insertSelective(qualityCommonBatchNumberExtra);

        QualitySampleDeliveringRecordRaw qualitySampleDeliveringRecordRaw = new QualitySampleDeliveringRecordRaw();
        qualitySampleDeliveringRecordRaw.setSampleId(sampleDeliveringRecord.getId());
        qualitySampleDeliveringRecordRaw.setStandardId(sampleDeliveringRecordDTO.getStandradId());
        qualitySampleDeliveringRecordRawMapper.insertSelective(qualitySampleDeliveringRecordRaw);

        return sampleDeliveringRecordDTO;
    }

    //自定义样品送检
    @Override
    @Transactional
    public String customSample(List<Integer> testItemIds,Integer userId,String batch){
        SampleDeliveringRecordDTO deliveringRecordDTO = new SampleDeliveringRecordDTO();

        //自定义一个物料
        RepoBaseSerialNumber customItem = new RepoBaseSerialNumber();
        customItem
                .setMaterialClass(2)
                .setManufacturerName("前驱体工厂")
                .setSerialNumber(NumberGenerator.serialNumberGenerator(2))
                .setMaterialName("自定义物料");
        repoBaseSerialNumberMapper.insert(customItem);

        SampleDeliveringRecord sampleDeliveringRecord = new SampleDeliveringRecord();

        DeliveryFactory newValue = new DeliveryFactory();
        newValue.setName("无来源");
        DeliveryFactory oldValue = deliveryFactoryMapper.byFullName("无来源");
        if(oldValue==null) deliveryFactoryMapper.insert(newValue);

        sampleDeliveringRecord
                .setTempBatchNumber(NumberGenerator.batchNumberGenerator(BatchTypeEnum.QUALITY_INTERMEDIATE_TEST.typeCode()))
                .setType(QualitySampleTypeEnum.SAMPLE_INTERMEDIATE.get())
                .setDelivererId(userId)
                .setSampleDeliveringDate(new Date())
                .setDeliveryFactoryId(oldValue==null?newValue.getId():oldValue.getId())
                .setSerialNumberId(customItem.getId())
                .setAcceptStatus(QualitySampleStatusEnum.SAMPLE_WAIT.get());
        deliveringRecordDTO.setTestItemIds(testItemIds)
                .setSampleDeliveringRecord(sampleDeliveringRecord)
                .setBatch(batch);

        SampleDeliveringRecordDTO add = this.add(deliveringRecordDTO);

        return sampleDeliveringRecord.getTempBatchNumber();
    }
    /**
     * 更新样品送检
     *
     * @param sampleDeliveringRecordDTO
     * @return
     */
    @Override
    public SampleDeliveringRecordDTO update(SampleDeliveringRecordDTO sampleDeliveringRecordDTO) {

        SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordDTO.getSampleDeliveringRecord();

        Integer deliveringRecordId = sampleDeliveringRecord.getId();

        Assert.notNull(deliveringRecordId, "更新id不能为空");

        SampleDeliveringRecord oldValue = sampleDeliveringRecordMapper.getByIdAndTypeAndAccept(deliveringRecordId, null, null);

        Assert.notNull(oldValue, "更新失败,找不到此样品送检");

        Assert.isTrue(oldValue.getAcceptStatus().equals(BatchStatusEnum.SAVE.status()), "非保存数据不可编辑");

        //将DTO转换成类
        SampleDeliveringRecord newValue = this.convert2Data(sampleDeliveringRecordDTO);

        sampleDeliveringRecordMapper.update(newValue);

        return sampleDeliveringRecordDTO;
    }

    /**
     * 根据id查询样品送检
     *
     * @param id
     * @return
     */
    @Override
    public SampleDeliveringRecordDTO findById(Integer id) {
        SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getByIdAndTypeAndAccept(id, null, null);
        if (null == sampleDeliveringRecord) return null;
        return convert2DTO(sampleDeliveringRecord);
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(id);

        Assert.notNull(sampleDeliveringRecord, "删除失败,不存在该样品送检数据");

        Assert.isTrue(sampleDeliveringRecord.getAcceptStatus().equals(BatchStatusEnum.SAVE.status()), "非保存数据不可删除");

        sampleDeliveringRecordMapper.deleteById(id);
    }

    /**
     * 根据ids删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(List<Integer> ids) {

        for (int i = 0; i < ids.size(); i++) {

            int id = ids.get(i);

            SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(id);

            Assert.isTrue(sampleDeliveringRecord.getAcceptStatus().equals(BatchStatusEnum.SAVE.status()), "仅能删除保存数据");
        }

        sampleDeliveringRecordMapper.deleteByIds(ids);

    }


    /**
     * 查询所有
     *
     * @param acceptStatus 接收状态 NN
     * @param materialType 物料类型 NN
     * @return
     */
    @Override
    public List<SampleDeliveringRecordDTO> findAllByAcceptAndType(Integer acceptStatus, Integer materialType) {
        //查询所有
        List<SampleDeliveringRecord> sampleDeliveringRecords = sampleDeliveringRecordMapper.getAllByFactors(materialType, acceptStatus, null, null);

        if (null == sampleDeliveringRecords || 0 == sampleDeliveringRecords.size()) return null;

        List<SampleDeliveringRecordDTO> sampleDeliveringRecordDTOS = new ArrayList<>();

        for (SampleDeliveringRecord sampleDeliveringRecord : sampleDeliveringRecords) {
            sampleDeliveringRecordDTOS.add(this.convert2DTO(sampleDeliveringRecord));
        }

        return sampleDeliveringRecordDTOS;
    }

    /**
     * 查询所有-分页
     *
     * @param factoryName 送检工厂名称
     * @return
     */
    @Override
    public PageBean<SampleDeliveringRecordDTO> findByFactoryNameByPage(String factoryName, PageBean pageBean) {

        List<SampleDeliveringRecord> sampleDeliveringRecords = sampleDeliveringRecordMapper.getAllByFactors(null, null, factoryName, pageBean);

        if (null == sampleDeliveringRecords || 0 == sampleDeliveringRecords.size()) return null;

        List<SampleDeliveringRecordDTO> sampleDeliveringRecordDTOS = new ArrayList<>();

        sampleDeliveringRecords.stream().forEach(e -> {
            sampleDeliveringRecordDTOS.add(this.convert2DTO(e));
        });

        pageBean.setList(sampleDeliveringRecordDTOS);

        pageBean.setTotal(sampleDeliveringRecordMapper.countSum(null, null, factoryName));

        return pageBean;
    }

    /**
     * 拒绝或者接受一个样品送检
     *
     * @param id
     * @param isAccept
     * @param handleComment
     */
    @Override
    @Transactional
    public String isAccept(Integer id, Integer isAccept, String handleComment) {
        //是否接受
        Assert.isTrue(isAccept.equals(QualitySampleStatusEnum.SAMPLE_DENY.get()) || isAccept.equals(QualitySampleStatusEnum.SAMPLE_ACCEPTED.get()), "请选择是否接受");

        SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(id);

        Assert.notNull(sampleDeliveringRecord, "接受失败,不存在此样品送检数据");

        sampleDeliveringRecord.setAcceptStatus(isAccept);

        //是否接受样品送检
        String returnMessage = "已拒绝样品送检";
        if (isAccept.equals(QualitySampleStatusEnum.SAMPLE_DENY.get())) {
            Assert.isTrue(!handleComment.isEmpty(), "拒绝请填写理由");
            sampleDeliveringRecord.setHandleComment(handleComment);
            sampleDeliveringRecordMapper.update(sampleDeliveringRecord);
            return returnMessage;
        }


        sampleDeliveringRecord.setHandleComment(null);
        //GET==>物料类型
        Integer materialType = sampleDeliveringRecord.getType();
        //GET==>批号DataType
        Integer dataType = BatchTypeEnum.getSampleTypeByMaterialType(materialType).typeCode();
        //GET==>检测项目ids
        String testItemIdsString = sampleDeliveringRecord.getTestItems();
        //GET==>创建人id
        Integer userId = sampleDeliveringRecord.getDelivererId();
        List<Integer> testItemIds = new ArrayList<>();
        //检测项目存储形式为 1,2,3,4,5等 使用List存储
        for (String idString : testItemIdsString.replaceAll("[^0-9]", ",").split(",")) {

            if (idString.length() > 0) testItemIds.add(Integer.parseInt(idString));

        }

        String description = BatchTypeEnum.getSampleTypeByMaterialType(materialType).description();

        CommonBatchNumber commonBatchNumber =
                CommonBatchFactory.initialize()
                        .setCreatePersonId(userId)
                        .setBatchNumber(sampleDeliveringRecord.getTempBatchNumber())
                        .setDataType(dataType)
                        .setDescription(description);

        commonBatchNumberMapper.insert(commonBatchNumber);

        //SET==>样品送检审核记录
        TestReportRecord testReportRecord = new TestReportRecord();
        testReportRecord.setSampleDeliveringRecordId(id);
        testReportRecord.setBatchNumberId(commonBatchNumber.getId());
        testReportRecordMapper.saveOne(testReportRecord);

        //SET==>检测项目记录表
        List<TestItemResultRecord> testItemResultRecords = new ArrayList<>();
        for (int i = 0; i < testItemIds.size(); i++) {
            Integer testItemId = testItemIds.get(i);
            TestItemResultRecord testItemResultRecord = new TestItemResultRecord();

            testItemResultRecord.setTestReportRecordId(testReportRecord.getId());
            testItemResultRecord.setTestItemId(testItemId);

            testItemResultRecords.add(testItemResultRecord);
        }
        testItemResultRecordMapper.batchInsert(testItemResultRecords);

        sampleDeliveringRecordMapper.update(sampleDeliveringRecord);

        returnMessage = String.format("已生成批号为%s的%s数据",commonBatchNumber.getBatchNumber(),description);
        return returnMessage;

}

    /**
     * 将DTO转换成类
     *
     * @param sampleDeliveringRecordDTO
     * @return
     */
    public SampleDeliveringRecord convert2Data(SampleDeliveringRecordDTO sampleDeliveringRecordDTO) {

        SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordDTO.getSampleDeliveringRecord();
        //检测项目验证
        List<Integer> testItemIds = sampleDeliveringRecordDTO.getTestItemIds();

        StringBuffer testItemIdsString = new StringBuffer();

        for (Integer id : testItemIds) {
            TestItem testItem = testItemMapper.find(id);
            Assert.notNull(testItem, "不存在该检测项目id:"+id);
            testItemIdsString.append(testItem.getId() + ",");
        }
        testItemIdsString.deleteCharAt(testItemIdsString.length() - 1);

        /**
         * 详情验证环节
         */
        //送检工厂验证
        DeliveryFactory deliveryFactory = deliveryFactoryMapper.findById(sampleDeliveringRecord.getDeliveryFactoryId());
        Assert.notNull(deliveryFactory, "不存在此送检工厂id:"+sampleDeliveringRecord.getDeliveryFactoryId());


        //送样人验证
        AuthUserDTO authUserDTO = authUserMapper.byId(sampleDeliveringRecord.getDelivererId());
        Assert.notNull(authUserDTO, "不存在此送样人id:"+sampleDeliveringRecord.getDelivererId());


        //基础编号验证
        Integer serialNumberId = sampleDeliveringRecord.getSerialNumberId();

        Assert.notNull(serialNumberId, "请选择物料");

        RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(serialNumberId);

        Assert.notNull(repoBaseSerialNumber, "不存在此基础编号信息id:"+serialNumberId);

        Assert.isTrue(sampleDeliveringRecord.getType().equals(repoBaseSerialNumber.getMaterialClass()), "物料类型不是所选的原料类型");

        /**
         * 设置环节
         */
        //设置检验项目
        sampleDeliveringRecord.setTestItems(testItemIdsString.toString());
        //设置拒绝理由  新增不存在拒绝
        sampleDeliveringRecord.setHandleComment(null);

        return sampleDeliveringRecord;
    }

    /**
     * 将类转换成DTO
     *
     * @param sampleDeliveringRecord
     * @return
     */
    public SampleDeliveringRecordDTO convert2DTO(SampleDeliveringRecord sampleDeliveringRecord) {
        StringBuffer testIdsString = new StringBuffer(sampleDeliveringRecord.getTestItems());

        SampleDeliveringRecordDTO sampleDeliveringRecordDTO = new SampleDeliveringRecordDTO();

        //拿到检测项目

        String itemNames = testItemUtil.convertItemNames(testIdsString.toString());

        //设置工厂
        DeliveryFactory deliveryFactory = deliveryFactoryMapper.findById(sampleDeliveringRecord.getDeliveryFactoryId());
        //基本编号
        RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(sampleDeliveringRecord.getSerialNumberId());
        //送样人
        AuthUserDTO authUserDTO = authUserMapper.byId(sampleDeliveringRecord.getDelivererId());
        QualityCommonBatchNumberExtraExample example = new QualityCommonBatchNumberExtraExample();
        example.createCriteria().andCommonBatchIdEqualTo(sampleDeliveringRecord.getId());
        List<QualityCommonBatchNumberExtra> qualityCommonBatchNumberExtra = qualityCommonBatchNumberExtraMapper.selectByExample(example);

        sampleDeliveringRecordDTO
                .setDeliverer(authUserDTO)
                .setSerialNumberName(repoBaseSerialNumber==null?"":repoBaseSerialNumber.getSerialNumber())
                .setDeliveryFactory(deliveryFactory)
                .setTestItemString(itemNames)
                .setBatch(qualityCommonBatchNumberExtra.size()==0?"":qualityCommonBatchNumberExtra.get(0).getBatch());

        //设置详情
        sampleDeliveringRecordDTO.setSampleDeliveringRecord(sampleDeliveringRecord);

        return sampleDeliveringRecordDTO;
    }

    /**
     * 根据基本编号id查询标准
     *
     * @param serialNumberId
     * @return
     */
    @Override
    public List<Integer> rawStandards(Integer serialNumberId) {
        RepoBaseSerialNumber serialNumber = repoBaseSerialNumberMapper.findById(serialNumberId);

        String materialName = serialNumber.getMaterialName();


        TechniqueBaseRawMaterial material = techniqueBaseRawMaterialMapper.getByName(materialName);

        Assert.notNull(material, "此物料还没有建立标准,请建立标准后再送检");

        //拿到了物料的id
        Integer materialId = material.getId();

        List<TechniqueRawItemRecord> techniqueRawItemRecords = techniqueRawItemRecordMapper.byRawId(materialId);

        Assert.notEmpty(techniqueRawItemRecords, "此物料还没有建立标准,请建立标准后再送检");

        List<Integer> itemIds = new ArrayList<>();

        techniqueRawItemRecords.forEach(e -> itemIds.add(e.getTestItemId()));

        return itemIds;
    }

    @Override
    public Integer count(String batch) {
        QualityCommonBatchNumberExtraExample example = new QualityCommonBatchNumberExtraExample();
        example.createCriteria().andBatchLike(batch+"%");
        return qualityCommonBatchNumberExtraMapper.countByExample(example);
    }

    @Override
    public Page getPageByBatch(String batch, Integer page, Integer size) {
        ValueOperations<String,SampleDeliveringRecordDTO> op = redisTemplate.opsForValue();
        QualityCommonBatchNumberExtraExample extraExample = new QualityCommonBatchNumberExtraExample();
        extraExample.createCriteria().andBatchLike(batch+"%");
        List<QualityCommonBatchNumberExtra> extras = qualityCommonBatchNumberExtraMapper.selectByExample(extraExample);

        List<SampleDeliveringRecordDTO> sampleDeliveringRecordDTOS = new ArrayList<>();

        List<SampleDeliveringRecord> sampleDeliveringRecords = sampleDeliveringRecordMapper.selectByBatch(batch+"%",(page-1)*size,size);
        //System.out.println(sampleDeliveringRecords);
        sampleDeliveringRecords.stream().forEach(e -> {
            String key = "sample_de_re_"+e.getId();
            if(redisTemplate.hasKey(key)){
                sampleDeliveringRecordDTOS.add(op.get(key));
            }else {
                SampleDeliveringRecordDTO record = this.convert2DTO(e);
                op.set(key,record);
                sampleDeliveringRecordDTOS.add(record);
            }//修改样品送检数据之后显示数据可能会出错，这里未解决--2019-11-13 --已解决
        });
        Page ans = new Page(sampleDeliveringRecordDTOS, 1, size);
        ans.setPage(page);
        ans.setTotal(extras.size());
        return ans;
    }
}
