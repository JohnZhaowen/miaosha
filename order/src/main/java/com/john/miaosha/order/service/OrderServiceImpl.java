package com.john.miaosha.order.service;

import com.john.miaosha.entity.SeckillInfo;
import com.john.miaosha.entity.SeckillOrder;
import com.john.miaosha.form.OrderRequest;
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

    @Autowired
    private OrderFacadeService orderFacadeService;

    @Override
    public void saveOrder(SeckillOrder seckillOrder) {
        seckillOrder.setPayStatus(0);
        seckillOrder.setOrderFlag(0);
        seckillOrder.setTradeSerialNum(TradeNumUtil.generateTradeNum());
        orderMapper.saveOrder(seckillOrder);
    }

    @Override
    public void saveOrder(OrderRequest orderRequest) {
        SeckillInfo p = orderFacadeService.findSeckillProductById(orderRequest.getId());
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setPayStatus(0);
        seckillOrder.setOrderFlag(0);
        seckillOrder.setTradeSerialNum(TradeNumUtil.generateTradeNum());
        seckillOrder.setSeckillProductId(orderRequest.getId());

        seckillOrder.setProductId(p.getProductId());
        seckillOrder.setPayAmount(p.getSeckillPrice());
        seckillOrder.setNum(1);
        seckillOrder.setUserId(orderRequest.getUserId());
        seckillOrder.setMerchantId(orderRequest.getMerchantId());
        orderMapper.saveOrder(seckillOrder);
    }
}
