package com.john.miaosha.utils;

import redis.clients.jedis.Jedis;

import java.util.Collections;

public class RedisUtil {

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

    public static String get(String key){
        return jedis.get(key);
    }
    public static void set(String key, String value){
        jedis.set(key, value);
    }

}
