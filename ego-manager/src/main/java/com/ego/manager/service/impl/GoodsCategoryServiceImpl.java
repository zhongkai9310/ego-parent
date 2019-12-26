package com.ego.manager.service.impl;

import com.ego.common.result.BaseResult;
import com.ego.manager.mapper.GoodsCategoryMapper;
import com.ego.manager.pojo.GoodsCategory;
import com.ego.manager.pojo.GoodsCategoryExample;
import com.ego.manager.service.GoodsCategoryService;
import com.ego.manager.vo.GoodsCategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类实现类
 */
@Service
@SuppressWarnings("all")
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    /**
     * 查询所有顶级分类
     *
     * @return
     */
    @Override
    public List<GoodsCategory> selectTopList() {
        // 创建查询对象
        GoodsCategoryExample example = new GoodsCategoryExample();
        // 设置查询条件
        example.createCriteria().andParentIdEqualTo((short) 0);
        // 返回查询结果
        return goodsCategoryMapper.selectByExample(example);
    }

    /**
     * 通过父id查询所有分类
     *
     * @param parentId
     * @return
     */
    @Override
    public List<GoodsCategory> selectListByParentId(Short patentId) {
        // 创建查询对象
        GoodsCategoryExample example = new GoodsCategoryExample();
        // 设置查询条件
        example.createCriteria().andParentIdEqualTo(patentId);
        // 返回查询结果
        return goodsCategoryMapper.selectByExample(example);
    }

    /**
     * 保存
     *
     * @param goodsCategory
     * @return
     */
    @Override
    public BaseResult save(GoodsCategory goodsCategory) {
        int row = goodsCategoryMapper.insertSelective(goodsCategory);
        return row > 0 ? BaseResult.success() : BaseResult.error();
    }

    /**
     * 查询所有分类List
     * @return
     */
    @Override
    public List<GoodsCategoryVo> selectAllList() {
        // 顶级分类的list
        List<GoodsCategoryVo> gcvList01 = new ArrayList<>();
        // 创建查询对象
        GoodsCategoryExample example = new GoodsCategoryExample();
        // 设置查询条件 where parentId = 0 and level = 1
        example.createCriteria().andParentIdEqualTo((short) 0).andLevelEqualTo((byte) 1);
        // 查询所有顶级分类
        List<GoodsCategory> gcList01 = goodsCategoryMapper.selectByExample(example);
        // 遍历查询结果，copy给vo对象
        for (GoodsCategory gc01 : gcList01) {
            // 顶级分类的vo对象
            GoodsCategoryVo gcv01 = new GoodsCategoryVo();
            // 拷贝对象
            BeanUtils.copyProperties(gc01, gcv01);
            // 清除之前的查询条件
            example.clear();
            // 二级分类的list
            List<GoodsCategoryVo> gcvList02 = new ArrayList<>();
            // 设置查询条件   where parentId = 顶级分类的id and level = 2
            example.createCriteria().andParentIdEqualTo(gcv01.getId()).andLevelEqualTo((byte) 2);
            // 查询所有二级分类
            List<GoodsCategory> gcList02 = goodsCategoryMapper.selectByExample(example);
            // 遍历查询结果，copy给vo对象
            for (GoodsCategory gc02 : gcList02) {
                // 二级分类的vo对象
                GoodsCategoryVo gcv02 = new GoodsCategoryVo();
                // 拷贝对象
                BeanUtils.copyProperties(gc02, gcv02);
                // 清除之前的查询条件
                example.clear();
                // 三级分类的list
                List<GoodsCategoryVo> gcvList03 = new ArrayList<>();
                // 设置查询条件   where parentId = 二级分类的id and level = 3
                example.createCriteria().andParentIdEqualTo(gcv02.getId()).andLevelEqualTo((byte) 3);
                // 查询所有三级分类
                List<GoodsCategory> gcList03 = goodsCategoryMapper.selectByExample(example);
                // 遍历查询结果，copy给vo对象
                for (GoodsCategory gc03 : gcList03) {
                    // 二级分类的vo对象
                    GoodsCategoryVo gcv03 = new GoodsCategoryVo();
                    // 拷贝对象
                    BeanUtils.copyProperties(gc03, gcv03);
                    // 清除之前的查询条件
                    example.clear();
                    // 把所有三级分类放入三级分类的List
                    gcvList03.add(gcv03);
                }
                // 把所有三级分类List注入二级分类的属性值
                gcv02.setChildrenList(gcvList03);
                // 把所有三级分类放入二级分类的子分类List
                gcvList02.add(gcv02);
            }
            // 把所有二级分类List注入一级分类的属性值
            gcv01.setChildrenList(gcvList02);
            // 把所有二级分类放入一级分类的子分类List
            gcvList01.add(gcv01);
        }
        return gcvList01;
    }
}
