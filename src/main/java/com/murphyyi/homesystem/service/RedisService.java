package com.murphyyi.homesystem.service;

/**
 * @ClassName: RedisService
 * @description:
 * @author: zhangyi
 * @since: 2019-05-05 04:30
 */
public interface RedisService {

    void set(String key, Object value, Integer sec);

    Object get(String key, Class clazz);

    Boolean del(String key);

    Boolean refreshOrAdd(String key,  String keyMap, Object value, Integer sec);
}
