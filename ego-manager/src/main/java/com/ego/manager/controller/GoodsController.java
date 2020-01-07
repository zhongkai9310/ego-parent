package com.ego.manager.controller;

import com.ego.common.result.BaseResult;
import com.ego.common.result.FileResult;
import com.ego.manager.pojo.Goods;
import com.ego.manager.pojo.GoodsImages;
import com.ego.manager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 商品Controller
 *
 * @Author: sun
 * @DateTime: 2020/1/4 17:01
 * @since 1.0.0
 */
@Controller
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsImagesService goodsImagesService;

    @Autowired
    private UploadService uploadService;

    /**
     * 商品列表-跳转列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String list(Model model) {
        // 查询所有分类
        model.addAttribute("gcList", goodsCategoryService.selectList());
        // 查询所有品牌
        model.addAttribute("brandList", brandService.selectList());
        return "goods/goods-list";
    }

    /**
     * 商品列表-跳转新增页面
     *
     * @param model
     * @return
     */
    @RequestMapping("add")
    public String add(Model model) {
        // 查询所有顶级分类
        model.addAttribute("gcList", goodsCategoryService.selectTopList());
        // 查询所有品牌
        model.addAttribute("brandList", brandService.selectList());
        return "goods/goods-add";
    }

    /**
     * 商品列表-保存
     *
     * @param goods
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public BaseResult save(Goods goods) {
        return goodsService.save(goods);
    }

    /**
     * 商品列表-分页查询
     *
     * @param goods
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("search")
    @ResponseBody
    public BaseResult search(Goods goods, Integer pageNum, Integer pageSize) {
        return goodsService.search(goods, pageNum, pageSize);
    }

    /**
     * 商品相册上传(多文件上传相当于多次调用该方法)
     *
     * @param file
     * @param goodsId
     * @return
     */
    @RequestMapping("images/save")
    @ResponseBody
    public BaseResult imagesSave(MultipartFile file, Integer goodsId) throws IOException {
        String filename = file.getOriginalFilename();
        // 格式化时间
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd/").format(LocalDateTime.now());
        filename = date + System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));
        FileResult result = uploadService.upload(file.getInputStream(), filename);
        if (!StringUtils.isEmpty(result.getFileUrl())) {
            // 上传成功
            // 创建GoodsImages对象
            GoodsImages goodsImages = new GoodsImages();
            // 设置上传成功后的url
            goodsImages.setImageUrl(result.getFileUrl());
            // 设置商品id
            goodsImages.setGoodsId(goodsId);
            BaseResult baseResult = goodsImagesService.save(goodsImages);
            return baseResult;
        } else {
            // 上传失败
            return BaseResult.error();
        }
    }

}
