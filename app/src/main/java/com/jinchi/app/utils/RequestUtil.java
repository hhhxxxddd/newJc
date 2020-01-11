package com.jinchi.app.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author：XudongHu
 * @className:RequestUtil
 * @description:
 * @date:19:35 2019/3/17
 * @Modifer:
 */
public class RequestUtil {
    public Object request(HttpServletRequest request) {
        request.getParameterMap().entrySet().stream().forEach(e ->
        {
            e.getKey();
            e.getValue();
            Arrays.toString(e.getValue());
        });
        return null;
    }
}
