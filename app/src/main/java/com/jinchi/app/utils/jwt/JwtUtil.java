package com.jinchi.app.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;

/**
 * @author：XudongHu
 * @className:JwtUtil
 * @description: token生成器
 * @date:22:16 2018/12/18
 */
public class JwtUtil {

    /**
     * 解析Token
     *
     * @param token Token String
     * @return
     */
    public  static Claims parseToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(JwtProperty.getSecretKey())
                    .parseClaimsJws(token.replace(JwtProperty.getPrefix(), "")).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 生成Token
     *
     * @param username    用户名
     *         JwtProperty 自定义的jwt公共属性（包括超时时长、签发者、 私钥）
     * @param authorities 权限,用来生成token的主体
     * @return
     *
     */
    public static   String createToken(String username, String authorities) {
        Calendar calendar = Calendar.getInstance();

        String builder = Jwts.builder()
                .setSubject(authorities)
//                .setIssuer()  签发者设置
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS512, JwtProperty.getSecretKey())
                .setExpiration(new Date(calendar.getTimeInMillis() + JwtProperty.getExpiry()))
                .setNotBefore(calendar.getTime())
                .compact();

        String token = JwtProperty.getPrefix() + builder;

        return token;
    }
}
