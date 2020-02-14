package com.john.miaosha.seckillMessage.service;

import com.john.miaosha.seckillMessage.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SendMsgForDisTrans<T> implements RabbitTemplate.ReturnCallback{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(T t) {
        log.info("开始发送消息：[{}]", t);
        rabbitTemplate.setReturnCallback(this);

        final List<String> count = new ArrayList<>();

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if(!ack){
                //重试3次
                if(count.size() < 3){
                    rabbitTemplate.convertAndSend(RabbitMqConfig.SECKILL_EXCHANGE, RabbitMqConfig.SECKILL_BIND_KEY, t);
                    log.error("消息发送失败[{}]", cause + correlationData);
                } else {
                    //TODO 计入数据库
                }
                count.add("a");

            } else {
                log.info("消息发送成功");
            }
        });
        rabbitTemplate.convertAndSend(RabbitMqConfig.SECKILL_EXCHANGE, RabbitMqConfig.SECKILL_BIND_KEY, t);
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }
}
