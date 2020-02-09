package com.john.miaosha.seckill.mapper;

import com.john.miaosha.entity.SeckillResult;

import java.util.List;

public interface SeckillResultMapper {

    void saveSeckillResult(SeckillResult seckillResult);
    List<SeckillResult> findSeckillResultByUserId(Long userId);

}
