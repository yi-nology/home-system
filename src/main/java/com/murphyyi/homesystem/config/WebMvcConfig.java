//package com.murphyyi.homesystem.config;
//
//
//import com.murphyyi.homesystem.interceptor.LoginInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
///**
// * @program: teach-interactive-manager
// * @ClassName: WebMvcConfig
// * @description: 配置拦截器过滤器监听器等
// * @author: zhangyi
// * @create: 2019-04-19 14:48
// **/
//
//@Configuration
//public class WebMvcConfig extends WebMvcConfigurationSupport {
//
//    @Bean
//    LoginInterceptor localInterceptor() {
//        return new LoginInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/login")
//                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
//    }
//
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//}
