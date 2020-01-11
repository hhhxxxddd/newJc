package com.jinchi.common.utils;

/**
 * @author：XudongHu
 * @className:WeChatUtil
 * @description:
 * @date:23:28 2019/6/3
 * @Modifer:
 */
public class WeChatUtil {

    /**
     * 微信公众号推送消息给客户
     *
     * @return
     *
     */
//    public static void sendTextMessageToUser(String url, String openId, String firstData, String keyword1Data,
//                                             String keyword2Data, String keyword3Data, String remarkData) {
//        String json = "{\"template_id\": \"uZBaZtqpJPHME3-MyPsfUFvLOtvpwnhvp1EoDLYsP60\",\"url\": \""+url+"\",\"touser\": \""+openId+"\",\"msgtype\": \"text\", \"data\":{\"first\": {\"value\":\""+firstData+"\"},\"keyword1\":{\"value\":\""+keyword1Data+"\"},\"keyword2\":{\"value\":\""+keyword2Data+"\"},\"keyword3\":{\"value\":\""+keyword3Data+"\"},\"remark\":{\"value\":\""+remarkData+"\"}}}";
//        Map<String, Object> map = new HashMap<>();
//        map.put("template_id", "uZBaZtqpJPHME3-MyPsfUFvLOtvpwnhvp1EoDLYsP60");
//        map.put("toUser", openId);
//        map.put("url", url);
//
//        Map<String, Object> data = new HashMap<>();
//        Map<String, String> first = new HashMap<>();
//        first.put("value", firstData);
//        data.put("first", first);
//
//        Map<String, String> keyword1 = new HashMap<>();
//        keyword1.put("value", keyword1Data);
//        data.put("keyword1", keyword1);
//
//        Map<String, String> keyword2 = new HashMap<>();
//        keyword2.put("value", keyword2Data);
//        data.put("keyword2", keyword2);
//
//        Map<String, String> keyword3 = new HashMap<>();
//        keyword3.put("value", keyword3Data);
//        data.put("keyword3", keyword3);
//
//        Map<String, String> remark = new HashMap<>();
//        remark.put("value", remarkData);
//        data.put("remark", remark);
//
//        map.put("data", data);
//        JSONObject jsonObject = JSONObject.fromObject(map);
//        String jString = jsonObject.toString();
//        jString = jString.replace("\"", "\\\"");
//        System.out.println(jString);
//
//        try {
//            String accessToken = HttpRequestUtil.getToken();
//            // 获取请求路径
//            String action = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
//            connectWeiXinInterface(action, json);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获得ACCESS_TOKEN
//     *
//     * @Title: getAccess_token @Description: 获得ACCESS_TOKEN @param @return
//     * 设定文件 @return String 返回类型 @throws
//     */
//    public static String getAccess_token() {
//
//        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WeChatConfig.APP_ID
//                + "&secret=" + WeChatConfig.APP_SECRET;
//        String accessToken = null;
//        try {
//            URL urlGet = new URL(url);
//            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
//            http.setRequestMethod("GET"); // 必须是get方式请求
//            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            http.setDoOutput(true);
//            http.setDoInput(true);
//            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
//            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
//            http.connect();
//            InputStream is = http.getInputStream();
//            int size = is.available();
//            byte[] jsonBytes = new byte[size];
//            is.read(jsonBytes);
//            String message = new String(jsonBytes, "UTF-8");
//            JSONObject demoJson = JSONObject.fromObject(message);
//            accessToken = demoJson.getString("access_token");
//            System.out.println(accessToken);
//            is.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return accessToken;
//    }
//
//    /**
//     *
//     * 连接请求微信后台接口
//     *
//     * @param action
//     *            接口url
//     *
//     * @param json
//     *            请求接口传送的json字符串
//     * @throws JcExceptions
//     *
//     */
//    public static void connectWeiXinInterface(String action, String json) throws JcExceptions {
//        URL url;
//        try {
//            url = new URL(action);
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            http.setRequestMethod("POST");
//            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            http.setDoOutput(true);
//            http.setDoInput(true);
//            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
//            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
//            http.connect();
//            OutputStream os = http.getOutputStream();
//            os.write(json.getBytes("UTF-8"));// 传入参数
//            InputStream is = http.getInputStream();
//            int size = is.available();
//            byte[] jsonBytes = new byte[size];
//            is.read(jsonBytes);
//            String result = new String(jsonBytes, "UTF-8");
//            System.out.println("请求返回结果:" + result);
//            os.flush();
//            os.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new EnumExceptions("推送失败");
//        }
//    }
//
//    /**
//     * 给用户打上标签
//     * @return
//     */
//    public static boolean putUserLable(String openId, Integer tagId){
//        try {
//            String token = (String) CacheUtils.get("weixinToken");
//            if (StringUtils.isEmpty(token)) {
//                token = HttpRequestUtil.getToken();
//            }
//            String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=" + token;
//            List<String> users = new ArrayList<>();
//            users.add(openId);
//            Map<String, Object> map = new HashMap<>();
//            map.put("openid_list", users);
//            map.put("tagid", tagId);
//            JSONObject jsonObject = JSONObject.fromObject(map);
//            String data = HttpRequestUtil.httpRequest(url, "POST", null, jsonObject);
//            System.out.println("给用户打上标签，微信返回数据-----" + data);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
