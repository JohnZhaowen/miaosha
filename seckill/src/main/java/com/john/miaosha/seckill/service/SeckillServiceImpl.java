package com.john.miaosha.seckill.service;

import com.john.miaosha.entity.ProductInfo;
import com.john.miaosha.entity.SeckillInfo;
import com.john.miaosha.form.SeckillForm;
import com.john.miaosha.seckill.mapper.SeckillMapper;
import com.john.miaosha.vo.SeckillInfoCondition;
import com.john.miaosha.vo.SeckillInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private SeckillFacadeService seckillFacadeService;

    @Override
    public void saveSeckill(SeckillForm seckillForm) {
        if(seckillForm != null){
            SeckillInfo seckillInfo = new SeckillInfo();
            seckillInfo.setProductId(seckillForm.getProductId());
            seckillInfo.setShopId(seckillForm.getShopId());
            seckillInfo.setState(0);

            ProductInfo productInfo = seckillFacadeService.findProductById(seckillForm.getProductId());
            seckillInfo.setSeckillInventory(productInfo.getProductInventory());
            seckillInfo.setSeckillNum(0L);
            seckillInfo.setProductName(seckillForm.getProductName());
            seckillInfo.setProductTitle(seckillForm.getProductTitle());
            seckillInfo.setProductPrice(seckillForm.getProductPrice());
            seckillInfo.setSeckillPrice(seckillForm.getSeckillPrice());
            seckillInfo.setStartTime(LocalDateTime.parse(seckillForm.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            seckillInfo.setEndTime(LocalDateTime.parse(seckillForm.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            seckillMapper.saveSeckill(seckillInfo);
        }

    }

    @Override
    public List<SeckillInfo> listSeckillInfo(SeckillForm seckillForm) {
        SeckillInfoVo seckillInfoVo = new SeckillInfoVo();
        if(seckillForm != null){
            SeckillInfoCondition condition = new SeckillInfoCondition();
            condition.setShopId(seckillForm.getShopId());
            condition.setState(seckillForm.getState());
            seckillInfoVo.setSeckillInfoCondition(condition);
        }
        return seckillMapper.listSeckillInfo(seckillInfoVo);
    }

    @Override
    public void updateSeckillInfoBy(SeckillInfo seckillInfo) {
        seckillMapper.updateSeckillInfoBy(seckillInfo);
    }

    @Override
    public SeckillInfo selectForUpdate(Long id) {
        return seckillMapper.selectForUpdate(id);
    }

    @Override
    public SeckillInfo findSeckillInfoById(Long id) {
        return seckillMapper.findSeckillInfoById(id);
    }


    @Override
    public void seckillNumMinus(SeckillInfo seckillInfo) {
        seckillMapper.seckillNumMinus(seckillInfo);
    }
}
