package com.john.miaosha.seckill.controller;


import com.john.miaosha.entity.SeckillInfo;
import com.john.miaosha.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seckill/out")
@Slf4j
public class SeckillOutProductController {

    @Autowired
    private SeckillService seckillService;

    @GetMapping(value = "/findSeckillProductById")
    public SeckillInfo findSeckillProductById(@RequestParam("id") Long id){
       return seckillService.findSeckillInfoById(id);
    }
}
