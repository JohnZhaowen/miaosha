package com.john.miaosha.seckillMessage.service;

import com.john.miaosha.form.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "order")
public interface OrderlFacadeService {

    @PostMapping(value = "/order/out/saveOrder")
    String saveOrder(@RequestBody OrderRequest orderRequest);
}
