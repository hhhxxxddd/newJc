package com.jinchi.auth.utils;

import com.jinchi.auth.utils.jwt.JWTCompressUtil;
import com.jinchi.auth.utils.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization项读取token数据
 * 然后用Jwts包提供的方法校验token的合法性。如果校验通过，就认为这是一个取得授权的合法请求。
 *
 * @author：XudongHu
 * @author: Lanyage
 * @since 2018/11/14
 */
public class JWTauthenticationFilter extends BasicAuthenticationFilter {

    public JWTauthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    private static final Logger logger = LoggerFactory.getLogger(JWTauthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("==>开始授权<==");

        String header = request.getHeader("Authorization");


        String token = JWTCompressUtil.decompress(header);
        //ROLE_ADMIN

        logger.info("==================");
        System.out.println(token);

        if (null == token || !token.startsWith("JC")) {
            logger.info("==> 授权失败:header为空或者header并不以JC开头 <==");
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        String token = JWTCompressUtil.decompress(request.getHeader("Authorization"));
        if (null != token && !"".equals(token.trim())) {
            //取得token
            Claims claims = JwtUtil.parseToken(token);

            //取得token主体
            String authSubject = claims.getSubject();


            if (authSubject != null) {
                String auths = authSubject.split("-")[1];  //lanyage-[ROLE_ADMIN, ROLE_USER]

                auths = auths.substring(1, auths.length() - 1);

                String[] authorities = auths.split(",");
                Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

                for (String auth : authorities) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(auth.trim()));  //去掉两端空格(unicode小于等于32的所有字符)
                }

                logger.info("==> 授权成功,权限为:{} <==", grantedAuthorities.toString());

                return new UsernamePasswordAuthenticationToken(authSubject, null, grantedAuthorities);
            }
            logger.info("==> 授权失败:user为空 <==");
            return null;
        }
        logger.info("==> 授权失败:token为空 <==");
        return null;
    }

}
