package com.john.miaosha.seckill.eventModel;


import com.john.miaosha.seckill.service.MessageFacadeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEvent extends Event {

    private String name;

    private OrderState orderState;

    private Long id;

    private Long userId;

    private Long merchantId;

    private MessageFacadeService messageFacadeService;

    public OrderEvent(String name, OrderState orderState){
        this.name = name;
        this.orderState = orderState;
    }
}
