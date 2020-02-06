package com.john.miaosha.seckill.service;

import com.john.miaosha.entity.SeckillInfo;
import com.john.miaosha.seckill.mapper.SeckillMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 秒杀实现
 */
@Service
@Slf4j
public class SeckillByServiceImpl implements SeckillByService {

    @Autowired
    private SeckillMapper seckillMapper;

    private Lock lock = new ReentrantLock();

    @Override
    @Transactional
    public Map<String, String> programLock(long userId, long id){
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
