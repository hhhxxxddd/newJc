package com.jinchi.common.service;

import com.jinchi.common.constant.BatchStatusEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.constant.QualitySampleTypeEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.NumberGenerator;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 说明:不合格品追踪
 *
 * @author Huxudong
 * 日期: 2018/11/19
 * <br>
 * 版本: 1.0
 */
@Service
public class UnqualifiedTracingRecordServiceImp implements UnqualifiedTracingRecordService {
    private static final Integer BATCH_UNQUALIFIED_TRACING = BatchTypeEnum.UNQUALIFIED_TRACING.typeCode();
    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    private UnqualifiedTestReportRecordMapper unqualifiedTestReportRecordMapper;
    @Autowired
    private UnqualifiedTracingRecordMapper unqualifiedTracingRecordMapper;
    @Autowired
    private SampleDeliveringRecordMapper sampleDeliveringRecordMapper;
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    private UnqualifiedTestItemResultRecordMapper unqualifiedTestItemResultRecordMapper;
    @Autowired
    private UnqualifiedTracingTestRecordMapper unqualifiedTracingTestRecordMapper;
    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private ProductionProcessMapper productionProcessMapper;
    @Autowired
    private TechniqueProductStandardRecordService techniqueProductStandardRecordService;
    @Autowired
    private TechniqueRawStandardRecordService techniqueRawStandardRecordService;

    /**
     * 新增不合格品跟踪
     *
     * @param batchNumberId 批号id
     */
    @Override
    @Transactional
    public void generate(Integer batchNumberId, Integer userId) {
        UnqualifiedTracingRecord unqualifiedTracingRecord = new UnqualifiedTracingRecord();
        CommonBatchNumber commonBatchNumber = new CommonBatchNumber();
        //==>验证
        List<UnqualifiedTestReportRecord> unqualifiedTestReportRecords = unqualifiedTestReportRecordMapper.findByBatchNumberId(batchNumberId);
        Assert.notEmpty(unqualifiedTestReportRecords, String.format("%d该id批号下没有不合格评审数据", batchNumberId));

        boolean flag = true;
        for(UnqualifiedTestReportRecord unqualifiedTestReportRecord:unqualifiedTestReportRecords){
            if(unqualifiedTestReportRecord.getDecision().equals(0)
                    ||unqualifiedTestReportRecord.getIsQualified().equals(0)){
                flag = false;
            }
        }
        if(flag) return;
        //新增批号
        commonBatchNumber
                .setDataType(BatchTypeEnum.UNQUALIFIED_TRACING.typeCode())
                .setCreateTime(new Date())
                .setBatchNumber(NumberGenerator.batchNumberGenerator(BATCH_UNQUALIFIED_TRACING))
                .setStatus(BatchStatusEnum.SAVE.status())
                .setDescription("不合格跟踪表数据")
                .setCreatePersonId(userId);
        commonBatchNumberMapper.insert(commonBatchNumber);
        //GET=> 不合格跟踪的批号id
        Integer commonBatchNumberId = commonBatchNumber.getId();
        //新增不合格跟踪    初始处理人
        unqualifiedTracingRecord
                .setCreateTime(new Date())
                .setBatchNumberId(commonBatchNumberId)
                .setHandler(userId)
                .setProductionProcessId(null);

        //首先新增主表
        unqualifiedTracingRecordMapper.insert(unqualifiedTracingRecord);
        Integer tracingRecordId = unqualifiedTracingRecord.getId();
        //如果是进货检测,势必存在多条不合格评审记录
        List<UnqualifiedTracingTestRecord> testRecords = new ArrayList<>();

        unqualifiedTestReportRecords.stream().forEach(
                e -> testRecords.add(new UnqualifiedTracingTestRecord()
                        .setUnqualifiedTestReportRecordId(e.getId())
                        .setUnqualifiedTracingRecordId(tracingRecordId)));


        unqualifiedTracingTestRecordMapper.batchSave(testRecords);

    }

    /**
     * 查看详情
     *
     * @param batchNumberId 批号id
     * @return
     */
    @Override
    public CommonBatchNumberDTO byBatchNumberId(Integer batchNumberId) {
        CommonBatchNumberDTO bnDTO = commonBatchNumberMapper.createDTOByIdAndDataType(batchNumberId, null);
        if (null == bnDTO) return null;

        UnqualifiedTracingRecord unTracing = unqualifiedTracingRecordMapper.findByBatchNumberId(batchNumberId);
        Integer unTracingId = unTracing.getId();

        List<UnqualifiedTracingTestRecord> testRecords = unqualifiedTracingTestRecordMapper.byTraceId(unTracingId);

        Map<Object, Object> bigMap = new HashMap<>();
        Map<Object, Object> dataMap = new HashMap<>();
        bigMap.put("standard", null);
        bigMap.put("unqualifiedData", dataMap);
        bigMap.put("unqualifiedHead", this.convert2DTO(bnDTO));
        //==>key 是不合格追踪id 值是编号
        List<Integer> testItemIds = new ArrayList<>();
        Map<Integer, String> unRecordMap = new HashMap<>();
        RepoBaseSerialNumber repoBaseSerialNumber = null;
        for (int i = 0; i < testRecords.size(); i++) {
            UnqualifiedTracingTestRecord testRecord = testRecords.get(i);
            UnqualifiedTestReportRecord record = unqualifiedTestReportRecordMapper.findById(testRecord.getUnqualifiedTestReportRecordId());
            Integer sampleDeliveringRecordId = record.getSampleDeliveringRecordId();
            SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(sampleDeliveringRecordId);
            if (record.getDecision() != 1) {
                Integer serialNumberId = sampleDeliveringRecord.getSerialNumberId();
                repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(serialNumberId);
                unRecordMap.put(record.getId(), repoBaseSerialNumber.getSerialNumber());
            }
            if (i < 1) {
                for (String stringId : sampleDeliveringRecord.getTestItems().replaceAll("[^0-9]", ",").split(",")) {
                    if (stringId.length() > 0) {
                        testItemIds.add(Integer.parseInt(stringId));
                    }
                }
            }
        }
        Assert.notEmpty(unRecordMap, "数据库数据错误,此不合格追踪不存在不合格数据");
        for (Integer unRecordId : unRecordMap.keySet()) {
            List<UnqualifiedTestItemResultRecord> unResults = unqualifiedTestItemResultRecordMapper.getByUnqualifiedRecordId(unRecordId);
            unResults.sort(Comparator.comparing(UnqualifiedTestItemResultRecord::getTestItemId));
            dataMap.put(unRecordMap.get(unRecordId), unResults);
        }
        //查找标准
        List<String> standard = repoBaseSerialNumber.getMaterialClass()
                .equals(QualitySampleTypeEnum.SAMPLE_ENDPRODUCT.get())
                ? techniqueProductStandardRecordService.lastStandard(repoBaseSerialNumber.getId(), testItemIds)
                : techniqueRawStandardRecordService.lastStandard(repoBaseSerialNumber.getId(), null, null);

        bigMap.put("standard", standard);

        bnDTO.setDetails(bigMap);

        return bnDTO;
    }


    /**
     * 更新
     *
     * @param batchNumberDTO
     * @return
     */
    @Override
    @Transactional
    public CommonBatchNumberDTO update(CommonBatchNumberDTO<UnqualifiedTracingRecord> batchNumberDTO) {
        //批号id
        CommonBatchNumber newValue = batchNumberDTO.getCommonBatchNumber();
        Integer bnId = newValue.getId();

        CommonBatchNumber oldValue = commonBatchNumberMapper.byId(bnId);
        Assert.isTrue(oldValue != null && BATCH_UNQUALIFIED_TRACING.equals(oldValue.getDataType()), "更新失败,不存在此不合格跟踪表");


        //找到主表
        UnqualifiedTracingRecord tracingRecord = unqualifiedTracingRecordMapper.findByBatchNumberId(bnId);
        Assert.notNull(tracingRecord, String.format("数据库数据错误,id{%d}不存在该批号的不合格追踪表数据", bnId));

        //更新主表
        UnqualifiedTracingRecord details = batchNumberDTO.getDetails();

        Date createTime = details.getCreateTime();
        Integer productionProcessId = details.getProductionProcessId();
        Integer handler = details.getHandler();

        Assert.notNull(authUserMapper.byId(handler), String.format("id{%d}负责人不存在", handler));
        Assert.notNull(productionProcessMapper.findById(productionProcessId), "该工序不存在");

        tracingRecord
                .setHandler(handler)
                .setProductionProcessId(productionProcessId)
                .setCreateTime(createTime);

        unqualifiedTracingRecordMapper.update(tracingRecord);

        return batchNumberDTO;
    }


    /**
     * 查询所有-分页
     *
     * @param personName
     * @param pageBean
     * @return
     */
    @Override
    public PageBean byPage(String personName, PageBean pageBean) {

        CommonBatchNumberDTO commonBatchNumberDTO = new CommonBatchNumberDTO()
                .setCreatePersonName(personName)
                .setCommonBatchNumber(new CommonBatchNumber().setDataType(BatchTypeEnum.UNQUALIFIED_TRACING.typeCode()));

        List<CommonBatchNumberDTO> batchNumbers = commonBatchNumberMapper.createDTOsByCommonDTObyPage(commonBatchNumberDTO, pageBean);
        if (null == batchNumbers || 0 == batchNumbers.size()) return null;

        batchNumbers.stream().forEach(e -> this.convert2DTO(e));

        pageBean.setList(batchNumbers);
        pageBean.setTotal(commonBatchNumberMapper.countSum(commonBatchNumberDTO));

        return pageBean;
    }


    /**
     * DTOs生成
     *
     * @param batchNumberDTO 批号
     */
    public Map<Object, Object> convert2DTO(CommonBatchNumberDTO batchNumberDTO) {
        Integer bnId = batchNumberDTO.getCommonBatchNumber().getId();

        UnqualifiedTracingRecord tracingRecord = unqualifiedTracingRecordMapper.findByBatchNumberId(bnId);
        List<UnqualifiedTracingTestRecord> traceResults = unqualifiedTracingTestRecordMapper.byTraceId(tracingRecord.getId());
        Integer unqualifiedTestReportRecordId = traceResults.get(0).getUnqualifiedTestReportRecordId();

        SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(unqualifiedTestReportRecordMapper.findById(unqualifiedTestReportRecordId).getSampleDeliveringRecordId());

        Integer serialNumberId = sampleDeliveringRecord.getSerialNumberId();
        RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(serialNumberId);

        //拿到材料名
        String materialName = repoBaseSerialNumber.getMaterialName();
        //工序
        ProductionProcess process = productionProcessMapper.findById(tracingRecord.getProductionProcessId());
        //处理人
        AuthUserDTO userDTO = authUserMapper.byId(tracingRecord.getHandler());
        String name = userDTO == null ? "" : userDTO.getName();
        //创建时间
        Map<Object, Object> head = new HashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateFormat = sdf.format(tracingRecord.getCreateTime());

        head.put("materialName", materialName);
        head.put("process", process);
        head.put("handle", name);
        head.put("createTime", dateFormat);

        batchNumberDTO.setDetails(head);

        return head;
    }

}
