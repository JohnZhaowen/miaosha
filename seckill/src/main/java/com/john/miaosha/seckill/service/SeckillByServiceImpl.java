package com.john.miaosha.seckill.service;

import com.john.miaosha.entity.SeckillInfo;
import com.john.miaosha.entity.SeckillResult;
import com.john.miaosha.seckill.mapper.SeckillMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
    @Autowired
    private SeckillService seckillService;

    @Autowired
    private SeckillResultService seckillResultService;

    private Lock lock = new ReentrantLock();

    private ConcurrentHashMap<String, Long> cacheMap = new ConcurrentHashMap<>();

    private static final String Inventory = "Inventory";
    private static final String SeckillNum = "SeckillNum";

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

    @Override
    @Transactional
    public Map<String, String> programLockByAop(long userId, long id){
        log.info("=====秒杀方法=====");
        Map<String, String> result = new HashMap<>();

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

    }

    @Override
    public void multiThread(long userId, long id){
        Long inventory = cacheMap.get(Inventory + id);

        if(inventory == null){
            SeckillInfo seckillInfo = seckillMapper.findSeckillInfoById(id);
            cacheMap.put(Inventory + id, seckillInfo.getSeckillInventory());
            cacheMap.put(SeckillNum + id, seckillInfo.getSeckillNum());
            inventory = seckillInfo.getSeckillInventory();
        }

        Long seckillNum = cacheMap.get(SeckillNum + id);
        seckillNum++;
        cacheMap.put(SeckillNum + id, seckillNum);

        new Thread(new SeckillThread(id, userId, inventory, seckillNum)).start();
    }

    @AllArgsConstructor
    class SeckillThread implements Runnable {

        private Long id;

        private Long userId;

        private Long inventory;

        private Long seckillNum;

        @Override
        public void run() {
            log.info("用户[" + userId + "]开始秒杀");
            SeckillInfo seckillInfo = seckillMapper.findSeckillInfoById(id);

            SeckillResult seckillResult = new SeckillResult();
            seckillResult.setUserId(userId);
            seckillResult.setProductId(seckillInfo.getProductId());
            seckillResult.setSeckillId(seckillInfo.getId());
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

        }
    }

    @Transactional
    public Map<String, String> pessimismLock(long userId, long id){
        Map<String, String> result = new HashMap<>();

        SeckillInfo seckillInfo = seckillMapper.selectForUpdate(id);
        Long seckillInventory = seckillInfo.getSeckillInventory();
        Long seckillNum = seckillInfo.getSeckillNum();
        if(seckillNum >= seckillInventory){
            log.info("卖光了，谢谢惠顾！");
            result.put("flag", "fail");
            result.put("date", "卖光了，谢谢惠顾！");
            return result;
        }
        seckillInfo.setSeckillNum(++seckillNum);
        seckillMapper.updateSeckillInfoBySeckNum(seckillInfo);
        result.put("flag", "success");
        result.put("date", "秒杀成功");
        return result;
    }


    @Transactional
    public Map<String, String> optimisticLock(long userId, long id){
        Map<String, String> result = new HashMap<>();
        SeckillInfo seckillInfo = seckillMapper.findSeckillInfoById(id);

        Long version = seckillInfo.getVersion();
        Long seckillInventory = seckillInfo.getSeckillInventory();
        Long seckillNum = seckillInfo.getSeckillNum();

        if(seckillNum >= seckillInventory){
            log.info("卖光了，谢谢惠顾！");
            result.put("flag", "fail");
            result.put("date", "卖光了，谢谢惠顾！");
            return result;
        }
        seckillInfo.setSeckillNum(++seckillNum);
        int updateNum = seckillMapper.updateSeckillInfoByVersion(seckillInfo);
        if(updateNum > 0){
            log.info("秒杀成功");
            result.put("flag", "success");
            result.put("date", "秒杀成功");
        } else {
            log.info("操作失败，请重试");
            result.put("flag", "fail");
            result.put("date", "操作失败，请重试！");
        }
        return result;
    }




}


