package com.ego.rpc;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Rpc启动类
 *
 * @Author: sun
 * @DateTime: 2020/1/2 20:59
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDubboConfiguration
@MapperScan("com.ego.rpc.mapper")
public class RpcApplication {
    public static void main(String[] args) {
        SpringApplication.run(RpcApplication.class, args);
    }
}
