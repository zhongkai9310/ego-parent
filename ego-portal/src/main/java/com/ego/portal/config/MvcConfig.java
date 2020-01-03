package com.ego.portal.config;

import com.ego.portal.interceptor.PortalLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 *
 * @author zhoubin
 * @create 2020/1/2
 * @since 1.0.0
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	@Autowired
	private PortalLoginInterceptor loginInterceptor;

	/**
	 * 拦截器配置
	 * addInterceptor:添加拦截器
	 * addPathPatterns：添加拦截路径
	 *  /*:只到后面一层路径比如/admin/*  拦截/admin/login,/admin/user
	 *  /**:后面所有路径比如/admin/**  拦截/admin/login,/admin/user/login/logout
	 *  excludePathPatterns:添加放行的路径（不拦截）
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/cart/**")
				.excludePathPatterns("/static/**")
				.excludePathPatterns("/login/**")
				.excludePathPatterns("/user/login/**")
				.excludePathPatterns("/user/logout/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
}