package com.john.miaosha.seckill.eventModel;

import com.john.miaosha.entity.Event;

public interface StateProcessor {
    void process(Event event);
}
