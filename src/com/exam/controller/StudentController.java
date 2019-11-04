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
		logger.debug("----------"+request.getParameter("username")+"�����¼");
		Student student = studentService.selectStudentById(request.getParameter("username"));
		if(student!=null) {
			if(student.getSpwd().equals(request.getParameter("password"))) {
				logger.debug("----------��¼�ɹ�");
				request.getSession().setAttribute("student", student);
				return "student_main";
			}else {
				logger.debug("----------��¼ʧ�ܣ��������");
				request.setAttribute("errorCode", "�������");
				return "forward:login.jsp";
			}
		}else {
			logger.debug("----------��¼ʧ�ܣ��û������ڣ�");
			request.setAttribute("errorCode", "��ѧ��������");
			return "forward:login.jsp";
		}
	}
}
