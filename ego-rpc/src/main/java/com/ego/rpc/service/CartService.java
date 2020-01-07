package com.ego.rpc.service;

import com.ego.common.pojo.Admin;
import com.ego.common.result.BaseResult;
import com.ego.rpc.vo.CartResult;
import com.ego.rpc.vo.CartVo;

/**
 * 购物车service
 *
 * @Author: sun
 * @DateTime: 2020/1/4 20:31
 * @since 1.0.0
 */
public interface CartService {

    /**
     * 加入购物车
     * @param cart
     * @param admin
     * @return
     */
    BaseResult addCart(CartVo cart, Admin admin);

    /**
     * 获取购物车数量
     * @param admin
     * @return
     */
    Integer getCartNum(Admin admin);

    /**
     * 获取购物车列表
     * @param admin
     * @return
     */
    CartResult getCartList(Admin admin);

    /**
     * 清除购物车
     * @param admin
     * @return
     */
    BaseResult clearCart(Admin admin);

}
