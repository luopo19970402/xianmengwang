package com.qfedu.xianmengwang.config;


import com.qfedu.xianmengwang.util.JedisCore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JedisConfig {
    @Value("${xianmengwang.redis.host}")
    private String host;
    @Value("${xianmengwang.redis.port}")
    private int port;


    @Bean
    public JedisCore createJC(){
        return new JedisCore(host,port);
    }
}
