package com.ego.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.Admin;
import com.ego.rpc.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 购物车Controller
 *
 * @author zhoubin
 * @create 2020/1/4
 * @since 1.0.0
 */
@Controller
@RequestMapping("cart")
public class CartController {
	@Reference(interfaceClass = CartService.class)
	private CartService cartService;

	/**
	 * 获取购物车数量
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("getCartNum")
	@ResponseBody
	public Integer getCartNum(HttpServletRequest request) {
		Admin admin = (Admin) request.getSession().getAttribute("user");
		return cartService.getCartNum(admin);
	}

}