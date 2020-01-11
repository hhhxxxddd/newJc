package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.DeptUserDTO;
import com.jinchi.common.mapper.BasicInfoAppAuthorityMapper;
import com.jinchi.common.mapper.BasicInfoAppUserAuthorityMapMapper;
import com.jinchi.common.mapper.BasicInfoUserDeviceDeptMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AppUserAuthServiceImp implements AppUserAuthService {
    @Autowired
    BasicInfoAppAuthorityMapper basicInfoAppAuthorityMapper;
    @Autowired
    BasicInfoAppUserAuthorityMapMapper basicInfoAppUserAuthorityMapMapper;
    @Autowired
    BasicInfoUserDeviceDeptMapMapper basicInfoUserDeviceDeptMapMapper;
    @Autowired
    AuthUserService authUserService;

    @Override
    public List<BasicInfoAppAuthority> getAllAuth() {
        BasicInfoAppAuthorityExample example = new BasicInfoAppAuthorityExample();
        example.createCriteria();
        return basicInfoAppAuthorityMapper.selectByExample(example);
    }

    @Override
    public List<BasicInfoAppAuthority> getAuthByUserId(Integer userId) {
        BasicInfoAppUserAuthorityMapExample example = new BasicInfoAppUserAuthorityMapExample();
        example.createCriteria().andUserCodeEqualTo(userId);
        List<BasicInfoAppUserAuthorityMap> basicInfoAppUserAuthorityMaps = basicInfoAppUserAuthorityMapMapper.selectByExample(example);
        List<Byte> authIds = new ArrayList<>();
        basicInfoAppUserAuthorityMaps.forEach(e->authIds.add(e.getAuthCode()));
        BasicInfoAppAuthorityExample example1 = new BasicInfoAppAuthorityExample();
        if(authIds.size()!=0) {
            example1.createCriteria().andCodeIn(authIds);
        }
        else{
            example1.createCriteria().andAuthNameEqualTo("无权限");
        }
        return basicInfoAppAuthorityMapper.selectByExample(example1);
    }

    @Override
    public void update(Integer userId, Integer[] authIds) {
        BasicInfoAppUserAuthorityMapExample example = new BasicInfoAppUserAuthorityMapExample();
        example.createCriteria().andUserCodeEqualTo(userId);
        basicInfoAppUserAuthorityMapMapper.deleteByExample(example);

        for(Integer authId:authIds){
            BasicInfoAppUserAuthorityMap basicInfoAppUserAuthorityMap = new BasicInfoAppUserAuthorityMap();
            basicInfoAppUserAuthorityMap.setUserCode(userId);
            basicInfoAppUserAuthorityMap.setAuthCode(authId.byteValue());
            basicInfoAppUserAuthorityMapMapper.insertSelective(basicInfoAppUserAuthorityMap);
        }

    }

    @Override
    public List getUser(Integer deptCode) {
        List<DeptUserDTO> ans = new ArrayList<>();
        BasicInfoUserDeviceDeptMapExample example = new BasicInfoUserDeviceDeptMapExample();
        example.createCriteria().andDeptCodeEqualTo(deptCode);
        List<BasicInfoUserDeviceDeptMap> maps = basicInfoUserDeviceDeptMapMapper.selectByExample(example);
        Set<Integer> chosenIds = new HashSet<>();
        maps.forEach(e->chosenIds.add(e.getAuthCode()));
        List<AuthUserDTO> users = authUserService.findAll();
        for(int i=0;i<users.size();i++){
            DeptUserDTO user = new DeptUserDTO();
            user.setUserId(users.get(i).getId());
            user.setUsername(users.get(i).getName());
            if(chosenIds.contains(user.getUserId())){
                user.setChosen(true);
            }else{
                user.setChosen(false);
            }
            ans.add(user);
        }
        return ans;
    }

    @Override
    public void assign(Integer deptCode, Integer[] userIds) {
        BasicInfoUserDeviceDeptMapExample example = new BasicInfoUserDeviceDeptMapExample();
        example.createCriteria().andDeptCodeEqualTo(deptCode);
        basicInfoUserDeviceDeptMapMapper.deleteByExample(example);
        for(int i=0;i<userIds.length;i++){
            BasicInfoUserDeviceDeptMap map = new BasicInfoUserDeviceDeptMap();
            map.setDeptCode(deptCode);
            map.setAuthCode(userIds[i]);
            basicInfoUserDeviceDeptMapMapper.insertSelective(map);
            example.clear();
        }
    }
}
