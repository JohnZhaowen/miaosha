package com.john.miaosha.order.service;

import com.john.miaosha.entity.SeckillOrder;
import com.john.miaosha.order.mapper.OrderMapper;
import com.john.miaosha.utils.TradeNumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void saveOrder(SeckillOrder seckillOrder) {
        seckillOrder.setPayStatus(0);
        seckillOrder.setOrderFlag(0);
        seckillOrder.setTradeSerialNum(TradeNumUtil.generateTradeNum());
        orderMapper.saveOrder(seckillOrder);
    }
}
