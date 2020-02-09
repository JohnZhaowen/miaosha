package com.john.miaosha.order.service;

import com.john.miaosha.entity.SeckillOrder;
import com.john.miaosha.form.OrderRequest;

public interface OrderService {

    void saveOrder(SeckillOrder seckillOrder);

    void saveOrder(OrderRequest orderRequest);
}
