package com.john.miaosha.seckill.eventModel;

import com.john.miaosha.entity.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
@Service
public class CentralEventProcessor {

    public Map<Class<? extends Event>, Handler> distribute = new HashMap<>();

    public BlockingDeque<Event> centralQueue = new LinkedBlockingDeque<>();

    public CentralDistributeHandle centralDistributeHandle = new CentralDistributeHandle();

    public EventProess eventProess = new EventProess();

    public CentralEventProcessor(){
        distribute.put(SeckillEvent.class, new SeckillEventHandle(this));
        distribute.put(OrderEvent.class, new OrderEventHandle(this));
        eventProess.start();
    }

    public void process(Event event){
        centralDistributeHandle.handle(event);
    }

    class CentralDistributeHandle implements Handler {
        @Override
        public void handle(Event event) {
            try {
                centralQueue.put(event);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class EventProess extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    Event event = centralQueue.take();
                    distribute.get(event.getClass()).handle(event);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        CentralEventProcessor centralEventProcessor = new CentralEventProcessor();
        centralEventProcessor.process(new SeckillEvent("new", SeckillState.NEW));

    }



}
