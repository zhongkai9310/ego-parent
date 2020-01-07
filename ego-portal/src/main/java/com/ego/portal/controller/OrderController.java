package com.ego.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 跳转订单Controller
 *
 * @author zhoubin
 * @create 2020/1/4
 * @since 1.0.0
 */
@Controller
@RequestMapping("order")
public class OrderController {

	/**
	 * 跳转到订单系统
	 * @param request
	 * @return
	 */
	@RequestMapping("toPreOrder")
	public String toPreOrder(HttpServletRequest request) {
		return "redirect:" + request.getSession().getServletContext().getAttribute("orderUrl") + "order/preOrder";
	}


	/**
	 * 回调页面
	 * @return
	 */
	@RequestMapping("callback")
	public String callback(Model model){
		/**
		 * 这边要处理对应订单对象里的状态
		 */
		System.out.println("异步回调成功，跳转前台门户页面");
		model.addAttribute("result","success");
		return "order/callback";
	}

}