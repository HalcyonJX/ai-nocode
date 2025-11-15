package com.halcyon.ainocode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("com.halcyon.ainocode.mapper")
public class AiNocodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiNocodeApplication.class, args);
    }

}
