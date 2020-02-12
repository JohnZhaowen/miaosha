package com.john.miaosha.seckill.service;

import com.john.miaosha.entity.SeckillOrder;
import com.john.miaosha.form.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "message")
public interface MessageFacadeService {

    @PostMapping("/msg/out/sendMsg")
    boolean sendMsg(@RequestBody OrderRequest msg);

    @PostMapping("/msg/out/updateOrder")
    boolean updateOrder(@RequestBody SeckillOrder msg);
}
