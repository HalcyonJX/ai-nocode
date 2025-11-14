package com.halcyon.ainocode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class AiNocodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiNocodeApplication.class, args);
    }

}
