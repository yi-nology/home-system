package com.murphyyi.homesystem.utils;

import java.time.LocalDate;

/**
 * @program: teach-interactive-manager
 * @ClassName: DateUtil
 * @description: 时间工具类封装对象
 * @author: zhangyi
 * @create: 2019-04-17 10:58
 **/

public class DateUtil {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static LocalDate DataPlusMonth(LocalDate time, Integer month){
        Integer y = time.getDayOfYear();
        Integer m = time.getMonthValue();
        if (month + m  > 12){
            y+=1;
        }
        return LocalDate.of(y,m,1);
    }
}
