package com.exam.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exam.pojo.Exam;
import com.exam.pojo.Student;
import com.exam.pojo.Teacher;
import com.exam.service.ExamService;
import com.exam.service.StudentService;
import com.exam.service.TeacherService;

@Controller
public class AdminController {
	Logger logger = Logger.getLogger(getClass());
	@Resource
	private TeacherService teacherService;
	@Resource
	private StudentService studentService;
	@Resource
	private ExamService examService;
	
	@RequestMapping("aLogin")
	public String login(HttpServletRequest request) {
		logger.debug("----------"+request.getParameter("username")+"·¢ÆðµÇÂ¼");
		Teacher teacher = teacherService.selectTeacherById(request.getParameter("username"));
		if(teacher!=null&&teacher.isTadmin()) {
			if(teacher.getTpwd().equals(request.getParameter("password"))) {
				logger.debug("----------µÇÂ¼³É¹¦");
				request.getSession().setAttribute("admin", teacher);
				return "redirect:aMain";
			}else {
				logger.debug("----------µÇÂ¼Ê§°Ü£¨ÃÜÂë´íÎó£©");
				request.setAttribute("errorCode", "ÃÜÂë´íÎó");
				return "forward:login.jsp";
			}
		}else {
			logger.debug("----------µÇÂ¼Ê§°Ü£¨ÓÃ»§²»´æÔÚ£©");
			request.setAttribute("errorCode", "¸Ã¹ÜÀíÔ±²»´æÔÚ");
			return "forward:login.jsp";
		}
	}
	
	@RequestMapping("aMain")
	public String main(HttpServletRequest request) {
		request.setAttribute("active_0", "active");
		return "admin_main";
	}
	
	@RequestMapping("tManager")
	public String teacherManager(HttpServletRequest request) {
		List<Teacher> teachers = teacherService.selectAllTeacher();
		request.setAttribute("teachers", teachers);
		request.setAttribute("active_1", "active");
		return "admin_teacher";
	}
	
	@RequestMapping("addTeacher")
	public String addTeacher(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		Teacher teacher = new Teacher();
		teacher.setTid(request.getParameter("tid"));
		teacher.setTname(request.getParameter("tname"));
		teacher.setTpwd(request.getParameter("tpwd"));
		teacher.setTadmin(Boolean.parseBoolean(request.getParameter("tadmin")));
		try {
			if(teacherService.insertTeacher(teacher)>0){
				return "redirect:tManager";
			}else {
				request.setAttribute("errorCode", "Ìí¼ÓÊ§°Ü");
				return "forward:tManager";
			}
		} catch (Exception e) {
			request.setAttribute("errorCode", "Ìí¼ÓÊ§°Ü");
			return "forward:tManager";
		}
	}
	
	@RequestMapping("editTeacher")
	public String editTeacher(HttpServletRequest request) {
		Teacher teacher = teacherService.selectTeacherById(request.getParameter("tid"));
		request.setAttribute("teacher", teacher);
		return "admin_teacher_edit";
	}
	
	@RequestMapping("updateTeacher")
	public String updateTeacher(HttpServletRequest request) {
		Teacher teacher = new Teacher();
		teacher.setTid(request.getParameter("tid"));
		teacher.setTname(request.getParameter("tname"));
		teacher.setTpwd(request.getParameter("tpwd"));
		teacher.setTadmin(Boolean.parseBoolean(request.getParameter("tadmin")));
		try {
			if(teacherService.updateTeacherById(teacher)>0){
				return "redirect:tManager";
			}else {
				request.setAttribute("errorCode", "±£´æÊ§°Ü");
				request.setAttribute("tid", teacher.getTid());
				return "forward:editTeacher";
			}
		} catch (Exception e) {
			request.setAttribute("errorCode", "±£´æÊ§°Ü");
			request.setAttribute("tid", teacher.getTid());
			return "forward:editTeacher";
		}
	}
	
	@RequestMapping("deleteTeacher")
	public String deleteTeacher(HttpServletRequest request) {
		try {
			if(teacherService.deleteTeacherById(request.getParameter("tid"))>0){
				return "redirect:tManager";
			}else {
				request.setAttribute("errorCodeDel", "É¾³ýÊ§°Ü");
				return "forward:tManager";
			}
		} catch (Exception e) {
			request.setAttribute("errorCodeDel", "É¾³ýÊ§°Ü");
			return "forward:tManager";
		}
	}
	
	@RequestMapping("sManager")
	public String studentManager(HttpServletRequest request) {
		List<Student> students = studentService.selectAllStudent();
		request.setAttribute("students", students);
		request.setAttribute("active_2", "active");
		return "admin_student";
	}
	
	@RequestMapping("addStudent")
	public String addStudent(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		Student student = new Student();
		student.setSid(request.getParameter("sid"));
		student.setSname(request.getParameter("sname"));
		student.setSpwd(request.getParameter("spwd"));
		try {
			if(studentService.insertStudent(student)>0){
				return "redirect:sManager";
			}else {
				request.setAttribute("errorCode", "Ìí¼ÓÊ§°Ü");
				return "forward:sManager";
			}
		} catch (Exception e) {
			request.setAttribute("errorCode", "Ìí¼ÓÊ§°Ü");
			return "forward:sManager";
		}
	}
	
	@RequestMapping("editStudent")
	public String editStudent(HttpServletRequest request) {
		Student student = studentService.selectStudentById(request.getParameter("sid"));
		request.setAttribute("student", student);
		return "admin_student_edit";
	}
	
	@RequestMapping("updateStudent")
	public String updateStudent(HttpServletRequest request) {
		Student student = new Student();
		student.setSid(request.getParameter("sid"));
		student.setSname(request.getParameter("sname"));
		student.setSpwd(request.getParameter("spwd"));
		try {
			if(studentService.updateStudentById(student)>0){
				return "redirect:sManager";
			}else {
				request.setAttribute("errorCode", "±£´æÊ§°Ü");
				request.setAttribute("sid", student.getSid());
				return "forward:editStudent";
			}
		} catch (Exception e) {
			request.setAttribute("errorCode", "±£´æÊ§°Ü");
			request.setAttribute("sid", student.getSid());
			return "forward:editStudent";
		}
	}
	
	@RequestMapping("deleteStudent")
	public String deleteStudent(HttpServletRequest request) {
		try {
			if(studentService.deleteStudentById(request.getParameter("sid"))>0){
				return "redirect:sManager";
			}else {
				request.setAttribute("errorCodeDel", "É¾³ýÊ§°Ü");
				return "forward:sManager";
			}
		} catch (Exception e) {
			request.setAttribute("errorCodeDel", "É¾³ýÊ§°Ü");
			return "forward:sManager";
		}
	}
	
	@RequestMapping("eManager")
	public String examManager(HttpServletRequest request) {
		List<Exam> exams = examService.selectAllExam();
		request.setAttribute("exams", exams);
		request.setAttribute("active_3", "active");
		return "admin_exam";
	}
	@RequestMapping("delExam")
	public String deleteExam(HttpServletRequest request) {
		try {
			int eid = Integer.parseInt(request.getParameter("eid"));
			String rootPath = request.getServletContext().getRealPath("files");
			String examdir = rootPath + File.separator + examService.selectExamById(eid).getTid() + File.separator + eid;
			teacherService.deleteFile(examdir);
			examService.deleteExamById(eid);
			return "redirect:eManager";
		} catch (Exception e) {
			return "redirect:eManager";
		}
	}
	
	
	@RequestMapping("aLogout")
	public String alogout(HttpServletRequest request) {
		logger.debug(((Teacher)request.getSession().getAttribute("admin")).getTid()+"ÍË³öÏµÍ³");
		request.getSession().invalidate();
		return "redirect:login.jsp";
	}
	
	@RequestMapping("aConfig")
	public String config(HttpServletRequest request) {
		return "admin_config";
	}
}
