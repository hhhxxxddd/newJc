package com.jinchi.common.service;

import com.jinchi.common.domain.TestItemResultRecord;
import com.jinchi.common.mapper.TestItemResultRecordMapper;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 */
@Service
public class TestItemResultRecordServiceImp implements TestItemResultRecordService {

    @Autowired
    private TestItemResultRecordMapper testItemResultRecordMapper;


    @Override
    public TestItemResultRecord insert(TestItemResultRecord testItemResultRecord) {
        testItemResultRecordMapper.insert(testItemResultRecord);
        return testItemResultRecord;
    }

    @Override
    public TestItemResultRecord update(TestItemResultRecord testItemResultRecord) {
        testItemResultRecordMapper.update(testItemResultRecord);
        return testItemResultRecord;
    }

    /**
     * 批量新增
     *
     * @param testItemResultRecords
     * @return 返回数如果不对报错
     */
    @Override
    @Transactional
    public Integer batchInsert(List<TestItemResultRecord> testItemResultRecords) {
        if (testItemResultRecords.size() != testItemResultRecordMapper.batchInsert(testItemResultRecords)) {
            Assert.notNull(null, "插入失败");
            return 0;
        }
        return 1;
    }

    /**
     * 批量更新
     *
     * @param testItemResultRecords
     */
    @Override
    public void batchUpdate(List<TestItemResultRecord> testItemResultRecords) {
        testItemResultRecordMapper.batchUpdate(testItemResultRecords);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public TestItemResultRecord findById(Integer id) {
        return testItemResultRecordMapper.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        testItemResultRecordMapper.deleteById(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        testItemResultRecordMapper.deleteByIds(ids);
    }

    @Override
    public List<TestItemResultRecord> getByTestReportId(Integer testReportId) {
        return testItemResultRecordMapper.getByTestReportId(testReportId);
    }
}
