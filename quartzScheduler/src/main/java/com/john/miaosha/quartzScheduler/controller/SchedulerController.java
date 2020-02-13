package com.john.miaosha.quartzScheduler.controller;

import com.john.miaosha.quartzScheduler.service.OrderFacadeService;
import com.john.miaosha.quartzScheduler.service.ProductFacadeService;
import com.john.miaosha.quartzScheduler.service.SchedulerService;
import com.john.miaosha.quartzScheduler.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;


    @GetMapping(value = "/order/overdue")
    public void orderOverduw(){
        schedulerService.scannerOrderOverdue();
    }
}
