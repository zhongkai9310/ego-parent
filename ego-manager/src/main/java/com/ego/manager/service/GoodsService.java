package com.ego.manager.service;

import com.ego.common.result.BaseResult;
import com.ego.manager.pojo.Goods;

/**
 * 商品Service
 *
 * @Author: sun
 * @DateTime: 2020/1/3 21:42
 * @since 1.0.0
 */
public interface GoodsService {
    /**
     * 商品列表-保存
     * @param goods
     * @return
     */
    BaseResult save(Goods goods);

    /**
     * 商品列表-分页搜索
     * @param goods
     * @param pageNum
     * @param pageSize
     * @return
     */
    BaseResult search(Goods goods, Integer pageNum, Integer pageSize);
}
