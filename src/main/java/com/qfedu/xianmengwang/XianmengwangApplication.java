package com.qfedu.xianmengwang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.qfedu.xianmengwang.dao")
public class XianmengwangApplication {

    public static void main(String[] args) {
        SpringApplication.run(XianmengwangApplication.class, args);
    }

}
