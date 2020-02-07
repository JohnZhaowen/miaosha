package com.john.miaosha.seckill.service;

public interface SeckillIntegrationByService {
    void seckollByDistributedLockAndFuture(long userId, long id);
}
