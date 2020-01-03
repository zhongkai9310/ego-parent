package com.ego.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.rpc.service.GoodsCategroyService;
import com.ego.rpc.vo.GoodsCategoryVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 商品Controller
 *
 * @Author: sun
 * @DateTime: 2020/1/3 20:06
 * @since 1.0.0
 */
@Controller
@RequestMapping("goodsCategory")
public class GoodsController {

    @Reference(interfaceClass = GoodsCategroyService.class)
    private GoodsCategroyService goodsCategroyService;

    /**
     * 商品分类列表
     *
     * @return
     */
    public List<GoodsCategoryVo> goodsCategoryList() {
        return goodsCategroyService.selectAllList();
    }

}
