package com.john.miaosha.rabbitmqtest.send;

import com.john.miaosha.rabbitmqtest.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendMsg {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String msg){
        log.info("开始发送消息");
        amqpTemplate.convertAndSend(RabbitMqConfig.SPRING_BOOT_EXCHANGE, RabbitMqConfig.SPRING_BOOT_BIND_KEY, msg);
        log.info("发送消息成功");
    }


}
