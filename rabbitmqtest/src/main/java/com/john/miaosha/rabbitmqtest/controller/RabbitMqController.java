package com.john.miaosha.rabbitmqtest.controller;

import com.john.miaosha.rabbitmqtest.pojo.entity.MessageContent;
import com.john.miaosha.rabbitmqtest.pojo.entity.MessageContent2;
import com.john.miaosha.rabbitmqtest.send.SendMsg;
import com.john.miaosha.rabbitmqtest.send.SendMsgWithConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RabbitMqController {

    @Autowired
    private SendMsg sendMsg;

    @Autowired
    private SendMsgWithConverter sendMsgWithConverter;

    @GetMapping("/send/{msg}")
    public String send(@PathVariable String msg){
        sendMsg.send(msg);
        return "发送消息成功！！";
    }

    @PostMapping("/sendWithConverter")
        public String sendWithConverter(@RequestBody MessageContent msg){
        sendMsgWithConverter.send(msg);
        return "发送消息成功！！";
    }
    @PostMapping("/sendWithConverter2")
        public String sendWithConverter2(@RequestBody MessageContent2 msg){
        sendMsgWithConverter.send2(msg);
        return "发送消息成功！！";
    }
}
