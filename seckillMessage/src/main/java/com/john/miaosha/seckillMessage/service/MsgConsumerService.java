package com.john.miaosha.seckillMessage.service;

import com.john.miaosha.entity.SeckillOrder;
import com.john.miaosha.form.OrderRequest;
import com.john.miaosha.seckillMessage.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RabbitListener(queues = RabbitMqConfig.SECKILL_QUEUE)
public class MsgConsumerService {

    @Autowired
    private OrderlFacadeService orderlFacadeService;

    @RabbitHandler
    public void handleOrderRequest(OrderRequest orderRequest){
        log.info("消费者消费到消息[{}]", orderRequest);
        orderlFacadeService.saveOrder(orderRequest);
    }

    @RabbitHandler
    public void handleOrder(SeckillOrder seckillOrder){
        log.info("消费者消费到消息[{}]", seckillOrder);
        orderlFacadeService.updateOrder(seckillOrder);
    }

}
