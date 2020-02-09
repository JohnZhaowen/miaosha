package com.john.miaosha.seckill.service;

import com.john.miaosha.entity.SeckillResult;
import com.john.miaosha.seckill.mapper.SeckillResultMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SeckillResultServiceImpl implements SeckillResultService {

    @Autowired
    private SeckillResultMapper seckillResultMapper;

    @Override
    public void saveSeckillResult(SeckillResult seckillResult) {
        seckillResultMapper.saveSeckillResult(seckillResult);
    }

    @Override
    public List<SeckillResult> findSeckillResultByUserId(Long userId) {
        return seckillResultMapper.findSeckillResultByUserId(userId);
    }
}
