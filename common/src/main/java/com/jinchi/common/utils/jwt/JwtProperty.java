package com.jinchi.common.utils.jwt;

/**
 * @author：XudongHu
 * @className:JwtProperty
 * @description:
 * @date:22:20 2018/12/18
 */
public class JwtProperty {
    //请求头前缀设置
    private final static String prefix = "JC";

    //私钥设置  TODO

    private final static String secretKey = "MyJwtSecret";

    //超时时间
    private final static Integer expiry = 1000 * 60 * 60 * 24;

    public static String getPrefix() {
        return prefix;
    }

    public static String getSecretKey() {
        return secretKey;
    }

    public static Integer getExpiry() {
        return expiry;
    }
}
