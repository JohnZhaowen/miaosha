package com.john.miaosha.rabbitmqtest.send;

import com.john.miaosha.rabbitmqtest.config.RabbitMqConfig;
import com.john.miaosha.rabbitmqtest.pojo.entity.MessageContent;
import com.john.miaosha.rabbitmqtest.pojo.entity.MessageContent2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendMsgWithConverter {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(MessageContent msg){
        log.info("开始发送消息");
        amqpTemplate.convertAndSend(RabbitMqConfig.SPRING_BOOT_EXCHANGE, RabbitMqConfig.SPRING_BOOT_BIND_KEY, msg);
        log.info("发送消息成功");
    }
    public void send2(MessageContent2 msg){
        log.info("开始发送消息");
        amqpTemplate.convertAndSend(RabbitMqConfig.SPRING_BOOT_EXCHANGE, RabbitMqConfig.SPRING_BOOT_BIND_KEY, msg);
        log.info("发送消息成功");
    }


}
