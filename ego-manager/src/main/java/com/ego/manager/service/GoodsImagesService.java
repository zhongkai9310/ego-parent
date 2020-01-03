package com.ego.manager.service;

import com.ego.common.result.BaseResult;
import com.ego.manager.pojo.GoodsImages;

/**
 * 商品相册Service
 *
 * @Author: sun
 * @DateTime: 2020/1/3 21:43
 * @since 1.0.0
 */
public interface GoodsImagesService {
    /**
     * 商品相册-保存
     * @param goodsImages
     * @return
     */
    BaseResult save(GoodsImages goodsImages);
}
