package com.jinchi.common.mapper;

import com.jinchi.common.dto.AuthDepartmentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


/**
 * @author：XudongHu
 * @className:AuthDepartmentMapper
 * @description: 权限模块部门
 * @date:22:21 2019/3/8
 * @Modifer:
 */
@Mapper
@Component
public interface AuthDepartmentMapper {
    /**
     * 部门id
     * @param id
     * @return
     */
    AuthDepartmentDTO byId(@Param("id") Integer id);
}
