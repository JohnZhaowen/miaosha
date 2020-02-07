package com.john.miaosha.seckill.service;

import com.john.miaosha.entity.SeckillInfo;
import com.john.miaosha.entity.SeckillResult;
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

    private Map<SeckillUniqueKey, Future> cacheSeckillResult = new HashMap<>();

    private ExecutorService executor = Executors.newCachedThreadPool();


    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private SeckillResultService seckillResultService;

    @Override
    public void seckollByDistributedLockAndFuture(long userId, long id){
        Future<Integer> future = executor.submit(new SeckillFuture(id, userId));
        cacheSeckillResult.put(new SeckillUniqueKey(id, userId), future);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class SeckillFuture implements Callable<Integer> {

        private Long id;

        private Long userId;

        @Override
        public Integer call() throws Exception {

            try {
                boolean getLock = RedisUtil.tryGetDistributedLock(id + "", userId + "", 50000);
                if(getLock){
                    return processSeckill();
                } else {
                    for(int i = 0; i < 3; i++){
                        getLock = RedisUtil.tryGetDistributedLock(id + "", userId + "", 50000);
                        if(getLock){
                            return processSeckill();
                        }
                    }
                    return 0;
                }
            } finally {
                boolean rls = RedisUtil.releaseDistributedLock(id + "", userId + "");
                while(!rls){
                    rls = RedisUtil.releaseDistributedLock(id + "", userId + "");
                }
            }
        }

        private Integer processSeckill() {
            SeckillInfo seckillInfo = seckillMapper.findSeckillInfoById(id);
            Long inventory = seckillInfo.getSeckillInventory();
            Long seckillNum = seckillInfo.getSeckillNum();
            seckillNum++;

            SeckillResult seckillResult = new SeckillResult();
            seckillResult.setUserId(userId);
            seckillResult.setProductId(seckillInfo.getProductId());
            seckillResult.setSeckillId(id);
            seckillResult.setResult(1);
            seckillResult.setResultData("秒杀成功");

            seckillInfo.setSeckillNum(seckillNum);
            if(seckillNum > inventory){
                log.info("卖光了，谢谢惠顾！");
                seckillResult.setResult(0);
                seckillResult.setResultData("秒杀失败");
            } else {
                log.info("秒杀成功");
                seckillMapper.updateSeckillInfoBySeckNum(seckillInfo);
            }
            seckillResultService.saveSeckillResult(seckillResult);

            return seckillResult.getResult();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class SeckillUniqueKey {
        private Long id;

        private Long userId;
    }
}
