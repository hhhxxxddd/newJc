package com.jinchi.common.mapper;

import com.jinchi.common.domain.PowerCheckModelDetail;
import com.jinchi.common.domain.PowerCheckModelDetailExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface PowerCheckModelDetailMapper {
    int countByExample(PowerCheckModelDetailExample example);

    int deleteByExample(PowerCheckModelDetailExample example);

    int deleteByPrimaryKey(Long code);

    int insert(PowerCheckModelDetail record);

    int insertSelective(PowerCheckModelDetail record);

    List<PowerCheckModelDetail> selectByExample(PowerCheckModelDetailExample example);

    PowerCheckModelDetail selectByPrimaryKey(Long code);

    int updateByExampleSelective(@Param("record") PowerCheckModelDetail record, @Param("example") PowerCheckModelDetailExample example);

    int updateByExample(@Param("record") PowerCheckModelDetail record, @Param("example") PowerCheckModelDetailExample example);

    int updateByPrimaryKeySelective(PowerCheckModelDetail record);

    int updateByPrimaryKey(PowerCheckModelDetail record);
}