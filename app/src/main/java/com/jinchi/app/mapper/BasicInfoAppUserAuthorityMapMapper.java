package com.jinchi.app.mapper;

import com.jinchi.app.domain.BasicInfoAppUserAuthorityMap;
import com.jinchi.app.domain.BasicInfoAppUserAuthorityMapExample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BasicInfoAppUserAuthorityMapMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_app_user_authority_map
     *
     * @mbggenerated
     */
    int countByExample(BasicInfoAppUserAuthorityMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_app_user_authority_map
     *
     * @mbggenerated
     */
    int deleteByExample(BasicInfoAppUserAuthorityMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_app_user_authority_map
     *
     * @mbggenerated
     */
    @Insert({
        "insert into basic_info_app_user_authority_map (code, user_code, ",
        "auth_code)",
        "values (#{code,jdbcType=INTEGER}, #{userCode,jdbcType=INTEGER}, ",
        "#{authCode,jdbcType=TINYINT})"
    })
    int insert(BasicInfoAppUserAuthorityMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_app_user_authority_map
     *
     * @mbggenerated
     */
    int insertSelective(BasicInfoAppUserAuthorityMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_app_user_authority_map
     *
     * @mbggenerated
     */
    List<BasicInfoAppUserAuthorityMap> selectByExample(BasicInfoAppUserAuthorityMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_app_user_authority_map
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BasicInfoAppUserAuthorityMap record, @Param("example") BasicInfoAppUserAuthorityMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_app_user_authority_map
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BasicInfoAppUserAuthorityMap record, @Param("example") BasicInfoAppUserAuthorityMapExample example);
}