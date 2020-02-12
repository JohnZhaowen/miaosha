package com.john.miaosha.seckill.eventModel;

import com.john.miaosha.entity.Event;
import com.john.miaosha.entity.SeckillResult;
import com.john.miaosha.form.OrderRequest;
import com.john.miaosha.seckill.service.SeckillResultServiceImpl;
import com.john.miaosha.seckill.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class OrderEventHandle implements Handler {

    private CentralEventProcessor centralEventProcessor;

    private OrderStateMechine orderStateMechine = new OrderStateMechine();

    public OrderEventHandle(CentralEventProcessor centralEventProcessor){
        this.centralEventProcessor = centralEventProcessor;
        orderStateMechine.register();
    }

    @Override
    public void handle(Event event) {
        try {
            OrderEvent orderEvent = (OrderEvent)event;
            this.orderStateMechine.getOrderStateProcessor(orderEvent).process(orderEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class OrderNewStateProcessor implements StateProcessor {
        @Override
        public void process(Event event) {
            log.info("开始订单处理");
            OrderEvent orderEvent = (OrderEvent)event;
            orderEvent.getMessageFacadeService().sendMsg(new OrderRequest(orderEvent.getId(),
                    orderEvent.getUserId(), orderEvent.getMerchantId(), orderEvent.getSeckillResultId()));
        }
    }
    class OrderCompleteStateProcessor implements StateProcessor {
        @Override
        public void process(Event event) {
            try {
                log.info("结束订单处理");
                //更新秒杀结果状态
                OrderEvent orderEvent = (OrderEvent)event;
                SeckillResult seckillResult = new SeckillResult();
                seckillResult.setResult(1);
                seckillResult.setId(orderEvent.getSeckillResultId());
                seckillResult.setOrderId(orderEvent.getOrderId());
                seckillResult.setResultData("秒杀成功");
                seckillResult.setUserId(orderEvent.getUserId());
                seckillResult.setSeckillId(orderEvent.getId());
                SpringUtil.getBean(SeckillResultServiceImpl.class).updateSeckillResult(seckillResult);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    class OrderFailStateProcessor implements StateProcessor {
        @Override
        public void process(Event event) {
            log.info("订单处理异常");
        }
    }

    class OrderStateMechine {

        private Map<OrderState, StateProcessor> orderStateStateProcessorMap = new HashMap<>();

        public StateProcessor getOrderStateProcessor(OrderEvent event){
            return orderStateStateProcessorMap.get(event.getOrderState());
        }

        public void register(){
            orderStateStateProcessorMap.put(OrderState.NEW, new OrderNewStateProcessor());
            orderStateStateProcessorMap.put(OrderState.COPLETE, new OrderCompleteStateProcessor());
            orderStateStateProcessorMap.put(OrderState.FAIL, new OrderFailStateProcessor());
        }

    }


}
