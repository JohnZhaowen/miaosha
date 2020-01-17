package com.john.miaosha.shop.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @GetMapping("")
    public String test(){
        return "success";
    }
}
