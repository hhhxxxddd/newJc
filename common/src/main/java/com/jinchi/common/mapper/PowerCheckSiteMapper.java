package com.jinchi.common.mapper;

import com.jinchi.common.domain.PowerCheckSite;
import com.jinchi.common.domain.PowerCheckSiteExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface PowerCheckSiteMapper {
    int countByExample(PowerCheckSiteExample example);

    int deleteByExample(PowerCheckSiteExample example);

    int deleteByPrimaryKey(Long code);

    int insert(PowerCheckSite record);

    int insertSelective(PowerCheckSite record);

    List<PowerCheckSite> selectByExample(PowerCheckSiteExample example);

    PowerCheckSite selectByPrimaryKey(Long code);

    int updateByExampleSelective(@Param("record") PowerCheckSite record, @Param("example") PowerCheckSiteExample example);

    int updateByExample(@Param("record") PowerCheckSite record, @Param("example") PowerCheckSiteExample example);

    int updateByPrimaryKeySelective(PowerCheckSite record);

    int updateByPrimaryKey(PowerCheckSite record);
}