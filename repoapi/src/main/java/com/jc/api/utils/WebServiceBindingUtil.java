package com.jc.api.utils;

import com.jc.api.service.webservice.IERPService;
import com.jc.api.service.webservice.SERPService;

/**
 * @author XudongHu
 * @className WebServiceBindingUtil
 * @apiNote 调用webservice接口工具
 * @modifer
 * @since 2019/10/30日14:56
 */
public class WebServiceBindingUtil {
    //接口调用
    private static IERPService ierpService(){
        SERPService serpService = new SERPService();
        return serpService.getBasicHttpBindingIERPService();
    }
    /**
     * 下发出库计划
     * @param jsonPara
     * @return
     */
    public static String outPlan(String jsonPara){
        return ierpService().setIOTaskPost(jsonPara);
    }

    /**
     * 下发出库取消计划
     * @param jsonPara
     * @return
     */
    public static String outPlanCancel(String jsonPara){
        return ierpService().deletePlanOut(jsonPara);
    }

    /**
     * 下发库存查询
     * @param jsonPara
     * @return
     */
    public static String queryStock(String jsonPara){
        return ierpService().storageCheckPost(jsonPara);
    }

    /**
     * 下发红单处理
     * @param jsonPara
     * @return
     */
    public static String redTable(String jsonPara){
        return ierpService().setStorageCostPost(jsonPara);
    }
}
