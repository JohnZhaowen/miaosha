package com.john.miaosha.order.service;

import com.john.miaosha.entity.SeckillOrder;
import com.john.miaosha.form.OrderRequest;

public interface OrderService {

    Long saveOrder(SeckillOrder seckillOrder);

    SeckillOrder saveOrder(OrderRequest orderRequest);

    SeckillOrder findOrderBy(Long id);

    void updateOrder(SeckillOrder seckillOrder);
}
