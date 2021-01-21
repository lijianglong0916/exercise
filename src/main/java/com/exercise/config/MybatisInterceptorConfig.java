package com.exercise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author jianglong.li@hand-china.com
 * @date 2021-01-11 14:12
 **/
@Configuration
public class MybatisInterceptorConfig{
    @Bean
    public MybatisSqlInterceptor mybatisInterceptor() {
        MybatisSqlInterceptor interceptor = new MybatisSqlInterceptor();
        Properties properties = new Properties();
        // 可以调用properties.setProperty方法来给拦截器设置一些自定义参数
        interceptor.setProperties(properties);
        return interceptor;
    }
}
