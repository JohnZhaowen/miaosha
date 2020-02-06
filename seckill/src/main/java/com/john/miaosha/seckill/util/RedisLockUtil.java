package com.john.miaosha.seckill.util;

import redis.clients.jedis.Jedis;

import java.util.Collections;

public class RedisLockUtil {

    private static Jedis jedis = new Jedis("127.0.0.1", 6379);

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    public static boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime){
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        return LOCK_SUCCESS.equals(result);
    }

    public static boolean releaseDistributedLock(String lockKey, String requestId){
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        return RELEASE_SUCCESS.equals(result);
    }

    public static void main(String[] args) {
        boolean firstSuccess1 = tryGetDistributedLock("lockKey1", "123", 10000);
        boolean firstSuccess2 = tryGetDistributedLock("lockKey1", "123", 10000);
        System.out.println("第1次获取锁：" + firstSuccess1);
        System.out.println("第2次获取锁：" + firstSuccess2);

        boolean firstRelease1 = releaseDistributedLock("lockKey1", "1234");
        boolean firstRelease2 = releaseDistributedLock("lockKey1", "123");
        boolean firstRelease3 = releaseDistributedLock("lockKey1", "123");

        System.out.println("第1次释放锁：" + firstRelease1);
        System.out.println("第2次释放锁：" + firstRelease2);
        System.out.println("第3次释放锁：" + firstRelease3);


    }

}
