package com.ego.portal;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 前台门户启动类
 *
 * @Author: sun
 * @DateTime: 2020/1/3 19:41
 * @since 1.0.0
 */
@SpringBootApplication
@MapperScan("com.ego.portal.mapper")
@EnableDubboConfiguration
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }
}
