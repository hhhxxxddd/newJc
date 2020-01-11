package com.jinchi.common.mapper;

import com.jinchi.common.domain.PowerCheckItem;
import com.jinchi.common.domain.PowerCheckItemExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface PowerCheckItemMapper {
    int countByExample(PowerCheckItemExample example);

    int deleteByExample(PowerCheckItemExample example);

    int deleteByPrimaryKey(Long code);

    int insert(PowerCheckItem record);

    int insertSelective(PowerCheckItem record);

    List<PowerCheckItem> selectByExample(PowerCheckItemExample example);

    PowerCheckItem selectByPrimaryKey(Long code);

    int updateByExampleSelective(@Param("record") PowerCheckItem record, @Param("example") PowerCheckItemExample example);

    int updateByExample(@Param("record") PowerCheckItem record, @Param("example") PowerCheckItemExample example);

    int updateByPrimaryKeySelective(PowerCheckItem record);

    int updateByPrimaryKey(PowerCheckItem record);

    List selectBySiteCode(@Param("siteCode") Long siteCode);
}