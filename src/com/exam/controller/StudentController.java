package com.exam.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exam.pojo.Student;
import com.exam.service.StudentService;

@Controller
public class StudentController {
	Logger logger = Logger.getLogger(getClass());
	@Resource
	private StudentService studentService;
	@RequestMapping("sLogin")
	public String login(HttpServletRequest request) {
		logger.debug("----------"+request.getParameter("username")+"发起登录");
		Student student = studentService.selectStudentById(request.getParameter("username"));
		if(student!=null) {
			if(student.getSpwd().equals(request.getParameter("password"))) {
				logger.debug("----------登录成功");
				request.getSession().setAttribute("student", student);
				return "student_main";
			}else {
				logger.debug("----------登录失败（密码错误）");
				request.setAttribute("errorCode", "密码错误");
				return "forward:login.jsp";
			}
		}else {
			logger.debug("----------登录失败（用户不存在）");
			request.setAttribute("errorCode", "该学生不存在");
			return "forward:login.jsp";
		}
	}
}
