package com.jinchi.common.core.util;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;

/**
 * @className UserContextHolder
 * @author XudongHu
 * @apiNote 用户上下文
 *          使用此上下文我们可以捕获到每个用户的操作
 * @since 2019/10/29日21:06
 * @modifer
 */
public class UserContextHolder {

    private ThreadLocal<Map<String, String>> threadLocal;

    private UserContextHolder() {
        this.threadLocal = new ThreadLocal<>();
    }

    /**
     * 创建实例
     *
     * @return
     */
    public static UserContextHolder getInstance() {
        return SingletonHolder.sInstance;
    }

    /**
     * 静态内部类单例模式
     * 单例初使化
     */
    private static class SingletonHolder {
        private static final UserContextHolder sInstance = new UserContextHolder();
    }

    /**
     * 用户上下文中放入信息
     *
     * @param map
     */
    public void setContext(Map<String, String> map) {
        threadLocal.set(map);
    }

    /**
     * 获取上下文中的信息
     *
     * @return
     */
    public Map<String, String> getContext() {
        return threadLocal.get();
    }

    /**
     * 获取上下文中的用户名
     *
     * @return
     */
    public String getUsername() {
        return Optional.ofNullable(threadLocal.get()).orElse(Maps.newHashMap()).get("user_name");
    }

    /**
     * 清空上下文
     */
    public void clear() {
        threadLocal.remove();
    }

}
