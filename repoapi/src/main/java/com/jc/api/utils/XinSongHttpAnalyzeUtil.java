package com.jc.api.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XudongHu
 * @apiNote 解析新松Http请求工具
 * @className HttpAnalyzeUtil
 * @modifier
 * @since 19.12.6日23:54
 */
@Slf4j
public class XinSongHttpAnalyzeUtil {
    //入库接收者
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StockInReceiver {
        private String operator;     //操作人
        private String materialCode; //物料编码
        private String stockInTime;  //入库时间
    }
    //出库接收者
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StockOutReceiver{
        private String planCode; //单号
        private String goodsCode;  //物料编码
    }
    //出库完成接收者
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StockOutFinishedReceiver {
        private String planCode; //单号
    }

    //入库接收
    public static StockInReceiver stockInContentLoading(HttpServletRequest request) {
        Map<String,String> stockInMap = new HashMap<>();
        stockInMap.put("Operator", ""); //操作人
        stockInMap.put("Goods_Code", ""); //物料编码
        stockInMap.put("Manage_End_Time", "");  //入库时间
        log.info("智能仓库接收入库信息:");
        Map<String, String> map = contentLoading(stockInMap, request);
        map.forEach((k,v)->log.info(k+":"+v));
        return new StockInReceiver(stockInMap.get("Operator"),stockInMap.get("Goods_Code"),stockInMap.get("Manage_End_Time"));
    }
    //出库上报接收
    public static StockOutReceiver stockOutContentLoading(HttpServletRequest request){
        Map<String, String> stockOutMap = new HashMap<>();
        stockOutMap.put("PlanCode", "");  //单号
        stockOutMap.put("GoodsCode", ""); //物料编码
        log.info("智能仓库接收出库上报:");
        Map<String, String> map = contentLoading(stockOutMap, request);
        map.forEach(log::info);
        return new StockOutReceiver(stockOutMap.get("PlanCode"),stockOutMap.get("GoodsCode"));
    }
    //出库完成接收
    public static StockOutFinishedReceiver stockOutFinishedContentLoading(HttpServletRequest request){
        Map<String, String> stockOutFinishedMap = new HashMap<>();
        stockOutFinishedMap.put("PlanCode", "");  //单号
        log.info("智能仓库接收出库完成上报:");
        Map<String, String> map = contentLoading(stockOutFinishedMap, request);
        map.forEach(log::info);
        return new StockOutFinishedReceiver(stockOutFinishedMap.get("PlanCode"));
    }
    //私有接收方法
    private static Map<String,String> contentLoading(Map<String,String> contentMap,HttpServletRequest request){
        request.getParameterMap().forEach((k, v) -> {
            String key = k.replace("\"", "");
            String value = Arrays.toString(v).replace("[", "").replace("]", "").replace("\"", "");
            log.info("key:{},value:{}",key,value);
            if (!contentMap.getOrDefault(key,"NotExist").equals("NotExist")) contentMap.put(key,value);
        });
        return contentMap;
    }
}
