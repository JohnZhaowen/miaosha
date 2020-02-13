package com.john.miaosha.quartzScheduler.service;

import com.john.miaosha.entity.SeckillOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "order")
public interface OrderFacadeService {

    @GetMapping(value = "/order/out/listOrderByCurrentTime")
    List<SeckillOrder> listOrderByCurrentTime();

    @PostMapping(value = "/order/out/updateOrderByFlag")
    void updateOrderByFlag(@RequestBody SeckillOrder seckillOrder);

}
