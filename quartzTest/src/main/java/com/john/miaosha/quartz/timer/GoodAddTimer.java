package com.john.miaosha.quartz.timer;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;

/**
 * 商品添加定时任务实现类
 */
@Slf4j
public class GoodAddTimer extends QuartzJobBean {

    /**
     * 定时任务实现逻辑
     * 每当触发器触发时会执行该方法
     * @param context 任务执行上下文
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("分布式节点quartz-cluster-node-first, 商品添加任务，任务执行时间[{}]", LocalDateTime.now());
    }
}
