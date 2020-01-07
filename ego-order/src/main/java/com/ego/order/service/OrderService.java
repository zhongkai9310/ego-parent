package com.ego.order.service;

import com.ego.common.pojo.Admin;
import com.ego.common.result.BaseResult;
import com.ego.order.pojo.Order;
import com.ego.rpc.vo.CartResult;

/**
 * 订单service
 *
 * @Author: sun
 * @DateTime: 2020/1/7 20:32
 * @since 1.0.0
 */
public interface OrderService {

    /**
     * 保存订单
     * @param cartResult
     * @param admin
     * @return
     */
    BaseResult saveOrder(CartResult cartResult, Admin admin);


    /**
     * 通过订单编号查询订单
     * @param orderSn
     * @return
     */
    Order selectOrderByOrderSn(String orderSn);

}
