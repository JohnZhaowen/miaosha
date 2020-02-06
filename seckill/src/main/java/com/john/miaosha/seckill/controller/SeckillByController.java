package com.john.miaosha.seckill.controller;


import com.john.miaosha.seckill.service.SeckillByService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/seckillBy")
public class SeckillByController {

    @Autowired
    private SeckillByService seckillByService;

    @GetMapping(value = "/programLock/{userId}/{id}")
    public Map<String, String> programLock(@PathVariable("userId") Long userId, @PathVariable("id") Long id){
        Map<String, String> stringStringMap = seckillByService.programLock(userId, id);
        return stringStringMap;
    }

    @GetMapping(value = "/programLockByAop/{userId}/{id}")
    public Map<String, String> programLockByAop(@PathVariable("userId") Long userId, @PathVariable("id") Long id){
        Map<String, String> stringStringMap = seckillByService.programLockByAop(userId, id);
        return stringStringMap;
    }
    @GetMapping(value = "/seckillByMultiThread/{userId}/{id}")
    public String seckillByMultiThread(@PathVariable("userId") Long userId, @PathVariable("id") Long id){
        seckillByService.multiThread(userId, id);
        return "秒杀请求已提交";
    }

}
