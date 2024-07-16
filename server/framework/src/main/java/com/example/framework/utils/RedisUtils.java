package com.example.framework.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setRedis(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String getRedis(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

}
