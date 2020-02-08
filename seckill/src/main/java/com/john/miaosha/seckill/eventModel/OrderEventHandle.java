package com.john.miaosha.seckill.eventModel;

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
            try {
                log.info("开始订单处理");
                Event completeEvent = new OrderEvent("complete", OrderState.COPLETE);
                centralEventProcessor.centralQueue.put(completeEvent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class OrderCompleteStateProcessor implements StateProcessor {
        @Override
        public void process(Event event) {
            try {
                log.info("结束订单处理");
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
