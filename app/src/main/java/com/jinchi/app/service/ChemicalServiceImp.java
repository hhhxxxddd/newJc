package com.jinchi.app.service;

import com.jinchi.app.domain.*;
import com.jinchi.app.dto.*;
import com.jinchi.app.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChemicalServiceImp implements ChemicalService {
    @Autowired
    TestItemMapper testItemMapper;
    @Autowired
    TestItemResultRecordMapper testItemResultRecordMapper;
    @Autowired
    SampleDeliveringRecordMapper sampleDeliveringRecordMapper;
    @Autowired
    TestReportRecordMapper testReportRecordMapper;
    @Autowired
    QualityCommonBatchNumberExtraMapper qualityCommonBatchNumberExtraMapper;
    @Autowired
    AuthUserService authUserService;
    @Autowired
    RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    DataTaskRecordMapper dataTaskRecordMapper;
    @Autowired
    TaskHandlingRecordMapper taskHandlingRecordMapper;

    @Override
    public List getAll(Integer userId, String condition) {
        return null;
    }

    @Override
    public Page page(Integer userId, String condition, Integer page, Integer size) {
        /*String sql = "select t.* from quality_test_report_record as t";
        if(condition!=null){
            sql += ",quality_common_batch_number_extra as c where t.sample_delivering_record_id = c.common_batch_id and c.batch like '" + condition + "%'";
        }
        //System.out.println(sql);
        sql += " order by t.judge_date desc limit "+(page-1)*size + ","+size;*/
        String sql = "select distinct t.* from quality_test_report_record as t,quality_common_batch_number_extra as c,quality_sample_delivering_record as s";
        sql += " where t.sample_delivering_record_id = s.id";
        if (condition == null) {
            condition = "";
        }
        sql += " and t.sample_delivering_record_id = c.common_batch_id";
        sql += " and c.batch like '" + condition + "%'";
        sql += " order by s.sample_delivering_date desc limit " + (page - 1) * size + "," + size;

        List<TestReportRecord> testReportRecords = testReportRecordMapper.selectByCondition(sql);
        List<ChemicalAppDTO> ans = new ArrayList<>();
        for(int i=0;i<testReportRecords.size();i++){
            TestReportRecord record = testReportRecords.get(i);
            ChemicalAppDTO chemicalAppDTO = new ChemicalAppDTO();
            chemicalAppDTO.setId(record.getId());
            SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(record.getSampleDeliveringRecordId());
            AuthUserDTO authUserDTO = authUserService.findById(sampleDeliveringRecord.getDelivererId());
            chemicalAppDTO.setDeliveryPeople(authUserDTO == null ? "" : authUserDTO.getName());
            chemicalAppDTO.setDeliveryDate(sampleDeliveringRecord.getSampleDeliveringDate());
            QualityCommonBatchNumberExtraExample example = new QualityCommonBatchNumberExtraExample();
            example.createCriteria().andCommonBatchIdEqualTo(record.getSampleDeliveringRecordId());
            List<QualityCommonBatchNumberExtra> extras = qualityCommonBatchNumberExtraMapper.selectByExample(example);
            if(extras.size()!=0){
                chemicalAppDTO.setCommonBatchNumber(extras.get(0).getBatch());
            }
            ans.add(chemicalAppDTO);
        }
        Page pageInfo = new Page(size, 1, ans);
        return pageInfo;
    }

    @Override
    public ChemicalAppDTO detail(Integer id) {
        TestReportRecord record = testReportRecordMapper.getById(id);
        SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(record.getSampleDeliveringRecordId());
        ChemicalAppDTO ans = new ChemicalAppDTO();
        List<TestItemsDTO> testItemsDTOS = new ArrayList<>();
        List<TestItemResultRecord> resultRecords = testItemResultRecordMapper.getByTestReportId(id);
        for(int i=0;i<resultRecords.size();i++){
            TestItemsDTO itemsDTO = new TestItemsDTO();
            itemsDTO.setId(resultRecords.get(i).getId());
            itemsDTO.setCount(resultRecords.get(i).getTestResult());
            TestItem testItem = testItemMapper.find(resultRecords.get(i).getTestItemId());
            itemsDTO.setName(testItem.getName());
            itemsDTO.setUnit(testItem.getUnit());
            testItemsDTOS.add(itemsDTO);
        }
        ans.setTestItems(testItemsDTOS);
        QualityCommonBatchNumberExtraExample example = new QualityCommonBatchNumberExtraExample();
        example.createCriteria().andCommonBatchIdEqualTo(record.getSampleDeliveringRecordId());
        List<QualityCommonBatchNumberExtra> extras = qualityCommonBatchNumberExtraMapper.selectByExample(example);
        if(extras.size()!=0){
            ans.setCommonBatchNumber(extras.get(0).getBatch());
        }
        ans.setIsQuality(record.getIsQualified());
        RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(sampleDeliveringRecord.getSerialNumberId());
        ans.setName(repoBaseSerialNumber.getMaterialName());

        List<AuditDTO> auditDTOs = new ArrayList<>();
        DataTaskRecord dataTaskRecord = dataTaskRecordMapper.findByDataBatchNumberId(record.getBatchNumberId());
        if(dataTaskRecord == null){
            ans.setAudit(auditDTOs);
            ans.setJudgePeole(record.getJudger()==null?"unknown":authUserService.findById(record.getJudger()).getName());
            ans.setJudgeDate(record.getJudgeDate());
            ans.setDeliveryPeople(authUserService.findById(sampleDeliveringRecord.getDelivererId()).getName());
            ans.setDeliveryDate(sampleDeliveringRecord.getSampleDeliveringDate());
            return ans;
        }
        List<TaskHandlingRecord> tasks = taskHandlingRecordMapper.findAllByDataTaskId(dataTaskRecord.getId());

        for(int i=0;i<tasks.size();i++){
            AuditDTO auditDTO = new AuditDTO();
            auditDTO.setTime(tasks.get(i).getHandleTime());
            auditDTO.setName(authUserService.findById(tasks.get(i).getHandler()).getName());
            auditDTO.setComment(tasks.get(i).getHandleReply());
            auditDTOs.add(auditDTO);
        }
        ans.setAudit(auditDTOs);
        ans.setJudgePeole(authUserService.findById(record.getJudger()).getName());
        ans.setJudgeDate(record.getJudgeDate());
        ans.setDeliveryPeople(authUserService.findById(sampleDeliveringRecord.getDelivererId()).getName());
        ans.setDeliveryDate(sampleDeliveringRecord.getSampleDeliveringDate());
        return ans;
    }
}
