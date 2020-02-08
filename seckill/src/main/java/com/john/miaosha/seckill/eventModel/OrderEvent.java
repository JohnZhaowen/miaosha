package com.john.miaosha.seckill.eventModel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEvent extends Event {

    private String name;

    private OrderState orderState;
}
