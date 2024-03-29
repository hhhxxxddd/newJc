package com.jinchi.common.service;

import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.mapper.AuthUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：XudongHu
 * @className:AuthUserServiceImp
 * @description:
 * @date:11:23 2018/11/23
 */
@Service
public class AuthUserServiceImp implements AuthUserService {
    @Autowired
    private AuthUserMapper authUserMapper;

    @Override
    public AuthUserDTO findById(Integer id) {
        return authUserMapper.byId(id);
    }

    @Override
    public List<AuthUserDTO> findAll() {
        return authUserMapper.findAll();
    }
}
