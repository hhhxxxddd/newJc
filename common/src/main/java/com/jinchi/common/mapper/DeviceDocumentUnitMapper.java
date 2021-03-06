package com.jinchi.common.mapper;

import com.jinchi.common.domain.DeviceDocumentUnit;
import com.jinchi.common.domain.DeviceDocumentUnitExample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface DeviceDocumentUnitMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_document_unit
     *
     * @mbggenerated
     */
    int countByExample(DeviceDocumentUnitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_document_unit
     *
     * @mbggenerated
     */
    int deleteByExample(DeviceDocumentUnitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_document_unit
     *
     * @mbggenerated
     */
    @Insert({
        "insert into device_document_unit (code, fixedassets_code, ",
        "device_name, specification, ",
        "id_code, startdate, ",
        "supplier, supplier_num, ",
        "power, dept_code, edit_flag, ",
        "main_code)",
        "values (#{code,jdbcType=BIGINT}, #{fixedassetsCode,jdbcType=VARCHAR}, ",
        "#{deviceName,jdbcType=VARCHAR}, #{specification,jdbcType=VARCHAR}, ",
        "#{idCode,jdbcType=VARCHAR}, #{startdate,jdbcType=TIMESTAMP}, ",
        "#{supplier,jdbcType=VARCHAR}, #{supplierNum,jdbcType=VARCHAR}, ",
        "#{power,jdbcType=REAL}, #{deptCode,jdbcType=INTEGER}, #{editFlag,jdbcType=BIT}, ",
        "#{mainCode,jdbcType=BIGINT})"
    })
    int insert(DeviceDocumentUnit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_document_unit
     *
     * @mbggenerated
     */
    int insertSelective(DeviceDocumentUnit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_document_unit
     *
     * @mbggenerated
     */
    List<DeviceDocumentUnit> selectByExample(DeviceDocumentUnitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_document_unit
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") DeviceDocumentUnit record, @Param("example") DeviceDocumentUnitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_document_unit
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") DeviceDocumentUnit record, @Param("example") DeviceDocumentUnitExample example);


    List<DeviceDocumentUnit> selectByCondition(@Param("sql")String sql);
}