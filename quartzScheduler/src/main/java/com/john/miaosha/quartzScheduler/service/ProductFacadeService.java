package com.john.miaosha.quartzScheduler.service;

import com.john.miaosha.entity.SeckillInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("seckill")
public interface ProductFacadeService {

    @PostMapping(value = "/seckill/out/updateSeckillSeckillNum")
    public void updateSeckillSeckillNum(@RequestBody SeckillInfo seckillInfo);

}
