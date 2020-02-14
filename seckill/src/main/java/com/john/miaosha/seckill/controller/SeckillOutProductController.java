package com.john.miaosha.seckill.controller;


import com.john.miaosha.entity.OrderAckEvent;
import com.john.miaosha.entity.SeckillInfo;
import com.john.miaosha.seckill.eventModel.CentralEventProcessor;
import com.john.miaosha.seckill.eventModel.OrderEvent;
import com.john.miaosha.seckill.eventModel.OrderState;
import com.john.miaosha.seckill.service.SeckillService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seckill/out")
@Slf4j
public class SeckillOutProductController {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private CentralEventProcessor centralEventProcessor;

    @GetMapping(value = "/findSeckillProductById")
    @HystrixCommand(fallbackMethod = "findSeckillProductByIdFail")
    public SeckillInfo findSeckillProductById(@RequestParam("id") Long id){
       return seckillService.findSeckillInfoById(id);
    }
    private SeckillInfo findSeckillProductByIdFail(@RequestParam("id") Long id){
        log.error("进入熔断服务");
        SeckillInfo seckillInfo = new SeckillInfo();
        seckillInfo.setProductName("出错商品");
        seckillInfo.setId(id);
       return seckillInfo;
    }

    @PostMapping(value = "/sendEvent")
    public void sendEvent(@RequestBody OrderAckEvent orderAckEvent){
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setId(orderAckEvent.getId());
        orderEvent.setSeckillResultId(orderAckEvent.getSckillResultId());
        orderEvent.setOrderId(orderAckEvent.getOrderId());
        orderEvent.setUserId(orderAckEvent.getUserId());
        orderEvent.setMerchantId(orderAckEvent.getMerchantId());
        orderEvent.setOrderState(OrderState.COPLETE);
        centralEventProcessor.process(orderEvent);
    }

    @PostMapping(value = "/updateSeckillSeckillNum")
    public void updateSeckillSeckillNum(@RequestBody SeckillInfo seckillInfo){
        seckillService.seckillNumMinus(seckillInfo);
    }
}
