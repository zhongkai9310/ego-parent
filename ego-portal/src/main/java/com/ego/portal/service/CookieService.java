package com.ego.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CookieService
 *
 * @Author: sun
 * @DateTime: 2020/1/3 19:54
 * @since 1.0.0
 */
public interface CookieService {

    /**
     * 设置cookie
     *
     * @param ticket
     * @param request
     * @param response
     * @return
     */
    Boolean setCookie(String ticket, HttpServletRequest request, HttpServletResponse response);

    /**
     * 删除cookie
     *
     * @param request
     * @param response
     * @return
     */
    Boolean deleteCookie(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取cookie
     *
     * @param request
     * @return
     */
    String getCookie(HttpServletRequest request);

}
