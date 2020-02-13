package com.john.miaosha.quartzScheduler.service;

import com.john.miaosha.quartzScheduler.timer.OrderOverdueScannerJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SchedulerService {

    @Autowired
    private Scheduler scheduler;

    public void scannerOrderOverdue(){
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = OrderOverdueScannerJob.class.getName();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(OrderOverdueScannerJob.class).withIdentity(name, group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("订单超时未支付扫描任务调度失败[{}]", e);
        }

    }



}
