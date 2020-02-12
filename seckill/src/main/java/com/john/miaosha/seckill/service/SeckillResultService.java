package com.john.miaosha.seckill.service;

import com.john.miaosha.entity.SeckillResult;

import java.util.List;

public interface SeckillResultService {

    Long saveSeckillResult(SeckillResult seckillResult);

    List<SeckillResult> findSeckillResultByUserId(Long userId);

    void updateSeckillResult(SeckillResult seckillResult);
}
