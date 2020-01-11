package com.jinchi.common.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author：XudongHu
 * @className:WeChatController
 * @description:
 * @date:23:37 2019/6/3
 * @Modifer:
 */
@Controller
@RequestMapping(value = "/weChatMessage")
@Api(tags = "微信推送")
public class WeChatController  {
//    @Value("${gendan.id}")
//    private String gendanId;

    /**
     * 样品送检通过
     * 向微信推送消息
     *
     * @param clientcompany  客户公司名称
     * @param model          电芯型号
     * @param capacity       容量
     * @param quotationOrder 报价单号
     * @param containTax     是否含税
     * @return
     */
//    @ApiOperation(value = "推送样品送检通过信息")
//    @PostMapping
//    public AjaxJson saleSave(@RequestParam("clientcompany") String clientcompany,
//                             @RequestParam("model") String model, @RequestParam("capacity") String capacity,
//                             @RequestParam("quotationOrder") String quotationOrder,
//                             @RequestParam("containTax") String containTax,
//                             @RequestParam("number") String number,
//                             @RequestParam("clientId") String clientId) {
//        AjaxJson j = new AjaxJson();
//        try {
//            // 必填项校验
//            salesQuotationService.saleSave(clientcompany, model, capacity, quotationOrder, containTax, number, clientId);
//            j.setSuccess(true);
//            j.setMsg("成功");
//            // 公众号推送
//            String openId = userMapper.getOpenIdByRoleId(jipinId);
//            if (!StringUtils.isEmpty(openId)) {
//                String firstData = "您好，您的样品送检已审核通过.";
//                String keyword1Data = UserUtils.getUser().getName();
//                String keyword2Data = "报价单流程";
//                String keyword3Data = "待补充";
//                String remarkData = "请及时查看.TIME:" + Tools.date2Str(new java.util.Date());
//                String url = "http://wechat.leadererp.cn/quotation?quotationOrder=" + quotationOrder + "&openId="
//                        + openId;
//                WeChatTUtil.sendTextMessageToUser(url, openId, firstData, keyword1Data, keyword2Data, keyword3Data,
//                        remarkData);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            j.setSuccess(false);
//            j.setMsg("失败");
//        }
//        return j;
//    }
}
