package com.john.miaosha.seckill.service;

import java.util.Map;

public interface SeckillByService {

    Map<String, String> programLock(long userId, long id);
}
