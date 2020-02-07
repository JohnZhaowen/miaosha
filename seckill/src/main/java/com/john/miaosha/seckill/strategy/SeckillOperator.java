package com.john.miaosha.seckill.strategy;

import java.util.Map;

public interface SeckillOperator {

    Map<String, String> seckill(long id, long userId);
}
