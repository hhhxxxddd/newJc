package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAppAuthority;

import java.util.List;

public interface AppUserAuthService {

    List<BasicInfoAppAuthority> getAllAuth();

    List<BasicInfoAppAuthority> getAuthByUserId(Integer userId);

    void update(Integer userId,Integer[] authIds);

    List getUser(Integer deptCode);

    void assign(Integer deptCode,Integer[] userIds);
}
