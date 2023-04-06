package com.example.goodscategory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.feign.clients.admin")
public class GoodsCategoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsCategoryApplication.class,args);
    }
}
