package com.john.miaosha.order.controller;

import com.john.miaosha.form.OrderRequest;
import com.john.miaosha.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/out")
public class OrderOutController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/saveOrder")
    public String saveOrder(@RequestBody OrderRequest orderRequest){
        orderService.saveOrder(orderRequest);
        return "saveSuccess";
    }

}
