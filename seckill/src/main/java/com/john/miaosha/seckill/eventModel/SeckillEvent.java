package com.john.miaosha.seckill.eventModel;

import com.john.miaosha.entity.Event;
import com.john.miaosha.seckill.service.MessageFacadeService;
import com.john.miaosha.seckill.service.SeckillResultService;
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

    private Long merchantId;

    private Long seckillResultId;

    private MessageFacadeService messageFacadeService;

    private SeckillResultService seckillResultService;

    public SeckillEvent(String name, SeckillState seckillState){
        this.name = name;
        this.seckillState = seckillState;
    }
}
