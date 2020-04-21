package com.kuir.library.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //目标方法执行前
    /**
     * 拦截器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if(user == null){
            //未登录，返回登录页面
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            response.getWriter().println("没有权限，请先登录");
            return false;
        }else {
            //已登录，放行请求
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

