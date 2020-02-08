package com.john.miaosha.rabbitmqtest.controller;

import com.john.miaosha.rabbitmqtest.send.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMqController {

    @Autowired
    private SendMsg sendMsg;

    @GetMapping("/send/{msg}")
    public String send(@PathVariable String msg){
        sendMsg.send(msg);
        return "发送消息成功！！";
    }
}
