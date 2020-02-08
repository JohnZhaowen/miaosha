package com.john.miaosha.rabbitmqtest.consumer;

import com.john.miaosha.rabbitmqtest.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Component
@Slf4j
public class ConsumerMsg {

    @RabbitListener(
            containerFactory = "rabbitListenerContainerFactory",
            bindings = @QueueBinding(
                    value = @Queue(value = RabbitMqConfig.SPRING_BOOT_QUEUE, durable = "true", autoDelete = "ture"),
                    exchange = @Exchange(value = RabbitMqConfig.SPRING_BOOT_EXCHANGE, type = ExchangeTypes.TOPIC),
                    key = RabbitMqConfig.SPRING_BOOT_BIND_KEY
            )
    )
    public void rec(String msg){
        log.info("接收到消息：[{}]", msg);
    }


}
