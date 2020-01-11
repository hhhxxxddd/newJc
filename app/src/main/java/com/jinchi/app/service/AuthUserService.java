package com.jinchi.app.service;

import com.jinchi.app.dto.AuthUserDTO;

import java.util.List;

public interface AuthUserService {

    AuthUserDTO findById(Integer id);

    List<AuthUserDTO> findAll();
}
