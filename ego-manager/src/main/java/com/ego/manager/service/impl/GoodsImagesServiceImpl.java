package com.ego.manager.service.impl;

import com.ego.common.result.BaseResult;
import com.ego.manager.mapper.GoodsImagesMapper;
import com.ego.manager.pojo.GoodsImages;
import com.ego.manager.service.GoodsImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品相册service实现类
 *
 * @author zhoubin
 * @create 2019/12/26
 * @since 1.0.0
 */
@Service
public class GoodsImagesServiceImpl implements GoodsImagesService {

	@Autowired
	private GoodsImagesMapper goodsImagesMapper;

	/**
	 * 商品相册-保存
	 *
	 * @param goodsImages
	 * @return
	 */
	@Override
	public BaseResult save(GoodsImages goodsImages) {
		int result = goodsImagesMapper.insertSelective(goodsImages);
		return result > 0 ? BaseResult.success() : BaseResult.error();
	}
}