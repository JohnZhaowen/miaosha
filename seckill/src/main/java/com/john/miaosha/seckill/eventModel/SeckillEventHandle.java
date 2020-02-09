package com.john.miaosha.seckill.eventModel;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SeckillEventHandle implements Handler {

    private CentralEventProcessor centralEventProcessor;

    private SeckillStateMechine seckillStateMechine = new SeckillStateMechine();

    public SeckillEventHandle(CentralEventProcessor centralEventProcessor){
        this.centralEventProcessor = centralEventProcessor;
        seckillStateMechine.register();
    }

    @Override
    public void handle(Event event) {
        try {
            SeckillEvent seckillEvent = (SeckillEvent)event;
            this.seckillStateMechine.getSeckillStateProcessor(seckillEvent).process(seckillEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class SeckillNewStateProcessor implements StateProcessor {
        @Override
        public void process(Event event) {
            try {
                log.info("开始秒杀");
                SeckillEvent seckillEvent = (SeckillEvent)event;
                seckillEvent.getSeckillOperator().seckill(seckillEvent.getId(), seckillEvent.getUserId());

                Event completeEvent = new SeckillEvent("complete", SeckillState.COPLETE, seckillEvent.getSeckillOperator(), seckillEvent.getId(),
                        seckillEvent.getUserId(), seckillEvent.getMerchantId(), seckillEvent.getMessageFacadeService());
                centralEventProcessor.centralQueue.put(completeEvent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class SeckillCompleteStateProcessor implements StateProcessor {
        @Override
        public void process(Event event) {
            try {
                log.info("结束秒杀");
                SeckillEvent seckillEvent = (SeckillEvent) event;
                Event orderNewEvent = new OrderEvent("new", OrderState.NEW, seckillEvent.getId(),
                        seckillEvent.getUserId(), seckillEvent.getMerchantId(), seckillEvent.getMessageFacadeService());
                centralEventProcessor.centralQueue.put(orderNewEvent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class SeckillFailStateProcessor implements StateProcessor {
        @Override
        public void process(Event event) {
            log.info("秒杀异常");
        }
    }

    class SeckillStateMechine {

        private Map<SeckillState, StateProcessor> seckillStateStateProcessorMap = new HashMap<>();

        public StateProcessor getSeckillStateProcessor(SeckillEvent event){
            return seckillStateStateProcessorMap.get(event.getSeckillState());
        }

        public void register(){
            seckillStateStateProcessorMap.put(SeckillState.NEW, new SeckillNewStateProcessor());
            seckillStateStateProcessorMap.put(SeckillState.COPLETE, new SeckillCompleteStateProcessor());
            seckillStateStateProcessorMap.put(SeckillState.FAIL, new SeckillFailStateProcessor());
        }

    }

}
