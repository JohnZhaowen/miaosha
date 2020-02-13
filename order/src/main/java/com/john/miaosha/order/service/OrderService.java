package com.john.miaosha.order.service;

import com.john.miaosha.entity.SeckillOrder;
import com.john.miaosha.form.OrderRequest;

import java.util.List;

public interface OrderService {

    Long saveOrder(SeckillOrder seckillOrder);

    SeckillOrder saveOrder(OrderRequest orderRequest);

    SeckillOrder findOrderBy(Long id);

    void updateOrder(SeckillOrder seckillOrder);

    List<SeckillOrder> listOrderByCurrentTime();

    void updateOrderByFlag(SeckillOrder seckillOrder);
}
