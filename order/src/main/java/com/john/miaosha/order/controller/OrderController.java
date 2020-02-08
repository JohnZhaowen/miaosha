package com.john.miaosha.order.controller;

import com.john.miaosha.entity.SeckillOrder;
import com.john.miaosha.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping(value = "/toSaveOrder")
    public String toSaveOrder(){
        return "toSaveOrder";
    }

    @PostMapping(value = "/saveOrder")
    public String saveOrder(SeckillOrder seckillOrder, Model model){

        orderService.saveOrder(seckillOrder);

        return "saveOrderSuccess";
    }

}
