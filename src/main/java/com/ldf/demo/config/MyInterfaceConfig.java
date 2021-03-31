package com.ldf.demo.config;


import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 清峰
 * @date: 2020/11/3 20:25
 * @code: 愿世间永无Bug!
 * @description: //自定义拦截器：实现拦截器接口，重写预处理方法
 */
public class MyInterfaceConfig implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user =request.getSession().getAttribute("user");
        if (user==null){
            response.sendRedirect("/");
        }
        return true;
    }
}
