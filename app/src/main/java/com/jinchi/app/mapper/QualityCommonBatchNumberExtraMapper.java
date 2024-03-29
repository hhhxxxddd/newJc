package com.jinchi.app.mapper;

import com.jinchi.app.domain.QualityCommonBatchNumberExtra;
import com.jinchi.app.domain.QualityCommonBatchNumberExtraExample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QualityCommonBatchNumberExtraMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quality_common_batch_number_extra
     *
     * @mbggenerated
     */
    int countByExample(QualityCommonBatchNumberExtraExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quality_common_batch_number_extra
     *
     * @mbggenerated
     */
    int deleteByExample(QualityCommonBatchNumberExtraExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quality_common_batch_number_extra
     *
     * @mbggenerated
     */
    @Insert({
        "insert into quality_common_batch_number_extra (id, common_batch_id, ",
        "batch)",
        "values (#{id,jdbcType=INTEGER}, #{commonBatchId,jdbcType=INTEGER}, ",
        "#{batch,jdbcType=VARCHAR})"
    })
    int insert(QualityCommonBatchNumberExtra record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quality_common_batch_number_extra
     *
     * @mbggenerated
     */
    int insertSelective(QualityCommonBatchNumberExtra record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quality_common_batch_number_extra
     *
     * @mbggenerated
     */
    List<QualityCommonBatchNumberExtra> selectByExample(QualityCommonBatchNumberExtraExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quality_common_batch_number_extra
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") QualityCommonBatchNumberExtra record, @Param("example") QualityCommonBatchNumberExtraExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quality_common_batch_number_extra
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") QualityCommonBatchNumberExtra record, @Param("example") QualityCommonBatchNumberExtraExample example);
}