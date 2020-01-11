package com.jinchi.common.service;

import com.jinchi.common.domain.TestItemResultRecord;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 */
@Service
public interface TestItemResultRecordService {
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    TestItemResultRecord findById(Integer id);

    void deleteById(Integer id);

    void deleteByIds(Integer[] ids);

    List<TestItemResultRecord> getByTestReportId(Integer testReportRecordId);

    TestItemResultRecord insert(TestItemResultRecord testItemResultRecord);

    TestItemResultRecord update(TestItemResultRecord testItemResultRecord);

    /**
     * 批量新增
     *
     * @param testItemResultRecords
     * @return 返回数如果不对报错
     */
    Integer batchInsert(List<TestItemResultRecord> testItemResultRecords);

    /**
     * 批量更新
     *
     * @param testItemResultRecords
     */
    void batchUpdate(List<TestItemResultRecord> testItemResultRecords);
}
