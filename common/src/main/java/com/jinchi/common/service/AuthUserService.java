package com.jinchi.common.service;

import com.jinchi.common.dto.AuthUserDTO;

import java.util.List;

/**
 * @author：XudongHu
 * @className:AuthUserService
 * @description:
 * @date:11:23 2018/11/23
 */
public interface AuthUserService {

    AuthUserDTO findById(Integer id);

    List<AuthUserDTO> findAll();
}
