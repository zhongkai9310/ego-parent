package com.ego.manager.service;

import com.ego.manager.pojo.Brand;

import java.util.List;

/**
 * 品牌Service
 *
 * @Author: sun
 * @DateTime: 2020/1/3 21:44
 * @since 1.0.0
 */
public interface BrandService {
    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> selectList();
}
