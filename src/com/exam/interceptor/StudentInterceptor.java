package com.exam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.exam.pojo.Student;

public class StudentInterceptor implements HandlerInterceptor{
	@Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse HttpServletResponse, Object o) throws Exception {
		Student student = (Student)httpServletRequest.getSession().getAttribute("student");
		if(student==null) {
			HttpServletResponse.sendRedirect("login.jsp");
			return false;
		}else {
			return true;
		}
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //��ִ����Controller������ᴥ���˷���
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
