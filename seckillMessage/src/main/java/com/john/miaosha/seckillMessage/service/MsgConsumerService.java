package com.john.miaosha.seckillMessage.service;

import com.john.miaosha.entity.SeckillOrder;
import com.john.miaosha.form.OrderRequest;
import com.john.miaosha.seckillMessage.config.RabbitMqConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RabbitListener(queues = RabbitMqConfig.SECKILL_QUEUE)
public class MsgConsumerService {

    @Autowired
    private OrderlFacadeService orderlFacadeService;

    @RabbitHandler
    public void handleOrderRequest(OrderRequest orderRequest, Channel channel, Message message){
        log.info("消费者消费到消息[{}]", orderRequest);
        try {
            orderlFacadeService.saveOrder(orderRequest);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            log.error("接收订单消息失败[{}]", e);
        }
    }

    @RabbitHandler
    public void handleOrder(SeckillOrder seckillOrder){
        log.info("消费者消费到消息[{}]", seckillOrder);
        orderlFacadeService.updateOrder(seckillOrder);
    }

}
