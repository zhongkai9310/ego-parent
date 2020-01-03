package com.ego.manager.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台系统登录拦截器
 *
 * @Author: sun
 * @DateTime: 2020/1/2 19:58
 * @since 1.0.0
 */
@Component
public class ManagerLoginInterceptor implements HandlerInterceptor {

    /**
     * 请求处理之前的拦截器，false直接返回，true继续执行下一个拦截器或者目标程序
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return false;
    }
}
