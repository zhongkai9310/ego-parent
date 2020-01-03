package com.ego.manager.service.impl;

import com.ego.manager.mapper.BrandMapper;
import com.ego.manager.pojo.Brand;
import com.ego.manager.pojo.BrandExample;
import com.ego.manager.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌service实现类
 *
 * @author zhoubin
 * @create 2019/12/26
 * @since 1.0.0
 */
@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandMapper brandMapper;

	/**
	 * 查询所有品牌
	 * @return
	 */
	@Override
	public List<Brand> selectList() {
		return brandMapper.selectByExample(new BrandExample());
	}
}