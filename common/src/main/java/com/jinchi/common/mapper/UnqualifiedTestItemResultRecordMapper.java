package com.jinchi.common.mapper;

import com.jinchi.common.domain.UnqualifiedTestItemResultRecord;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 不合格检测项目结果纪录映射类
 * @date 2018/11/26 20:25
 */
@Mapper
@Component
public interface UnqualifiedTestItemResultRecordMapper {

    /**
     * 添加一个不合格检测项目结果纪录
     *
     * @param unqualifiedTestItemResultRecord 不合格检测项目结果纪录
     */
    void insert(UnqualifiedTestItemResultRecord unqualifiedTestItemResultRecord);

    /**
     * 批量插入
     *
     * @param unqualifiedTestItemResultRecord
     */
    void batchInsert(List<UnqualifiedTestItemResultRecord> unqualifiedTestItemResultRecord);

    /**
     * 更新一个不合格检测项目结果纪录
     *
     * @param unqualifiedTestItemResultRecords 不合格检测项目结果纪录
     */
    void update(UnqualifiedTestItemResultRecord unqualifiedTestItemResultRecords);

    /**
     * 批量更新
     *
     * @param unqualifiedTestItemResultRecords
     */
    void batchUpdate(List<UnqualifiedTestItemResultRecord> unqualifiedTestItemResultRecords);

    /**
     * 根据ID删除不合格检测项目结果纪录
     *
     * @param id 不合格检测项目结果纪录ID
     */
    void deleteById(Integer id);

    /**
     * 根据IDS删除不合格检测项目结果纪录
     *
     * @param ids 不合格检测项目结果纪录ID数组
     */
    void deleteByIds(Integer[] ids);

    /**
     * 根据ID查找不合格检测项目结果纪录
     *
     * @param id 不合格检测项目结果纪录ID
     * @return UnqualifiedTestItemResultRecord
     */
    UnqualifiedTestItemResultRecord getById(Integer id);

    /**
     * 查询所有的不合格检测项目结果纪录
     *
     * @return List<UnqualifiedTestItemResultRecord>
     */
    List<UnqualifiedTestItemResultRecord> getAllByPage();

    /**
     * 根据不合格评审表id查询
     *
     * @param unqualifiedRecordId 不合格评审表id
     * @return
     * @See UnqualifiedTestReportRecord
     */
    List<UnqualifiedTestItemResultRecord> getByUnqualifiedRecordId(@Param("unqualifiedRecordId") Integer unqualifiedRecordId);

}
