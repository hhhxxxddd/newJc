package com.jinchi.app.service;

import com.jinchi.app.domain.*;
import com.jinchi.app.dto.LoginAuthDTO;
import com.jinchi.app.dto.LoginDTO;
import com.jinchi.app.dto.PasswordChangeDTO;
import com.jinchi.app.dto.Result;
import com.jinchi.app.mapper.BasicAppInfoMapper;
import com.jinchi.app.mapper.BasicInfoAppAuthorityMapper;
import com.jinchi.app.mapper.BasicInfoAppUserAuthorityMapMapper;
import com.jinchi.app.utils.ResultUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServiceImp implements LoginService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    BasicInfoAppUserAuthorityMapMapper basicInfoAppUserAuthorityMapMapper;
    @Autowired
    BasicInfoAppAuthorityMapper basicInfoAppAuthorityMapper;
    @Autowired
    BasicAppInfoMapper basicAppInfoMapper;

    @Override
    public LoginAuthDTO login(LoginDTO loginDTO, String url) {
        LoginAuthDTO ans = new LoginAuthDTO();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("username", loginDTO.getUsername());
                jsonObject.put("password", loginDTO.getPassword());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
            HttpEntity<String> formEntity = new HttpEntity<String>(jsonObject.toString(), headers);
            Result data = restTemplate.postForObject(url+"/jc/auth/login/", formEntity, Result.class);
            if(!data.getMessage().equals("操作成功")){
                return null;
            }
            String str = data.getData().toString().replaceAll(" ","");
            String[] strs = str.split(",");
            for(int i=0;i<strs.length;i++){
                String[] temp = strs[i].split("=");
                if(temp[0].equals("{id")){
                    ans.setId(Integer.parseInt(temp[1]));
                }
                if(temp[0].equals("username")){
                    ans.setUsername(temp[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BasicInfoAppUserAuthorityMapExample example = new BasicInfoAppUserAuthorityMapExample();
        example.createCriteria().andUserCodeEqualTo(ans.getId());
        List<Byte> authIds = new ArrayList<>();
        List<BasicInfoAppUserAuthorityMap> basicInfoAppUserAuthorityMaps = basicInfoAppUserAuthorityMapMapper.selectByExample(example);
        basicInfoAppUserAuthorityMaps.forEach(e->authIds.add(e.getAuthCode()));
        BasicInfoAppAuthorityExample example1 = new BasicInfoAppAuthorityExample();
        if(authIds.size()!=0) {
            example1.createCriteria().andCodeIn(authIds);
        }
        else{
            example1.createCriteria().andAuthNameEqualTo("无权限");
        }
        List<String> authNames = new ArrayList<>();
        List<BasicInfoAppAuthority> basicInfoAppAuthorities = basicInfoAppAuthorityMapper.selectByExample(example1);
        basicInfoAppAuthorities.forEach(e->authNames.add(e.getAuthName()));
        ans.setAuths(authNames);
        return ans;
    }

    @Override
    public BasicAppInfo versionInfo(String version) {
        BasicAppInfoExample example = new BasicAppInfoExample();
        example.createCriteria().andOsVersionEqualTo(version);
        return basicAppInfoMapper.selectByExample(example).get(0);
    }

    @Override
    public String pwdChange(PasswordChangeDTO passwordChangeDTO, String url) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            try {
                jsonObject.put("userId", passwordChangeDTO.getUserId());
                jsonObject.put("oldPassword", passwordChangeDTO.getOldPassword());
                jsonObject.put("newPassword", passwordChangeDTO.getNewPassword());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
            HttpEntity<String> formEntity = new HttpEntity<String>(jsonObject.toString(), headers);
            ResponseEntity<Result> data = restTemplate.exchange(url+"/jc/auth/passwordChange/", HttpMethod.PUT,formEntity,Result.class);
            //restTemplate.put(url+"/jc/auth/passwordChange/", formEntity, Result.class);
            //data.getBody().getMessage();
            if(data.getBody().getMessage().equals("操作成功"))
                return "修改密码成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "修改密码失败";
    }
}

