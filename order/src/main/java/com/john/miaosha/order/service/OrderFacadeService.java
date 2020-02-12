package com.john.miaosha.order.service;

import com.john.miaosha.entity.Event;
import com.john.miaosha.entity.SeckillInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seckill")
public interface OrderFacadeService {

    @GetMapping(value = "/seckill/out/findSeckillProductById")
    SeckillInfo findSeckillProductById(@RequestParam("id") Long id);

    @GetMapping(value = "/seckill/out/sendEvent")
    void sendEvent(@RequestBody Event event);

}
