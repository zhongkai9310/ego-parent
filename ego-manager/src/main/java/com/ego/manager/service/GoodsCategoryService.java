package com.ego.manager.service;

import com.ego.common.result.BaseResult;
import com.ego.manager.pojo.GoodsCategory;
import com.ego.manager.vo.GoodsCategoryVo;

import java.util.List;

/**
 *  商品分类service
 */
public interface GoodsCategoryService {

    /**
     * 查询所有顶级分类
     * @return
     */
    List<GoodsCategory> selectTopList();

    /**
     * 通过父id查询所有分类
     * @param patentId
     * @return
     */
    List<GoodsCategory> selectListByParentId(Short patentId);

    /**
     * 保存
     * @param goodsCategory
     * @return
     */
    BaseResult save(GoodsCategory goodsCategory);

    /**
     * 查询所有分类List
     * @return
     */
    List<GoodsCategoryVo> selectAllList();

}
