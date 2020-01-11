package com.jinchi.common.feign;

import com.alibaba.fastjson.JSONObject;
import com.jinchi.common.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.UnsupportedEncodingException;

/**
 * @author XudongHu
 * @className RepoInService
 * @apiNote Feign接口
 * @modifer
 * @since 2019/10/17 15:03
 */
@FeignClient(value = "REPOAPI-SERVICE",configuration = FeignConfig.class)
public interface FeignRepoInService {
    @PostMapping("/repo/out")
    byte[] outGoods(@RequestBody JSONObject jsonObject) throws UnsupportedEncodingException;
}
