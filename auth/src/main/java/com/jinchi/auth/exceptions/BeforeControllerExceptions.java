package com.jinchi.auth.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinchi.auth.domain.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author：XudongHu
 * @className:beforeContorollerExceptions
 * @description:   处理未进入controller之前的异常
 * @date:23:00 2018/12/17
 */
@Component
public class BeforeControllerExceptions extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JcExceptions e) {

            //返回json形式的错误信息
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");

            Result result = new Result();
            result.setCode(e.getCode());
            result.setMessage(e.getMessage());

            response.getWriter().write(convertObjectToJson(result));
        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
