package com.jc.api.service.feignservice;

import com.alibaba.fastjson.JSONObject;
import com.jc.api.config.FallBack;
import com.jc.api.config.FeignConfig;
import io.swagger.models.auth.In;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author XudongHu
 * @className ICommonService
 * @apiNote Feign接口 访问common模块
 * @modifer
 * @since 2019/10/17 15:03
 */
@FeignClient(value = "COMMON-SERVICE", configuration = FeignConfig.class, fallback = FallBack.class)
public interface ICommonService {
    /**
     * 提交出库审核
     */
    // 注意加utf-8 不然String 中文编码会失败,consumer表示发送 produces表示接收
    @RequestMapping(value = "/jc/outAudit", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    String outAudit(@RequestBody JSONObject jsonObject);

    //查询产线
    @RequestMapping(value = "/jc/productionLine", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    String productionLine(@RequestParam Integer id);
    //查询出库点
    @RequestMapping(value = "/jc/endPosition", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    String endPosition(@RequestParam Integer id);
    //查询创建人
    @RequestMapping(value = "/jc/personName", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    String personName(@RequestParam Integer id);

    /*@RequestMapping(value = "/redis/feign",method = RequestMethod.GET)
    String feign(@RequestParam String msg);*/
    //查询部门名称
    @RequestMapping(value = "/jc/deptName",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    String deptName(@RequestParam Integer id);

    //查询系统部门
    @RequestMapping(value = "/jc/sysDept",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    String sysDept(@RequestParam Integer id);

    //查询火法产线
    @RequestMapping(value = "/jc/fireLine",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    String fireLine(@RequestParam Integer id);

    //查询湿法产线
    @RequestMapping(value = "/jc/line",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    String line(@RequestParam Integer id);

    //出库送审
    @RequestMapping(value = "/jc/send2audit",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    String send2audit(@RequestParam Integer personId,
                      @RequestParam Integer isUrgent,
                      @RequestParam Integer auditId,
                      @RequestParam Integer flag);

    //校验批号
    @RequestMapping(value = "/jc/validateBatch",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    Boolean validateBatch(@RequestParam String batch);
}
