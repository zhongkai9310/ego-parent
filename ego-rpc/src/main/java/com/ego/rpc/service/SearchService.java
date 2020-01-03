package com.ego.rpc.service;

import com.ego.common.result.EgoPageInfo;
import com.ego.rpc.vo.GoodsVo;

/**
 * 搜索服务
 */
public interface SearchService {
	/**
	 * 搜索
	 * @param searchStr
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	EgoPageInfo<GoodsVo> doSearch(String searchStr, Integer pageNum, Integer pageSize);
}
