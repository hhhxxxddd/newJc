package com.jinchi.app.config;

import com.jinchi.app.domain.AppDownloadInfo;
import com.jinchi.app.mapper.AppDownloadInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class AppDownLoadInterceptor implements HandlerInterceptor {
    @Autowired
    AppDownloadInfoMapper infoMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //插表
        AppDownloadInfo info = new AppDownloadInfo();
        info.setAppName(request.getRequestURI());
        info.setDate(new Date());
        info.setHost(request.getRemoteHost());
        infoMapper.insert(info);
        return true;
    }
}
