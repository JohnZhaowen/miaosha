package com.john.miaosha.seckillMessage.service;

import com.john.miaosha.seckillMessage.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendMsgServiceImpl<T> implements SendMsgService<T> {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void send(T t) {
        log.info("开始发送消息：[{}]", t);
        amqpTemplate.convertAndSend(RabbitMqConfig.SECKILL_EXCHANGE, RabbitMqConfig.SECKILL_BIND_KEY, t);
        log.info("发送消息成功:[{}]", t);
    }
}
