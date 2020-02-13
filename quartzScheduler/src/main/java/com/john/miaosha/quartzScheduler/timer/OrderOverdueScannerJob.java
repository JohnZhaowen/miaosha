package com.john.miaosha.quartzScheduler.timer;

import com.john.miaosha.entity.SeckillInfo;
import com.john.miaosha.entity.SeckillOrder;
import com.john.miaosha.quartzScheduler.service.OrderFacadeService;
import com.john.miaosha.quartzScheduler.service.ProductFacadeService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * 商品添加定时任务实现类
 */
@Slf4j
public class OrderOverdueScannerJob extends QuartzJobBean {

    @Autowired
    private OrderFacadeService orderService;

    @Autowired
    private ProductFacadeService productService;

    /**
     * 定时任务实现逻辑
     * 每当触发器触发时会执行该方法
     * @param context 任务执行上下文
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext context) {
        log.info("当前任务hashCOde[{}]", this.hashCode());
        List<SeckillOrder> overdueOrders = orderService.listOrderByCurrentTime();
        for(SeckillOrder order : overdueOrders){
            Long seckillProductId = order.getSeckillProductId();
            orderService.updateOrderByFlag(new SeckillOrder(order.getId(), 2));
            productService.updateSeckillSeckillNum(new SeckillInfo(seckillProductId));
        }

        log.info("订单过期处理任务完成");
    }
}
