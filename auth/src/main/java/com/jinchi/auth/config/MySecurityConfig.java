package com.jinchi.auth.config;

import com.jinchi.auth.mapper.UserMapper;
import com.jinchi.auth.service.MyUserServiceImp;
import com.jinchi.auth.utils.AuthoritiesUtil;
import com.jinchi.auth.utils.JWTauthenticationEntryPoint;
import com.jinchi.auth.utils.JWTauthenticationFilter;
import com.jinchi.auth.utils.JWTloginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author：XudongHu Security的配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JWTauthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private AuthoritiesUtil authoritiesUtil;

    @Autowired
    private MyUserServiceImp userDetailsService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    //加密方法
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    //默认无需权限的页面
    private static final String[] AUTH_WHITELIST = {
            //swagger-Ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            //  appended to this array
            "/**/favicon.ico",
            "/webjars/**",
            "/**/"
    };

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http    //===>权限验证
                //允许跨域
                .cors().and().csrf().disable()
                //异常管理
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                //不创建session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //以下路径无需验证
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(HttpMethod.POST, "/user/signIn", "/login").permitAll()//登陆和注册不需要权限
                .antMatchers(HttpMethod.GET,"/external/**").permitAll() //通过idCard验证不需要权限
                .anyRequest().authenticated();

        http   //===>禁止缓存
                .headers()
                //允许来自同一个域的任何请求
                .frameOptions().sameOrigin()
                //缓存控制
                .cacheControl();

        authenticationManagerBean();

        http    //===>基于JWT的过滤器
                .addFilterBefore(new JWTloginFilter(authenticationManager(), userMapper,authoritiesUtil), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JWTauthenticationFilter(authenticationManager()));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //解密工具
        //user
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}
