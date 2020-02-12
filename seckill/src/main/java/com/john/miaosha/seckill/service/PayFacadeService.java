package com.john.miaosha.seckill.service;

import com.john.miaosha.form.PayForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "pay")
public interface PayFacadeService {

    @PostMapping(value = "/pay/out/payWith")
    void payWith(@RequestBody PayForm payForm);

}
