package com.jinchi.auth.cache;

import org.springframework.cache.annotation.Cacheable;

/**
 * @author：XudongHu
 * @className:CacheTest
 * @description:
 * @date:16:52 2019/3/12
 * @Modifer:
 */
public class CacheTest {
    @Cacheable(cacheNames = {"compressedToken"})
    public String tokenCache(Integer userId){
        return userId.toString()+"<=这个员工的缓存";
    }
}
