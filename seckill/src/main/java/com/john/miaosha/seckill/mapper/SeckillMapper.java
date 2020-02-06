package com.john.miaosha.seckill.mapper;

import com.john.miaosha.entity.SeckillInfo;
import com.john.miaosha.vo.SeckillInfoVo;

import java.util.List;

public interface SeckillMapper {

    void saveSeckill(SeckillInfo seckillInfo);

    List<SeckillInfo> listSeckillInfo(SeckillInfoVo seckillInfoVo);

    void updateSeckillInfoBy(SeckillInfo seckillInfo);
    void updateSeckillInfoBySeckNum(SeckillInfo seckillInfo);

    SeckillInfo findSeckillInfoById(Long id);
}
