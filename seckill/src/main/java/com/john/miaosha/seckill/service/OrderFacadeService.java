package com.john.miaosha.seckill.service;

import com.john.miaosha.entity.SeckillOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "order")
public interface OrderFacadeService {

    @GetMapping(value = "/order/out/findOrderBy")
    SeckillOrder findOrderBy(@RequestParam("id") Long id);

}
