package com.ego.manager.controller;

import com.ego.common.result.BaseResult;
import com.ego.manager.pojo.GoodsCategory;
import com.ego.manager.service.GoodsCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品分类
 *
 * @author sun
 * @create 2019/12/25
 * @since 1.0.0
 */
@Controller
@RequestMapping("category")
public class GoodsCategoryController {

    @Resource
    private GoodsCategoryService goodsCategoryService;

    /**
     * 跳转列表页面
     *
     * @return
     */
    @RequestMapping("list")
    public String list(Model model) {
        // 查询所有商品分类
        model.addAttribute("gcvList", goodsCategoryService.selectAllList());
        return "goods/category/category-list";
    }

    /**
     * 跳转新增页面
     *
     * @return
     */
    @RequestMapping("add")
    public String add(Model model) {
        // 查询所有顶级分类
        model.addAttribute("gcList", goodsCategoryService.selectTopList());
        return "goods/category/category-add";
    }

    /**
     * 商品分类查询-通过父id查询所有分类
     *
     * @param parentId
     * @return
     */
    @RequestMapping("/{parentId}")
    @ResponseBody
    public List<GoodsCategory> selectListByParentId(@PathVariable Short parentId) {
        return goodsCategoryService.selectListByParentId(parentId);
    }

    /**
     * 保存
     * @param goodsCategory
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public BaseResult save(GoodsCategory goodsCategory) {
        return goodsCategoryService.save(goodsCategory);
    }

}
