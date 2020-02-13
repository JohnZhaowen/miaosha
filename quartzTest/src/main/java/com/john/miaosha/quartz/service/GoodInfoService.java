package com.john.miaosha.quartz.service;

import com.john.miaosha.quartz.entity.GoodInfo;
import com.john.miaosha.quartz.jpa.GoodInfoRepository;
import com.john.miaosha.quartz.timer.GoodAddTimer;
import com.john.miaosha.quartz.timer.GoodSeckillRemindTimer;
import com.john.miaosha.quartz.timer.GoodStockCheckTimer;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodInfoService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private GoodInfoRepository goodInfoRepository;

    public Long saveGood(GoodInfo goodInfo){
        goodInfoRepository.save(goodInfo);
        //构建创建商品的定时任务
        buildCreateGoodTimer();

        //构建商品库存校验的定时任务
        buildGoodStockTimer();

        //构建商品秒杀提醒的定时任务
        buildGoodSeckillRemindTimer(goodInfo.getId());

        return goodInfo.getId();

    }

    private void buildCreateGoodTimer() {
        //设置开始时间为1min之后
        long startAtTIme = System.currentTimeMillis() + 1000 * 60;
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = GoodAddTimer.class.getName();
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(GoodAddTimer.class).withIdentity(name, group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startAtTIme)).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private void buildGoodStockTimer() {
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = GoodStockCheckTimer.class.getName();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(GoodStockCheckTimer.class).withIdentity(name, group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    private void buildGoodSeckillRemindTimer(Long id) {
        //秒杀开始时间
        long startAtTIme = System.currentTimeMillis() + 1000 * 60;
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = GoodSeckillRemindTimer.class.getName();

        JobDetail jobDetail = JobBuilder.newJob(GoodSeckillRemindTimer.class).withIdentity(name, group).build();
        jobDetail.getJobDataMap().put("goodId", id);

        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startAtTIme)).build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }



}
