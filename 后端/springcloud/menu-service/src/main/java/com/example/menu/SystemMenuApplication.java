package com.example.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.feign.clients.admin")
public class SystemMenuApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemMenuApplication.class,args);
    }
}
