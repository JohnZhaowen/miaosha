package com.john.miaosha.seckillMessage.controller;

import com.john.miaosha.entity.SeckillOrder;
import com.john.miaosha.form.OrderRequest;
import com.john.miaosha.seckillMessage.service.SendMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/msg/out")
public class MessageOutController {

    @Autowired
    private SendMsgService sendMsgService;

    @PostMapping("/sendMsg")
    public boolean sendMsg(@RequestBody OrderRequest msg){
        try {
            sendMsgService.send(msg);
            return true;
        } catch (Exception e) {
            log.error("发送消息失败[{}]", e);
            return false;
        }
    }
    @PostMapping("/updateOrder")
    public boolean updateOrder(@RequestBody SeckillOrder msg){
        try {
            sendMsgService.send(msg);
            return true;
        } catch (Exception e) {
            log.error("发送消息失败[{}]", e);
            return false;
        }
    }

}
