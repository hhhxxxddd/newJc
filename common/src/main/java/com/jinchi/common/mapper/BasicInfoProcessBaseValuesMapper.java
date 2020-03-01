package com.jinchi.common.mapper;

import com.jinchi.common.domain.BasicInfoProcessBaseValues;
import com.jinchi.common.domain.BasicInfoProcessBaseValuesExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BasicInfoProcessBaseValuesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_process_base_values
     *
     * @mbggenerated Sun Mar 01 09:57:39 CST 2020
     */
    int countByExample(BasicInfoProcessBaseValuesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_process_base_values
     *
     * @mbggenerated Sun Mar 01 09:57:39 CST 2020
     */
    int deleteByExample(BasicInfoProcessBaseValuesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_process_base_values
     *
     * @mbggenerated Sun Mar 01 09:57:39 CST 2020
     */
    int deleteByPrimaryKey(Integer code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_process_base_values
     *
     * @mbggenerated Sun Mar 01 09:57:40 CST 2020
     */
    int insert(BasicInfoProcessBaseValues record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_process_base_values
     *
     * @mbggenerated Sun Mar 01 09:57:40 CST 2020
     */
    int insertSelective(BasicInfoProcessBaseValues record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_process_base_values
     *
     * @mbggenerated Sun Mar 01 09:57:40 CST 2020
     */
    List<BasicInfoProcessBaseValues> selectByExample(BasicInfoProcessBaseValuesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_process_base_values
     *
     * @mbggenerated Sun Mar 01 09:57:40 CST 2020
     */
    BasicInfoProcessBaseValues selectByPrimaryKey(Integer code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_process_base_values
     *
     * @mbggenerated Sun Mar 01 09:57:40 CST 2020
     */
    int updateByExampleSelective(@Param("record") BasicInfoProcessBaseValues record, @Param("example") BasicInfoProcessBaseValuesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_process_base_values
     *
     * @mbggenerated Sun Mar 01 09:57:40 CST 2020
     */
    int updateByExample(@Param("record") BasicInfoProcessBaseValues record, @Param("example") BasicInfoProcessBaseValuesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_process_base_values
     *
     * @mbggenerated Sun Mar 01 09:57:40 CST 2020
     */
    int updateByPrimaryKeySelective(BasicInfoProcessBaseValues record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_process_base_values
     *
     * @mbggenerated Sun Mar 01 09:57:40 CST 2020
     */
    int updateByPrimaryKey(BasicInfoProcessBaseValues record);
}