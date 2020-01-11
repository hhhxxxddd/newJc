package com.jinchi.common.mapper;

import com.jinchi.common.domain.PowerCheckRecordHead;
import com.jinchi.common.domain.PowerCheckRecordHeadExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface PowerCheckRecordHeadMapper {
    int countByExample(PowerCheckRecordHeadExample example);

    int deleteByExample(PowerCheckRecordHeadExample example);

    int deleteByPrimaryKey(Long code);

    int insert(PowerCheckRecordHead record);

    int insertSelective(PowerCheckRecordHead record);

    List<PowerCheckRecordHead> selectByExample(PowerCheckRecordHeadExample example);

    PowerCheckRecordHead selectByPrimaryKey(Long code);

    int updateByExampleSelective(@Param("record") PowerCheckRecordHead record, @Param("example") PowerCheckRecordHeadExample example);

    int updateByExample(@Param("record") PowerCheckRecordHead record, @Param("example") PowerCheckRecordHeadExample example);

    int updateByPrimaryKeySelective(PowerCheckRecordHead record);

    int updateByPrimaryKey(PowerCheckRecordHead record);
}