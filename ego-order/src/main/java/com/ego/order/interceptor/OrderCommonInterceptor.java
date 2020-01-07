package com.ego.order.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前台全局拦截器
 *
 * @Author: sun
 * @DateTime: 2020/1/7 20:00
 * @since 1.0.0
 */
@Component
public class OrderCommonInterceptor implements HandlerInterceptor {

    @Value("${ego.portal.url}")
    private String egoPortalUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ServletContext servletContext = request.getSession().getServletContext();
        String portalUrl = (String) servletContext.getAttribute("portalUrl");
        if (StringUtils.isEmpty(portalUrl)) {
            servletContext.setAttribute("portalUrl", egoPortalUrl);
        }
        return true;
    }
}
