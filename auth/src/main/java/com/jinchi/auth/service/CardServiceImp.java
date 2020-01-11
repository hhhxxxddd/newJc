package com.jinchi.auth.service;

import com.jinchi.auth.domain.User;
import com.jinchi.auth.mapper.DepartmentMapper;
import com.jinchi.auth.mapper.UserMapper;
import com.jinchi.auth.utils.jwt.JWTCompressUtil;
import com.jinchi.auth.utils.jwt.JwtUtil;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author：XudongHu
 * @className:CardServiceImp
 * @description:
 * @date:23:29 2019/3/18
 * @Modifer:
 */
@Service
public class CardServiceImp implements CardService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Map<Object, Object> tokenByCard(String cardNumber) {
        User user = userMapper.byCard(cardNumber);
        Assert.notNull(user, "账号不存在");

        //找到所有权限(菜单前缀+操作名称)
        Set<String> auths = userMapper.findUserAuthsString(user.getId());
        Set<String> roles = userMapper.findUserRolesString(user.getId());
        //
        auths.addAll(roles);

        //传入权限
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (String auth : auths) {
            grantedAuthorities.add(new SimpleGrantedAuthority(auth));
        }

        Set<String> authSet = new HashSet<>();
        for (GrantedAuthority authority : grantedAuthorities) {
            authSet.add(authority.getAuthority());
        }
        String userAuthorities = user.getName() + "-" + authSet;


        String token = JwtUtil.createToken(user.getName(), userAuthorities);
        String compressToken = JWTCompressUtil.JWTCompressUtil(token);

        HashMap<Object, Object> authMap = new HashMap<>();
        authMap.put("username", user.getUsername());
        authMap.put("name", user.getName());
        authMap.put("phone,", user.getPhone());
        authMap.put("userId",user.getId());
        authMap.put("department", departmentMapper.byId(user.getDepartmentId()).getDepartmentName());
        authMap.put("idCardNumber", user.getIdCardNumber());
        authMap.put("token",compressToken);

        return authMap;
    }
}