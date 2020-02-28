package com.jc.api.config;

import com.alibaba.fastjson.JSONObject;
import com.jc.api.exception.custom.ConnectFailException;
import com.jc.api.service.feignservice.ICommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author XudongHu
 * @className Fall
 * @apiNote Feign单独设置熔断结果
 * @modifer
 * @since 2019/10/20 0:34
 */
@Component
@Slf4j
public class FallBack implements ICommonService {


    @Override
    public String outAudit(@RequestBody JSONObject jsonObject) {
        log.error("调用失败,主服务器未响应");
        throw new ConnectFailException("调用出库审核接口失败,连接中断");
    }

    @Override
    public String productionLine(@RequestParam Integer id) {
        log.error("调用产线查询接口失败,连接中断");
        return "加载失败";
    }

    @Override
    public String endPosition(@RequestParam Integer id) {
        log.error("调用出库点查询接口失败,连接中断");
        return "加载失败";
    }

    @Override
    public String personName(@RequestParam Integer id) {
        log.error("调用创建人查询接口失败,连接中断");
        return "加载失败";
    }

    @Override
    public String deptName(Integer id) {
        return null;
    }

    @Override
    public String fireLine(Integer id) {
        return null;
    }

    @Override
    public String line(Integer id) {
        return null;
    }

    @Override
    public String send2audit(Integer personId, Integer isUrgent, Integer auditId) {
        return null;
    }
}



