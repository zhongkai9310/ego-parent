package com.ego.rpc.service;

import com.ego.rpc.vo.GoodsCategoryVo;

import java.util.List;

/**
 * 商品分类service
 */
public interface GoodsCategroyService {

	/**
	 * 查询所有分类List
	 * @return
	 */
	List<GoodsCategoryVo> selectAllList();

}
