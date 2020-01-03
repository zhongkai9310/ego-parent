package com.ego.portal;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.util.JsonUtil;
import com.ego.rpc.service.GoodsCategroyService;
import com.ego.rpc.vo.GoodsCategoryVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 商品分类测试类
 *
 * @author zhoubin
 * @create 2019/12/31
 * @since 1.0.0
 */
@SpringBootTest
public class GoodsCategoryServiceTest {

	@Reference(interfaceClass = GoodsCategroyService.class)
	private GoodsCategroyService goodsCategroyService;

	@Test
	public void testRpc(){
		List<GoodsCategoryVo> goodsCategoryVos = goodsCategroyService.selectAllList();
		System.out.println(JsonUtil.object2JsonStr(goodsCategoryVos));
	}
}