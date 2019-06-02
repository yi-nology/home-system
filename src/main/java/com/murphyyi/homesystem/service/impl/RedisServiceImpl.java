package com.murphyyi.homesystem.service.impl;

import com.murphyyi.homesystem.service.RedisService;
import com.murphyyi.homesystem.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisServiceImpl
 * @description:
 * @author: zhangyi
 * @since: 2019-05-05 04:30
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void set(String key, Object value, Integer sec) {
        if(!(value instanceof String) && !(value instanceof Map) &&  !(value instanceof List) ){
            try {
                value = ObjectUtils.objectToMap(value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (value instanceof String) {
            redisTemplate.opsForValue().set(key, value, sec, TimeUnit.SECONDS);
        }
        if (value instanceof Map) {
            redisTemplate.opsForHash().putAll(key, (Map<?, ?>) value);
            redisTemplate.expire(key, sec, TimeUnit.SECONDS);
        }
        if (value instanceof List) {
            redisTemplate.opsForList().leftPushAll(key, value);
            redisTemplate.expire(key, sec, TimeUnit.SECONDS);
        }
    }

    @Override
    public Object get(String key, Class clazz) {
        if (clazz == String.class) {
            return redisTemplate.opsForValue().get(key);
        }
        if (clazz == Map.class) {
            Set set = redisTemplate.opsForHash().keys(key);
            if (set != null) {
                return redisTemplate.opsForHash().entries(key);
            }
        }
        if (clazz == List.class) {
            return redisTemplate.opsForList().range(key, 0, -1);
        }
        return null;
    }

    @Override
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Boolean refreshOrAdd(String key, String keyMap, Object value, Integer sec) {

        if (keyMap != null) {
            redisTemplate.opsForHash().put(key, keyMap, value);
        }
        if (value instanceof List) {
            redisTemplate.opsForList().rightPushAll(key, value);
        }
        return redisTemplate.expire(key, sec, TimeUnit.SECONDS);
    }
}
