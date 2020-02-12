package com.john.miaosha.seckill.eventModel;


import com.john.miaosha.entity.Event;
import com.john.miaosha.seckill.service.MessageFacadeService;
import com.john.miaosha.seckill.service.SeckillResultService;
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

    private Long orderId;

    private Long userId;

    private Long merchantId;

    private Long seckillResultId;

    private SeckillResultService seckillResultService;

    private MessageFacadeService messageFacadeService;

    public OrderEvent(String name, OrderState orderState){
        this.name = name;
        this.orderState = orderState;
    }
}
