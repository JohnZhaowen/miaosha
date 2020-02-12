package com.john.miaosha.seckill.service;

import com.alibaba.fastjson.util.AntiCollisionHashMap;
import com.john.miaosha.entity.SeckillInfo;
import com.john.miaosha.entity.SeckillResult;
import com.john.miaosha.seckill.entity.SeckillUniqueKey;
import com.john.miaosha.seckill.mapper.SeckillMapper;
import com.john.miaosha.utils.RedisUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@Slf4j
public class SeckillIntegrationByServiceImpl implements SeckillIntegrationByService {

    private Map<SeckillUniqueKey, Future<Map<String, Long>>> cacheSeckillResult = new HashMap<>();

    private ExecutorService executor = Executors.newCachedThreadPool();


    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private SeckillResultService seckillResultService;

    @Override
    public Map<SeckillUniqueKey, Future<Map<String, Long>>> seckillByDistributedLockAndFuture(long userId, long id){
        Future<Map<String, Long>> future = executor.submit(new SeckillFuture(id, userId));
        cacheSeckillResult.put(new SeckillUniqueKey(id, userId), future);
        return cacheSeckillResult;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class SeckillFuture implements Callable<Map<String, Long>> {

        private Long id;

        private Long userId;

        @Override
        public Map<String, Long> call() {

            try {
                boolean getLock = RedisUtil.tryGetDistributedLock(id + "", userId + "", 50000);
                if(getLock){
                    return processSeckill();
                } else {
                    while (true){
                        getLock = RedisUtil.tryGetDistributedLock(id + "", userId + "", 50000);
                        if(getLock){
                            return processSeckill();
                        }
                    }
                }
            } finally {
                boolean rls = RedisUtil.releaseDistributedLock(id + "", userId + "");
                while(!rls){
                    rls = RedisUtil.releaseDistributedLock(id + "", userId + "");
                }
            }
        }

        private Map<String, Long> processSeckill() {
            SeckillInfo seckillInfo = seckillMapper.findSeckillInfoById(id);
            Long inventory = seckillInfo.getSeckillInventory();
            Long seckillNum = seckillInfo.getSeckillNum();
            seckillNum++;

            SeckillResult seckillResult = new SeckillResult();
            seckillResult.setUserId(userId);
            seckillResult.setProductId(seckillInfo.getProductId());
            seckillResult.setSeckillId(id);
            seckillResult.setResult(2);
            seckillResult.setResultData("正在生成订单");

            seckillInfo.setSeckillNum(seckillNum);
            if(seckillNum > inventory){
                log.info("卖光了，谢谢惠顾！");
                seckillResult.setResult(0);
                seckillResult.setResultData("秒杀失败");
            } else {
                log.info("正在生成订单");
                seckillMapper.updateSeckillInfoBySeckNum(seckillInfo);
            }
            Long resultId = seckillResultService.saveSeckillResult(seckillResult);

            Map<String, Long> result = new AntiCollisionHashMap<>();
            result.put("seckillResult", Long.valueOf(seckillResult.getResult()));
            result.put("seckillId", resultId);

            return result;
        }
    }

}


