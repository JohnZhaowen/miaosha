package com.john.miaosha.seckill.strategy;

import com.john.miaosha.seckill.service.SeckillIntegrationByService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DistributedLockAndFutureSeckill implements SeckillOperator {

    @Autowired
    private SeckillIntegrationByService seckillIntegrationByService;

    @Override
    public Map<String, String> seckill(long id, long userId) {
        seckillIntegrationByService.seckollByDistributedLockAndFuture(userId, id);
        return null;
    }
}
