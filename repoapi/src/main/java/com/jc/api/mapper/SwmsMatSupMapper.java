package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.api.entity.SwmsBasicMatSup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SwmsMatSupMapper extends BaseMapper<SwmsBasicMatSup> {
}
