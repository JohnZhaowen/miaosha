package com.john.miaosha.seckillMessage.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String SECKILL_EXCHANGE = "seckill_exchange";
    public static final String SECKILL_BIND_KEY = "seckill_bind_key";
    public static final String SECKILL_QUEUE = "seckill_queue";

    @Bean
    public Queue queue(){
        return new Queue(SECKILL_QUEUE, false);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(SECKILL_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(SECKILL_BIND_KEY);
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
