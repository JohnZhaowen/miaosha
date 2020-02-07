package com.john.miaosha.seckill.strategy;

import com.john.miaosha.entity.SeckillInfo;
import com.john.miaosha.seckill.mapper.SeckillMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Service
public class ProgramLockSeckill implements SeckillOperator {

    private Lock lock = new ReentrantLock();

    @Autowired
    private SeckillMapper seckillMapper;

    @Override
    public Map<String, String> seckill(long id, long userId) {
        Map<String, String> result = new HashMap<>();
        lock.lock();
        try {
            SeckillInfo seckillInfo = seckillMapper.findSeckillInfoById(id);
            Long seckillInventory = seckillInfo.getSeckillInventory();
            Long seckillNum = seckillInfo.getSeckillNum();
            seckillNum++;
            seckillInfo.setSeckillNum(seckillNum);
            if(seckillNum > seckillInventory){
                log.info("卖光了，谢谢惠顾！");
                result.put("flag", "fail");
                result.put("date", "卖光了，谢谢惠顾！");
                return result;
            }
            seckillMapper.updateSeckillInfoBySeckNum(seckillInfo);
            result.put("flag", "success");
            result.put("date", "秒杀成功");
            return result;
        } finally {
            lock.unlock();
        }
    }
}
