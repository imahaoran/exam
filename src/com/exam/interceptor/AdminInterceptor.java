package com.exam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.exam.pojo.Teacher;

public class AdminInterceptor implements HandlerInterceptor{
	@Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse HttpServletResponse, Object o) throws Exception {
		Teacher admin = (Teacher)httpServletRequest.getSession().getAttribute("admin");
		if(admin==null) {
			HttpServletResponse.sendRedirect("login.jsp");
			return false;
		}else {
			return true;
		}
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //在执行完Controller函数后会触发此方法
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
