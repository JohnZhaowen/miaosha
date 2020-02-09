package com.john.miaosha.rabbitmqtest.consumer;

import com.john.miaosha.rabbitmqtest.config.RabbitMqConfig;
import com.john.miaosha.rabbitmqtest.pojo.entity.MessageContent;
import com.john.miaosha.rabbitmqtest.pojo.entity.MessageContent2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = RabbitMqConfig.SPRING_BOOT_QUEUE)
public class ConsumerMsgWithConverter {

    @RabbitHandler
    public void recMessageContent(MessageContent msg){
        System.out.println(msg);
    }

    @RabbitHandler
    public void recMessageContent2(MessageContent2 msg){
        System.out.println(msg);
    }


}
