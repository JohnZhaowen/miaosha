package com.john.miaosha.seckill.eventModel;

import com.john.miaosha.seckill.strategy.SeckillOperator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeckillEvent extends Event {

    private String name;

    private SeckillState seckillState;

    private SeckillOperator seckillOperator;

    private Long id;

    private Long userId;

    public SeckillEvent(String name, SeckillState seckillState){
        this.name = name;
        this.seckillState = seckillState;
    }
}
