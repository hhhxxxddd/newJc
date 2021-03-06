package com.jinchi.app.mapper;

import com.jinchi.app.domain.BasicInfoLocation;
import com.jinchi.app.domain.BasicInfoLocationExample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BasicInfoLocationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_location
     *
     * @mbggenerated
     */
    int countByExample(BasicInfoLocationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_location
     *
     * @mbggenerated
     */
    int deleteByExample(BasicInfoLocationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_location
     *
     * @mbggenerated
     */
    @Insert({
        "insert into basic_info_location (code, dept_code, ",
        "location_name, id_code)",
        "values (#{code,jdbcType=INTEGER}, #{deptCode,jdbcType=INTEGER}, ",
        "#{locationName,jdbcType=VARCHAR}, #{idCode,jdbcType=VARCHAR})"
    })
    int insert(BasicInfoLocation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_location
     *
     * @mbggenerated
     */
    int insertSelective(BasicInfoLocation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_location
     *
     * @mbggenerated
     */
    List<BasicInfoLocation> selectByExample(BasicInfoLocationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_location
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BasicInfoLocation record, @Param("example") BasicInfoLocationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_location
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BasicInfoLocation record, @Param("example") BasicInfoLocationExample example);
}