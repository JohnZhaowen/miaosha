package com.john.miaosha.order.mapper;

import com.john.miaosha.entity.SeckillOrder;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderMapper {

    void saveOrder(SeckillOrder seckillOrder);

    SeckillOrder findOrderBy(Long id);

    void updateOrder(SeckillOrder seckillOrder);

    void updateOrderByFlag(SeckillOrder seckillOrder);

    List<SeckillOrder> listOrderByCurrentTime(LocalDateTime overDueTime);

}
