package com.john.miaosha.seckill.strategy;

import com.john.miaosha.seckill.entity.SeckillUniqueKey;
import com.john.miaosha.seckill.service.SeckillIntegrationByService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

@Service
public class DistributedLockAndFutureSeckill implements SeckillOperator {

    @Autowired
    private SeckillIntegrationByService seckillIntegrationByService;

    @Override
    public Map<String, String> seckill(long id, long userId) {
        Map<String, String> result = new HashMap<>();
        result.put("result", "0");
        result.put("data", "秒杀失败");

        Map<SeckillUniqueKey, Future<Integer>> seckillUniqueKeyFutureMap = seckillIntegrationByService.seckillByDistributedLockAndFuture(userId, id);
        Future<Integer> future = seckillUniqueKeyFutureMap.get(new SeckillUniqueKey(id, userId));
        try {
            Integer integer = future.get();
            if(1 == integer){
                result.put("result", "1");
                result.put("data", "秒杀成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
