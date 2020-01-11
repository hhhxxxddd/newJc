package com.jinchi.common.service.feignservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author XudongHu
 * @className RepoInService
 * @apiNote Feign接口
 * @modifer
 * @since 2019/10/17 15:03
 */
@FeignClient(value = "REPOAPI-SERVICE")
public interface IRepoOutService {
    /**
     * 返回出库审核 结果
     */
    @PostMapping("/outManage/outStart")
    boolean outStart(@RequestParam Integer applicationFormId,@RequestParam Integer status);

    @PostMapping("/outRecord/matOut")
    String matOut(@RequestParam String startTime, @RequestParam String endTime, @RequestBody List<String> matName);
}
