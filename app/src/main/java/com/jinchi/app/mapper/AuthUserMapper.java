package com.jinchi.app.mapper;

import com.jinchi.app.dto.AuthUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:UserMapper
 * @description:
 * @date:13:01 2018/11/21
 */
@Mapper
@Component
public interface AuthUserMapper {
    /**
     * 查询所有用户
     *
     * @return
     */
    List<AuthUserDTO> findAll();

    /**
     * 查询一个用户
     */
    AuthUserDTO byId(@Param("id") Integer id);

    /**
     * 根据名称模糊
     * @param name
     * @return
     */
    List<AuthUserDTO> byNameLike(@Param("name") String name);

    /**
     * 根据id卡查用户
     * @param idCard
     * @return
     */
    Integer byIdCard(@Param("idCard") String idCard);


}

