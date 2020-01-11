package com.jinchi.auth.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinchi.auth.domain.MyUser;
import com.jinchi.auth.domain.Result;
import com.jinchi.auth.domain.User;
import com.jinchi.auth.dto.LoginInitialDTO;
import com.jinchi.auth.exceptions.JcExceptions;
import com.jinchi.auth.mapper.UserMapper;
import com.jinchi.auth.utils.jwt.JWTCompressUtil;
import com.jinchi.auth.utils.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * @author：XudongHu 验证账号密码成功后, 生成token给客户端, 下次登陆, 客户端使用token验证(有生命周期)
 */
public class JWTloginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthoritiesUtil authoritiesUtil;

    private UserMapper userMapper;

    private AuthenticationManager authenticationManager;

    public JWTloginFilter(AuthenticationManager authenticationManager, UserMapper userMapper,AuthoritiesUtil authoritiesUtil) {
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.authoritiesUtil = authoritiesUtil;
    }


    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    //解析并接收用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException,JcExceptions {
        try {
            //将request中读到的信息赋予MyUser
            MyUser user = new ObjectMapper().readValue(request.getInputStream(), MyUser.class);

            //在这里读到用户username进行操作


            response.setBufferSize(402800);

            response.setCharacterEncoding("utf-8");

            response.setContentType("application/json;charset=UTF-8");



            String name = user.getUsername().intern();

            /**
             *  设置登陆后的初始数据
             */
            User domainUser = userMapper.findByFullName(name);

            if (null == domainUser) {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(403);
                Result<Object> result = new Result<>();
                result.setCode(6);
                result.setMessage("账户密码错误");
                response.getWriter().write(convertObjectToJson(result));
                return null;
            }


            Integer userId = domainUser.getId();

            OutputStream writer = response.getOutputStream();
            //登陆后录入基础信息和权限
            LoginInitialDTO loginInitialData = authoritiesUtil.loginSuccess(userId);

            ObjectMapper om = new ObjectMapper();

            String loginInitialJson = om.writeValueAsString(loginInitialData);

            writer.write(loginInitialJson.getBytes("utf-8"));


            //通过权限管理器,将账号密码和其他信息返回给服务器
            return authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    user.getUsername(),
                                    user.getPassword(),
                                    new HashSet<GrantedAuthority>()   //这里是权限
                            )
                    );
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //登陆成功后生成token
    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        logger.info("登陆成功");

        //读到所有权限
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        //将每个权限添加到角色list中
        Set<String> authSet = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            authSet.add(authority.getAuthority());
        }

        String userAuthorities = authResult.getName() + "-" + authSet;
        String token = JwtUtil.createToken(authResult.getName(), userAuthorities);
        String compressToken = JWTCompressUtil.JWTCompressUtil(token);

        logger.info("你的token压缩后为: " + compressToken);

        response.addHeader("Authorization", compressToken);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept,Authorization");
        return;
    }
}
