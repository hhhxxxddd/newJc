package com.jinchi.common.mapper;

import com.jinchi.common.dto.AuthRoleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author：XudongHu
 * @className:AuthRoleMapper
 * @description: 权限模块角色接口
 * @date:22:29 2018/12/7
 */
@Mapper
@Component
public interface AuthRoleMapper {
    /**
     * 根据角色id查询
     *
     * @param roleId
     * @return
     */
    AuthRoleDTO findById(@Param("roleId") Integer roleId);

    AuthRoleDTO byName(@Param("roleName") String roleName);
}
