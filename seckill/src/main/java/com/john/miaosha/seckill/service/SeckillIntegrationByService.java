package com.john.miaosha.seckill.service;

import com.john.miaosha.seckill.entity.SeckillUniqueKey;

import java.util.Map;
import java.util.concurrent.Future;

public interface SeckillIntegrationByService {
    Map<SeckillUniqueKey, Future<Map<String, Long>>> seckillByDistributedLockAndFuture(long userId, long id);
}
