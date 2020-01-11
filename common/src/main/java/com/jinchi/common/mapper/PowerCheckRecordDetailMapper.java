package com.jinchi.common.mapper;

import com.jinchi.common.domain.PowerCheckRecordDetail;
import com.jinchi.common.domain.PowerCheckRecordDetailExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface PowerCheckRecordDetailMapper {
    int countByExample(PowerCheckRecordDetailExample example);

    int deleteByExample(PowerCheckRecordDetailExample example);

    int deleteByPrimaryKey(Long code);

    int insert(PowerCheckRecordDetail record);

    int insertSelective(PowerCheckRecordDetail record);

    List<PowerCheckRecordDetail> selectByExample(PowerCheckRecordDetailExample example);

    PowerCheckRecordDetail selectByPrimaryKey(Long code);

    int updateByExampleSelective(@Param("record") PowerCheckRecordDetail record, @Param("example") PowerCheckRecordDetailExample example);

    int updateByExample(@Param("record") PowerCheckRecordDetail record, @Param("example") PowerCheckRecordDetailExample example);

    int updateByPrimaryKeySelective(PowerCheckRecordDetail record);

    int updateByPrimaryKey(PowerCheckRecordDetail record);
}