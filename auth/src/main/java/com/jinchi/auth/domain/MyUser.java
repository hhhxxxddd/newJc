package com.jinchi.auth.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author：XudongHu
 *
 * 用MyUser实现security的UserDetails来封装我们自己的User
 *
 */
public class MyUser implements UserDetails {
    private String username = "admin";
    private String password = "123456";
    private Set<GrantedAuthority> authorities;

    //ROLE
    //这三个是我们需要的
    //其余重写的方法不用考虑

    public MyUser(){

    }

    //创建构造器
    public MyUser(User user,Set<GrantedAuthority> authorities){
        this();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    //是否过期
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    //是否冻结
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    //签证是否过期
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    //是否可用
    public boolean isEnabled() {
        return true;
    }
}
