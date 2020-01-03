package com.ego.manager.service.impl;

import com.ego.common.util.CookieUtil;
import com.ego.manager.service.CookieService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CookieService实现类
 *
 * @Author: sun
 * @DateTime: 2020/1/3 19:57
 * @since 1.0.0
 */
@Service
public class CookieServiceImpl implements CookieService {

    /**
     * 设置cookie
     *
     * @param ticket
     * @param request
     * @param response
     * @return
     */
    @Override
    public Boolean setCookie(String ticket, HttpServletRequest request, HttpServletResponse response) {
        try {
            CookieUtil.setCookie(request, response, "userTicket", ticket);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除cookie
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public Boolean deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        try {
            CookieUtil.deleteCookie(request, response, "userTicket");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取cookie
     *
     * @param request
     * @return
     */
    @Override
    public String getCookie(HttpServletRequest request) {
        return CookieUtil.getCookieValue(request, "userTicket");
    }
}
