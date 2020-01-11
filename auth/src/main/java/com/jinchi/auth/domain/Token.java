package com.jinchi.auth.domain;

/**
 * @author：XudongHu
 * @className:Token
 * @description: TOKEN实体
 * @date:16:00 2019/3/12
 * @Modifer:
 */
public class Token {
    private Integer userId;

    private String token;

    public Integer getUserId() {
        return userId;
    }

    public Token setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public Token setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String toString() {
        return "Token{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                '}';
    }
}
