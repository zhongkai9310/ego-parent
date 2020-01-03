package com.ego.manager.service.impl;

import com.ego.common.result.BaseResult;
import com.ego.common.util.JsonUtil;
import com.ego.manager.mapper.GoodsMapper;
import com.ego.manager.pojo.Goods;
import com.ego.manager.pojo.GoodsExample;
import com.ego.manager.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 商品Service实现类
 *
 * @Author: sun
 * @DateTime: 2020/1/3 21:45
 * @since 1.0.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 商品列表-保存
     *
     * @param goods
     * @return
     */
    @Override
    public BaseResult save(Goods goods) {
        // 如果详情描述不为空，进行转义保存
        if (!StringUtils.isEmpty(goods.getGoodsContent())) {
            String escape = HtmlUtils.htmlEscape(goods.getGoodsContent(), "UTF-8");
            goods.setGoodsContent(escape);
        }
        // 如果goodsId不为空，说明不是新增goods操作，返回error
        if (null != goods.getGoodsId()) {
            return BaseResult.error();
        }
        BaseResult baseResult = BaseResult.success();
        int result = goodsMapper.insertSelective(goods);
        if (result > 0) {
            // 把插入后的ID值传回前台
            baseResult.setMessage(String.valueOf(goods.getGoodsId()));
        } else {
            baseResult = BaseResult.error();
        }
        return baseResult;
    }

    /**
     * 商品列表-分页搜索
     *
     * @param goods
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public BaseResult search(Goods goods, Integer pageNum, Integer pageSize) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        // 创建查询对象
        GoodsExample example = new GoodsExample();

        // 确定redis的key
        /**
         * 查询的几种条件（不管选哪一种，分页一定存在）
         *
         * 一：没有任何条件
         * goods:list:catId_:brandId_:pageNum_:pageSize_:goodsName_:
         * 二：只选了分类
         * goods:list:catId_123:brandId_:pageNum_:pageSize_:goodsName_:
         * 三：只选了品牌
         * goods:list:catId_:brandId_123:pageNum_:pageSize_:goodsName_:
         * 四：只选了关键词
         * goods:list:catId_:brandId_:pageNum_:pageSize_:goodsName_华为:
         * 五：只选了分类和品牌
         * goods:list:catId_123:brandId_123:pageNum_:pageSize_:goodsName_:
         * 六：只选了分类和关键词
         * goods:list:catId_123:brandId_:pageNum_:pageSize_:goodsName_华为:
         * 七：只选了品牌和关键词
         * goods:list:catId_:brandId_123:pageNum_:pageSize_:goodsName_华为:
         * 八：选了分类，品牌和关键词
         * goods:list:catId_123:brandId_123:pageNum_:pageSize_:goodsName_华为:
         */
        String[] goodsKeyArr = new String[]{
                "goods:list:",
                "catId_:",
                "brandId_:",
                "pageNum_" + pageNum + ":",
                "pageSize_" + pageSize + ":",
                "goodsName_:"};
        // 设置查询条件
        GoodsExample.Criteria criteria = example.createCriteria();
        // 如果分类id不为空
        if (null != goods.getCatId()) {
            criteria.andCatIdEqualTo(goods.getCatId());
            // 相当于catId_123:
            goodsKeyArr[1] = "catId_" + goods.getCatId() + ":";
        }
        // 如果品牌id不为空
        if (null != goods.getBrandId()) {
            criteria.andBrandIdEqualTo(goods.getBrandId());
            // 相当于brandId_123:
            goodsKeyArr[2] = "brandId_" + goods.getBrandId() + ":";
        }
        // 如果关键词不为空
        if (!StringUtils.isEmpty(goods.getGoodsName())) {
            criteria.andGoodsNameLike("%" + goods.getGoodsName() + "%");
            goodsKeyArr[5] = "goodsName_" + goods.getGoodsName() + ":";
        }
        // 循环把所有的key拼接好
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : goodsKeyArr) {
            stringBuilder.append(s);
        }
        String goodsListKey = stringBuilder.toString();

        // 从redis里面获取数据，如果数据不为空，直接返回，如果数据为空去数据库里查询
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
        String goodsListPageJson = (String) stringObjectValueOperations.get(goodsListKey);
        if (!StringUtils.isEmpty(goodsListPageJson)) {
            PageInfo pageInfo = JsonUtil.jsonStr2Object(goodsListPageJson, PageInfo.class);
            BaseResult.success(pageInfo);
        }

        List<Goods> list = goodsMapper.selectByExample(example);
        // 如果查询结果不为空，放入分页对象返回
        if (!CollectionUtils.isEmpty(list)) {
            PageInfo<Goods> pageInfo = new PageInfo<>(list);
            // 将查询的数据放入Redis
            stringObjectValueOperations.set(goodsListKey, JsonUtil.object2JsonStr(pageInfo));
            return BaseResult.success(pageInfo);
        } else {
            // 如果没有查到数据，将null放入redis,并设置失效时间1分钟
            stringObjectValueOperations.set(goodsListKey, new PageInfo<>(new ArrayList<Goods>()), 60, TimeUnit.SECONDS);
        }
        return BaseResult.error();
    }
}
