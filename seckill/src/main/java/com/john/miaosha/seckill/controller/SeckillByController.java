package com.john.miaosha.seckill.controller;


import com.john.miaosha.seckill.service.SeckillByService;
import com.john.miaosha.seckill.service.SeckillIntegrationByService;
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
    @Autowired
    private SeckillIntegrationByService seckillIntegrationByService;

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

    @GetMapping(value = "/seckillPessimism/{userId}/{id}")
    public Map<String, String> seckillPessimism(@PathVariable("userId") Long userId, @PathVariable("id") Long id){
        Map<String, String> stringStringMap = seckillByService.pessimismLock(userId, id);
        return stringStringMap;
    }

    @GetMapping(value = "/seckillOptimis/{userId}/{id}")
    public Map<String, String> seckillOptimis(@PathVariable("userId") Long userId, @PathVariable("id") Long id){
        Map<String, String> stringStringMap = seckillByService.optimisticLock(userId, id);
        return stringStringMap;
    }
    @GetMapping(value = "/seckillByQueue/{userId}/{id}")
    public String seckillByQueue(@PathVariable("userId") Long userId, @PathVariable("id") Long id){
        seckillByService.queueAndThread(userId, id);
        return "秒杀任务提交成功";
    }
    @GetMapping(value = "/seckillByRedisLock/{userId}/{id}")
    public Map<String, String> seckillByRedisLock(@PathVariable("userId") Long userId, @PathVariable("id") Long id){
        return seckillByService.redisLock(userId, id);
    }
    @GetMapping(value = "/seckillFuture/{userId}/{id}")
    public String seckillFuture(@PathVariable("userId") Long userId, @PathVariable("id") Long id){
        seckillByService.seckillFuture(userId, id);
        return "秒杀任务提交成功";
    }
    @GetMapping(value = "/seckillByFutureAndRedisLock/{userId}/{id}")
    public String seckillByFutureAndRedisLock(@PathVariable("userId") Long userId, @PathVariable("id") Long id){
        seckillIntegrationByService.seckillByDistributedLockAndFuture(userId, id);
        return "秒杀任务提交成功";
    }

}
