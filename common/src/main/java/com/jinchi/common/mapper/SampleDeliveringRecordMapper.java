package com.jinchi.common.mapper;

import com.jinchi.common.domain.SampleDeliveringRecord;
import com.jinchi.common.dto.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 * Modified:huxudong
 */
@Mapper
@Component
public interface SampleDeliveringRecordMapper {
    /**
     * 新增样品送检
     *
     * @param sampleDeliveringRecord
     */
    void insert(SampleDeliveringRecord sampleDeliveringRecord);

    /**
     * 更新样品送检
     */
    void update(SampleDeliveringRecord sampleDeliveringRecord);

    /**
     * 根据id查询--杜敏
     */
    SampleDeliveringRecord getById(Integer id);

    List<SampleDeliveringRecord> byBatchIds(List<Integer> ids);


    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(@Param("id") Integer id);

    /**
     * 根据ids删除
     *
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id和类型查询样品送检
     * 1原材料 2中间件 3成品
     * accept为接收状态
     */
    SampleDeliveringRecord getByIdAndTypeAndAccept(@Param("id") Integer id, @Param("type") Integer type, @Param("acceptStatus") Integer acceptStatus);


    /**
     * 查询所有-可分页
     *
     * @param type         根据类型 NN
     * @param acceptStatus 根据接收状态 NN
     * @param factoryName  送检工厂名称模糊
     * @return
     */
    List<SampleDeliveringRecord> getAllByFactors(@Param("type") Integer type,
                                                 @Param("acceptStatus") Integer acceptStatus,
                                                 @Param("factoryName") String factoryName,
                                                 @Param("pageBean") PageBean pageBean);

    /**
     * 根据条件查询总数
     *
     * @return
     */
    Integer countSum(@Param("type") Integer type,
                     @Param("acceptStatus") Integer acceptStatus,
                     @Param("factoryName") String factoryName);


    List<SampleDeliveringRecord> selectByBatch(@Param("batch") String batch, @Param("start") Integer start, @Param("end") Integer end, @Param("type") Integer type);
}
