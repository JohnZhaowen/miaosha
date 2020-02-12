package com.john.miaosha.order.mapper;

import com.john.miaosha.entity.SeckillOrder;

public interface OrderMapper {

    void saveOrder(SeckillOrder seckillOrder);

    SeckillOrder findOrderBy(Long id);

    void updateOrder(SeckillOrder seckillOrder);

}
