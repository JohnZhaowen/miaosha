package com.john.miaosha.seckill.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "message")
public interface MessageFacadeService {
    @PostMapping("/msg/out/sendMsg")
    boolean sendMsg(@RequestBody Object msg);
}
