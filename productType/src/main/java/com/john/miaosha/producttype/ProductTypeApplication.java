package com.john.miaosha.producttype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProductTypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductTypeApplication.class, args);
    }
}
