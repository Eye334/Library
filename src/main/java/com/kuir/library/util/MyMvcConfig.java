package com.kuir.library.util;

import com.kuir.library.util.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc   不要接管SpringMVC
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    //所有的WebMvcConfigurerAdapter组件都会一起起作用
   @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**").excludePathPatterns("/","/login","/doLogin","/static/**","/templates/**","/log","/css/**","/images/**","/forgetpasswords","/upPass","/register","/doRegist","/doLogin","/dolog","/toUser");
    }
}
