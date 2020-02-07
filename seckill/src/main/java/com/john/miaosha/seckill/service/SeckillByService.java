package com.john.miaosha.seckill.service;

import java.util.Map;

public interface SeckillByService {

    Map<String, String> programLock(long userId, long id);

    Map<String, String> programLockByAop(long userId, long id);

    void multiThread(long userId, long id);

    Map<String, String> pessimismLock(long userId, long id);

    Map<String, String> optimisticLock(long userId, long id);

    void queueAndThread(long userId, long id);

    Map<String, String> redisLock(long userId, long id);

    void seckillFuture(long userId, long id);
}
