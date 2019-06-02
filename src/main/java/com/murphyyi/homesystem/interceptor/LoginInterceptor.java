package com.murphyyi.homesystem.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: teach-interactive-manager
 * @ClassName: LoginInterceptor
 * @description: 登录拦截器封装类
 * @author: zhangyi
 * @create: 2019-04-19 14:44
 **/
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final String OPTIONS_METHOD = "OPTIONS";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //允许跨域访问
        boolean jsonpResult = allowJsonpConfig(request, response);
        System.err.println("登录拦截器已生效");
        System.out.println(jsonpResult);
        return jsonpResult;
    }

    /**
     * @Description: 允许跨域访问
     * @Param1: request
     * @Param2: response
     * @return: boolean
     * @Author: malin
     * @Date: 2019-04-22
     */
    private boolean allowJsonpConfig(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With, token");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        response.setHeader("X-Powered-By", "Jetty");
        String method = request.getMethod();
        if (OPTIONS_METHOD.equals(method)) {
            response.setStatus(200);
            log.error("[LoginInterceptor,allowJsonpConfig]跨域访问被禁止,此类方法无法进行访问:request={}", request);
            return false;
        }
        return true;
    }
}
