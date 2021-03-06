package com.john.miaosha.rabbitmqtest.config;

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

    public static final String SPRING_BOOT_EXCHANGE = "spring_boot_exchange1";
    public static final String SPRING_BOOT_BIND_KEY = "spring_boot_bind_key1";
    public static final String SPRING_BOOT_QUEUE = "spring_boot_queue1";

    @Bean
    public Queue queue(){
        return new Queue(SPRING_BOOT_QUEUE, false);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(SPRING_BOOT_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(SPRING_BOOT_BIND_KEY);
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
