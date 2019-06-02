package com.murphyyi.homesystem.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import com.murphyyi.homesystem.utils.DateUtil;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.List;

/**
 * @program: teach-interactive-manager
 * @ClassName: ResponseJsonConfig
 * @description: 封装fastjson的配置信息
 * @author: zhangyi
 * @create: 2019-04-17 10:50
 **/

//@Configuration
//public class ResponseJsonConfig {
//
//    @Bean
//    public HttpMessageConverters fastJsonConfigure() {
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setDateFormat(DateUtil.YYYY_MM_DD_HH_MM_SS);
//        List<MediaType> fastMediaTypes = Lists.newArrayList(MediaType.APPLICATION_JSON_UTF8);
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        return new HttpMessageConverters(fastJsonHttpMessageConverter);
//    }
//
//
//}
