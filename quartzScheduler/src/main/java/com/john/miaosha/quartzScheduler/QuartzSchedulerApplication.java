package com.john.miaosha.quartzScheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class QuartzSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzSchedulerApplication.class, args);
    }
}
