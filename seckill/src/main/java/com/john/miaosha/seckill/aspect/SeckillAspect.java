package com.john.miaosha.seckill.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
@Aspect
@Slf4j
public class SeckillAspect {

    private Lock lock = new ReentrantLock();

    @Pointcut("execution(public * com.john.miaosha.seckill.service.SeckillByServiceImpl.programLockByAop(*, *))")
    public void lockSeck(){}

    @Around("lockSeck()")
    public Object around(ProceedingJoinPoint joinPoint){

        log.info("=====aop方法=====");
        lock.lock();

        Object object = null;

        try {
            object = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            lock.unlock();
        }

        return object;
    }

}
