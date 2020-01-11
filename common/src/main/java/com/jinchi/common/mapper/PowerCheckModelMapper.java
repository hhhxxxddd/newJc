package com.jinchi.common.mapper;

import com.jinchi.common.domain.PowerCheckModel;
import com.jinchi.common.domain.PowerCheckModelExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface PowerCheckModelMapper {
    int countByExample(PowerCheckModelExample example);

    int deleteByExample(PowerCheckModelExample example);

    int deleteByPrimaryKey(Long code);

    int insert(PowerCheckModel record);

    int insertSelective(PowerCheckModel record);

    List<PowerCheckModel> selectByExample(PowerCheckModelExample example);

    PowerCheckModel selectByPrimaryKey(Long code);

    int updateByExampleSelective(@Param("record") PowerCheckModel record, @Param("example") PowerCheckModelExample example);

    int updateByExample(@Param("record") PowerCheckModel record, @Param("example") PowerCheckModelExample example);

    int updateByPrimaryKeySelective(PowerCheckModel record);

    int updateByPrimaryKey(PowerCheckModel record);
}