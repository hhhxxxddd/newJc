package com.jinchi.app.mapper;

import com.jinchi.app.domain.TestItemResultRecord;
import com.jinchi.app.domain.TestReportRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 */
@Mapper
@Component
public interface TestItemResultRecordMapper {
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

    void insert(TestItemResultRecord testItemResultRecord);

    void update(TestItemResultRecord testItemResultRecord);

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

    /**
     * 批量更新是否合格
     * @param testItemResultRecords
     */
    void batchUpdateIsValid(List<TestItemResultRecord> testItemResultRecords);



}
