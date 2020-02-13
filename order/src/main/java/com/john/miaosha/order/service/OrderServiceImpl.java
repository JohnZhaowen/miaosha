package com.john.miaosha.order.service;

import com.john.miaosha.entity.SeckillInfo;
import com.john.miaosha.entity.SeckillOrder;
import com.john.miaosha.form.OrderRequest;
import com.john.miaosha.order.mapper.OrderMapper;
import com.john.miaosha.utils.TradeNumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderFacadeService orderFacadeService;

    @Override
    public Long saveOrder(SeckillOrder seckillOrder) {
        seckillOrder.setPayStatus(0);
        seckillOrder.setOrderFlag(0);
        seckillOrder.setTradeSerialNum(TradeNumUtil.generateTradeNum());
        orderMapper.saveOrder(seckillOrder);
        return seckillOrder.getId();
    }

    @Override
    public SeckillOrder saveOrder(OrderRequest orderRequest) {
        SeckillInfo p = orderFacadeService.findSeckillProductById(orderRequest.getId());
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setPayStatus(0);
        seckillOrder.setOrderFlag(0);
        seckillOrder.setTradeSerialNum(TradeNumUtil.generateTradeNum());
        seckillOrder.setSeckillProductId(orderRequest.getId());

        seckillOrder.setProductId(p.getProductId());
        seckillOrder.setPayAmount(p.getSeckillPrice());
        seckillOrder.setNum(1);
        seckillOrder.setSeckillResultId(orderRequest.getSeckillResultId());
        seckillOrder.setUserId(orderRequest.getUserId());
        seckillOrder.setMerchantId(orderRequest.getMerchantId());
        orderMapper.saveOrder(seckillOrder);

        return seckillOrder;
    }

    @Override
    public SeckillOrder findOrderBy(Long id) {
        return orderMapper.findOrderBy(id);
    }

    @Override
    public void updateOrder(SeckillOrder seckillOrder) {
        orderMapper.updateOrder(seckillOrder);
    }

    @Override
    public List<SeckillOrder> listOrderByCurrentTime() {
        LocalDateTime overDueTime = LocalDateTime.now().minusMinutes(20);
        return orderMapper.listOrderByCurrentTime(overDueTime);
    }

    @Override
    public void updateOrderByFlag(SeckillOrder seckillOrder) {
        orderMapper.updateOrderByFlag(seckillOrder);
    }
}
