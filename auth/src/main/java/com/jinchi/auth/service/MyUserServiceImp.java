package com.jinchi.auth.service;

import com.jinchi.auth.domain.MyUser;
import com.jinchi.auth.domain.User;
import com.jinchi.auth.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author：XudongHu
 * @description: 不解释
 * @date:21:55 2018/10/30
 */
@Service
public class MyUserServiceImp implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("Ready to load user by username.");
        //根据账号名称找到用户
        User user = userMapper.findByFullName(username);

        if(user==null){
            throw new UsernameNotFoundException("账号不存在");
        }
        //TODO 验证登陆异常


        //找到所有权限(菜单前缀+操作名称)
        Set<String> auths = userMapper.findUserAuthsString(user.getId());
        Set<String>  roles = userMapper.findUserRolesString(user.getId());
        //
        auths.addAll(roles);

        //传入权限
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(String auth:auths){
            grantedAuthorities.add(new SimpleGrantedAuthority(auth));
        }

        MyUser myUser = new MyUser(user,grantedAuthorities);

        logger.info("userDetails user{}",user);
        logger.info("userDetails auths{}",auths);

        return myUser;
    }

}
