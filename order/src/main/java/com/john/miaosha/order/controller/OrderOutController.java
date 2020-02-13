package com.john.miaosha.order.controller;

import com.john.miaosha.entity.Event;
import com.john.miaosha.entity.OrderAckEvent;
import com.john.miaosha.entity.SeckillOrder;
import com.john.miaosha.form.OrderRequest;
import com.john.miaosha.order.service.OrderFacadeService;
import com.john.miaosha.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order/out")
public class OrderOutController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderFacadeService orderFacadeService;

    @PostMapping(value = "/saveOrder")
    public String saveOrder(@RequestBody OrderRequest orderRequest){
        SeckillOrder seckillOrder = orderService.saveOrder(orderRequest);
        Event event = new OrderAckEvent(orderRequest.getId(), seckillOrder.getId(),
                orderRequest.getUserId(), orderRequest.getMerchantId(), seckillOrder.getSeckillResultId());

        orderFacadeService.sendEvent(event);
        return "saveSuccess";
    }

    @GetMapping(value = "/findOrderBy")
    public SeckillOrder findOrderBy(@RequestParam("id") Long id){
        return orderService.findOrderBy(id);
    }

    @PostMapping(value = "/updateOrder")
    public void updateOrder(@RequestBody SeckillOrder seckillOrder){
        orderService.updateOrder(seckillOrder);
    }

    @GetMapping(value = "/listOrderByCurrentTime")
    public List<SeckillOrder> listOrderByCurrentTime(){
        return orderService.listOrderByCurrentTime();
    }

    @PostMapping(value = "/updateOrderByFlag")
    public void updateOrderByFlag(@RequestBody SeckillOrder seckillOrder){
        orderService.updateOrderByFlag(seckillOrder);
    }

}
