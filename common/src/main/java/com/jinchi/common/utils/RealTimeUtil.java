package com.jinchi.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jinchi.common.dto.RealTimeData;
import org.apache.commons.codec.Charsets;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jinchi.common.constant.InstrumentEnum.*;

/**
 * http请求方法
 * 用于实时数据库的请求
 */
public class RealTimeUtil {

    static RestTemplate restTemplate = getRestTemplate();

    public static Map<String,String> param = setParam();

    private static RestTemplate getRestTemplate(){
        HttpClient httpClient = HttpClientBuilder.create().build();
        ClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        //解决中文乱码
        restTemplate.getMessageConverters()
                .set(1, new StringHttpMessageConverter(Charsets.UTF_8));
        return restTemplate;
    }
    /**
     * 试试数据库请求方法封装
     * @param url 请求的url
     * @param param 所需要传的参数，键值对形式
     * @return
     */
    public static Float timelyHttpMethod(String url, String param){
        url += ("=" + param);
        System.out.println(url);
        String response = restTemplate.getForObject(url, String.class);
        if("[]".equals(response)){
            return null;
        }
        response = response.replaceAll("\\[","");
        response = response.replaceAll("]","");
        response = response.replaceAll("\\{","");
        response = response.replaceAll("}","");
        String[] strs = response.split(",");
        String value = null;
        for(int i=0;i<strs.length;i++){
            if(strs[i].contains("Value")){
               value = strs[i];
               break;
            }
        }
        String[] ans = value.split(":");
        return Float.parseFloat(ans[1].replaceAll("\"","").trim());
    }

    public static Float dcsForAnode(String url, String point, Date date){
        //传参url示例 192.168.1.1/snapshot xx.xxx 2019-10-10
        url += "tagName=";
        url += point;
        url += "?";
        url += "beginTime=";
        url += ComUtil.dateToString(date,"yyyy/MM/dd");
        System.out.println(url);
        String response = restTemplate.getForObject(url, String.class);
        if("[]".equals(response)){
            return null;
        }
        response = response.replaceAll("\\[","");
        response = response.replaceAll("]","");
        response = response.replaceAll("\\{","");
        response = response.replaceAll("}","");
        String[] strs = response.split(",");
        String value = null;
        for(int i=0;i<strs.length;i++){
            if(strs[i].contains("Value")){
                value = strs[i];
                break;
            }
        }
        String[] ans = value.split(":");
        return Float.parseFloat(ans[1].replaceAll("\"","").trim());
    }

    public static Map getParam(){
        return param;
    }

    private static Map setParam(){
        String labels = get();
        Map<String,String> map = new HashMap<>();
        map.put("tagName",labels);
        return map;
    }
    public static String get(){
        String labels = "";
        labels += HCC701_AM1.getPath()+","+HCC701_AM2.getPath()+","+HCC701_AMA.getPath()+","+HCC701_AMW.getPath()+","+HCC701_PH.getPath()+","+HCC701_SALTFLOW1.getPath()+","+HCC701_SALTFLOW2.getPath()+","+HCC701_SALTFLOW3.getPath()+","+HCC701_SALTFLOW4.getPath()+","+HCC701_TP.getPath();//      labels += ",";
        labels += ",";
        labels += HCC702_AM1.getPath()+","+HCC702_AM2.getPath()+","+HCC702_AMA.getPath()+","+HCC702_AMW.getPath()+","+HCC702_PH.getPath()+","+HCC702_SALTFLOW1.getPath()+","+HCC702_SALTFLOW2.getPath()+","+HCC702_SALTFLOW3.getPath()+","+HCC702_SALTFLOW4.getPath()+","+HCC702_TP.getPath();
        labels += ",";
        labels += HCC703_AM1.getPath()+","+HCC703_AM2.getPath()+","+HCC703_AMA.getPath()+","+HCC703_AMW.getPath()+","+HCC703_PH.getPath()+","+HCC703_SALTFLOW1.getPath()+","+HCC703_SALTFLOW2.getPath()+","+HCC703_SALTFLOW3.getPath()+","+HCC703_SALTFLOW4.getPath()+","+HCC703_TP.getPath();
        labels += ",";
        labels += HCC704_AM1.getPath()+","+HCC704_AM2.getPath()+","+HCC704_AMA.getPath()+","+HCC704_AMW.getPath()+","+HCC704_PH.getPath()+","+HCC704_SALTFLOW1.getPath()+","+HCC704_SALTFLOW2.getPath()+","+HCC704_SALTFLOW3.getPath()+","+HCC704_SALTFLOW4.getPath()+","+HCC704_TP.getPath();
        labels  += ",";
        labels += HCC705_AM1.getPath()+","+HCC705_AM2.getPath()+","+HCC705_AMA.getPath()+","+HCC705_AMW.getPath()+","+HCC705_PH.getPath()+","+HCC705_SALTFLOW1.getPath()+","+HCC705_SALTFLOW2.getPath()+","+HCC705_SALTFLOW3.getPath()+","+HCC705_SALTFLOW4.getPath()+","+HCC705_TP.getPath();
        labels += ",";
        labels += HCC706_AM1.getPath()+","+HCC706_AM2.getPath()+","+HCC706_AMA.getPath()+","+HCC706_AMW.getPath()+","+HCC706_PH.getPath()+","+HCC706_SALTFLOW1.getPath()+","+HCC706_SALTFLOW2.getPath()+","+HCC706_SALTFLOW3.getPath()+","+HCC706_SALTFLOW4.getPath()+","+HCC706_TP.getPath();
        labels += ",";
        labels += HCC707_AM1.getPath()+","+HCC707_AM2.getPath()+","+HCC707_AMA.getPath()+","+HCC707_AMW.getPath()+","+HCC707_PH.getPath()+","+HCC707_SALTFLOW1.getPath()+","+HCC707_SALTFLOW2.getPath()+","+HCC707_SALTFLOW3.getPath()+","+HCC707_SALTFLOW4.getPath()+","+HCC707_TP.getPath();
        labels += ",";
        labels += HCC708_AM1.getPath()+","+HCC708_AM2.getPath()+","+HCC708_AMA.getPath()+","+HCC708_AMW.getPath()+","+HCC708_PH.getPath()+","+HCC708_SALTFLOW1.getPath()+","+HCC708_SALTFLOW2.getPath()+","+HCC708_SALTFLOW3.getPath()+","+HCC708_SALTFLOW4.getPath()+","+HCC708_TP.getPath();
        labels += ",";
        labels += HCC801_AM1.getPath()+","+HCC801_AM2.getPath()+","+HCC801_AMA.getPath()+","+HCC801_AMW.getPath()+","+HCC801_PH.getPath()+","+HCC801_SALTFLOW1.getPath()+","+HCC801_SALTFLOW2.getPath()+","+HCC801_SALTFLOW3.getPath()+","+HCC801_SALTFLOW4.getPath()+","+HCC801_TP.getPath();
        labels += ",";
        labels += HCC802_AM1.getPath()+","+HCC802_AM2.getPath()+","+HCC802_AMA.getPath()+","+HCC802_AMW.getPath()+","+HCC802_PH.getPath()+","+HCC802_SALTFLOW1.getPath()+","+HCC802_SALTFLOW2.getPath()+","+HCC802_SALTFLOW3.getPath()+","+HCC802_SALTFLOW4.getPath()+","+HCC802_TP.getPath();
        labels += ",";
        labels += HCC803_AM1.getPath()+","+HCC803_AM2.getPath()+","+HCC803_AMA.getPath()+","+HCC803_AMW.getPath()+","+HCC803_PH.getPath()+","+HCC803_SALTFLOW1.getPath()+","+HCC803_SALTFLOW2.getPath()+","+HCC803_SALTFLOW3.getPath()+","+HCC803_SALTFLOW4.getPath()+","+HCC803_TP.getPath();
        labels += ",";
        labels += HCC804_AM1.getPath()+","+HCC804_AM2.getPath()+","+HCC804_AMA.getPath()+","+HCC804_AMW.getPath()+","+HCC804_PH.getPath()+","+HCC804_SALTFLOW1.getPath()+","+HCC804_SALTFLOW2.getPath()+","+HCC804_SALTFLOW3.getPath()+","+HCC804_SALTFLOW4.getPath()+","+HCC804_TP.getPath();
        labels += ",";
        labels += HCC805_AM1.getPath()+","+HCC805_AM2.getPath()+","+HCC805_AMA.getPath()+","+HCC805_AMW.getPath()+","+HCC805_PH.getPath()+","+HCC805_SALTFLOW1.getPath()+","+HCC805_SALTFLOW2.getPath()+","+HCC805_SALTFLOW3.getPath()+","+HCC805_SALTFLOW4.getPath()+","+HCC805_TP.getPath();
        labels += ",";
        labels += HCC806_AM1.getPath()+","+HCC806_AM2.getPath()+","+HCC806_AMA.getPath()+","+HCC806_AMW.getPath()+","+HCC806_PH.getPath()+","+HCC806_SALTFLOW1.getPath()+","+HCC806_SALTFLOW2.getPath()+","+HCC806_SALTFLOW3.getPath()+","+HCC806_SALTFLOW4.getPath()+","+HCC806_TP.getPath();
        labels += ",";
        labels += HCC807_AM1.getPath()+","+HCC807_AM2.getPath()+","+HCC807_AMA.getPath()+","+HCC807_AMW.getPath()+","+HCC807_PH.getPath()+","+HCC807_SALTFLOW1.getPath()+","+HCC807_SALTFLOW2.getPath()+","+HCC807_SALTFLOW3.getPath()+","+HCC807_SALTFLOW4.getPath()+","+HCC807_TP.getPath();
        labels += ",";
        labels += HCC808_AM1.getPath()+","+HCC808_AM2.getPath()+","+HCC808_AMA.getPath()+","+HCC808_AMW.getPath()+","+HCC808_PH.getPath()+","+HCC808_SALTFLOW1.getPath()+","+HCC808_SALTFLOW2.getPath()+","+HCC808_SALTFLOW3.getPath()+","+HCC808_SALTFLOW4.getPath()+","+HCC808_TP.getPath();
        return labels;
    }

    public static void main(String[] args) {
        //System.out.println(param);
        /*List ans = timelyHttpMethod("http://192.168.190.173:10086/api/Snapshot",param, RealTimeData.class);
        System.out.println(ans.size());
        for(int i=0;i<ans.size();i++){
            System.out.println(ans.get(i));
        }*/

        /*String l = get();
        String[] ls = l.split(",");
        for(int i=0;i<ls.length;i++){
            Map<String,String> map = new HashMap<>();
            map.put("tagName",ls[i]);
            List ans = timelyHttpMethod("http://192.168.190.173:10086/api/Snapshot",map, RealTimeData.class);
            System.out.println(ans.size());
        }*/


        Map<String,String> param = new HashMap<>();
        String param1 = "qianqu_1.BFB_TJLT_CF207";
        Float data = RealTimeUtil.timelyHttpMethod("http://192.168.190.173:10086/api/Snapshot?tagName",param1);
        System.out.println(data);
    }
}
