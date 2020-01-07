package com.ego.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户Controller
 *
 * @author zhoubin
 * @create 2020/1/2
 * @since 1.0.0
 */
@Controller
@RequestMapping("user")
public class UserController {


	/**
	 * 用户退出
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		return "redirect:"+request.getSession().getServletContext().getAttribute("portalUrl")+"/user/logout";
	}


}