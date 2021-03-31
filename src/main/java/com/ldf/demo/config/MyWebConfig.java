package com.ldf.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: 清峰
 * @date: 2020/11/3 20:29
 * @code: 愿世间永无Bug!
 * @description: 添加拦截器，拦截资源
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterfaceConfig())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login","/static/*");
    }
}
