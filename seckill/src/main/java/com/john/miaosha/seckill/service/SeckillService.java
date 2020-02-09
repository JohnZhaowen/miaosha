package com.john.miaosha.seckill.service;

import com.john.miaosha.entity.SeckillInfo;
import com.john.miaosha.form.SeckillForm;

import java.util.List;

public interface SeckillService {

    void saveSeckill(SeckillForm seckillForm);

    List<SeckillInfo> listSeckillInfo(SeckillForm seckillForm);

    void updateSeckillInfoBy(SeckillInfo seckillInfo);

    SeckillInfo selectForUpdate(Long id);

    SeckillInfo findSeckillInfoById(Long id);
}
