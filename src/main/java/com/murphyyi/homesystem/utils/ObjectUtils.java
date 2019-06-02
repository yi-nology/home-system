package com.murphyyi.homesystem.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ObjectUtils
 * @description:
 * @author: zhangyi
 * @since: 2019-05-05 03:16
 */
public class ObjectUtils {
    /**
    * @Description: 获取利用反射获取类里面的值和名称
    * @Param1: obj
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: zhangyi
    * @Date: 2019-05-05
    */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }
}
