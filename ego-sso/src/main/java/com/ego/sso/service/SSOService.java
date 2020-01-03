package com.ego.sso.service;

import com.ego.sso.pojo.Admin;

/**
 * 单点登录系统service
 *
 * @Author: sun
 * @DateTime: 2020/1/2 20:15
 * @since 1.0.0
 */
public interface SSOService {
    /**
     * 验证用户信息返回ticket
     * @param admin
     * @return
     */
    String login(Admin admin);


    /**
     * 验证票据信息返回用户信息
     * @param ticket
     * @return
     */
    Admin validate(String ticket);
}
