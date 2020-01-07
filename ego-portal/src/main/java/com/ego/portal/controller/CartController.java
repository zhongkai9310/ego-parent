package com.ego.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.Admin;
import com.ego.common.result.BaseResult;
import com.ego.rpc.service.CartService;
import com.ego.rpc.vo.CartResult;
import com.ego.rpc.vo.CartVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 购物车Controller
 *
 * @Author: sun
 * @DateTime: 2020/1/7 19:18
 * @since 1.0.0
 */
@Controller
@RequestMapping("cart")
public class CartController {

    @Reference(interfaceClass = CartService.class)
    private CartService cartService;

    /**
     * 加入购物车
     *
     * @param cartVo
     * @param request
     * @return
     */
    @RequestMapping("addCart")
    @ResponseBody
    public BaseResult addCart(CartVo cartVo, HttpServletRequest request) {
        cartVo.setAddTime(new Date());
        Admin admin = (Admin) request.getSession().getAttribute("user");
        return cartService.addCart(cartVo, admin);
    }

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

    /**
     * 跳转至购物车列表页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("getCartList")
    public String getCartList(HttpServletRequest request, Model model) {
        Admin admin = (Admin) request.getSession().getAttribute("user");
        CartResult cartResult = cartService.getCartList(admin);
        model.addAttribute("cartResult", null == cartResult ? new CartResult() : cartResult);
        return "cart/list";
    }

}
