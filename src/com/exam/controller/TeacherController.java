package com.exam.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exam.pojo.Exam;
import com.exam.pojo.Result;
import com.exam.pojo.Student;
import com.exam.pojo.Teacher;
import com.exam.service.ExamService;
import com.exam.service.ResultService;
import com.exam.service.StudentService;
import com.exam.service.TeacherService;

@Controller
public class TeacherController{
	Logger logger = Logger.getLogger(getClass());
	private Teacher teacher;
	@Resource
	private ResultService resultService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private StudentService studentService;
	@Resource
	private ExamService examService;
	@RequestMapping("tLogin")
	public String login(HttpServletRequest request) {
		logger.debug("----------"+request.getParameter("username")+"�����¼");
		teacher = teacherService.selectTeacherById(request.getParameter("username"));
		if(teacher!=null) {
			if(teacher.getTpwd().equals(request.getParameter("password"))) {
				logger.debug("----------��¼�ɹ�");
				request.getSession().setAttribute("teacher", teacher);
				return "redirect:tMain";
			}else {
				logger.debug("----------��¼ʧ�ܣ��������");
				request.setAttribute("errorCode", "�������");
				return "forward:login.jsp";
			}
		}else {
			logger.debug("----------��¼ʧ�ܣ��û������ڣ�");
			request.setAttribute("errorCode", "�ý�ʦ������");
			return "forward:login.jsp";
		}
	}
	@RequestMapping("tMain")
	public String main(HttpServletRequest request) {
		request.setAttribute("active_0", "active");
		return "teacher_main";
	}
	@RequestMapping("examManager")
	public String teacherManager(HttpServletRequest request) {
		List<Exam> exams = examService.selectAllExam();
		request.setAttribute("exams", exams);
		request.setAttribute("active_1", "active");
		return "teacher_exam";
	}
	@RequestMapping("addExam")
	public String addExam(HttpServletRequest request) {
		Exam exam = new Exam();
		exam.setEname(request.getParameter("examname"));
		exam.setEtime(request.getParameter("examtime"));
		exam.setTeacher(teacher);
		exam.setTid(teacher.getTid());
		try {
			if(examService.insertExam(exam)>0){
				return "redirect:examManager";
			}else {
				request.setAttribute("errorCode", "���ʧ��");
				return "forward:examManager";
			}
		} catch (Exception e) {
			request.setAttribute("errorCode", "���ʧ��");
			return "forward:examManager";
		}
	}
	@RequestMapping("editExam")
	public String editExam(HttpServletRequest request) {
		Exam exam = examService.selectExamById(Integer.parseInt(request.getParameter("eid")));
		request.setAttribute("exam", exam);
		return "teacher_exam_edit";
	}
	@RequestMapping("updateExam")
	public String tUpdateExam(HttpServletRequest request) {
		Exam exam = examService.selectExamById(Integer.parseInt(request.getParameter("eid")));
		String ename = request.getParameter("ename");
		String etime = request.getParameter("etime");
		try {
			exam.setEname(ename);
			exam.setEtime(etime);
			exam.setEautostart(Boolean.parseBoolean(request.getParameter("eautostart")));
			examService.updateExam(exam);
			request.setAttribute("exam", exam);
			request.setAttribute("successCode", "����ɹ�");
			return "teacher_exam_edit";
		} catch (Exception e) {
			request.setAttribute("exam", exam);
			request.setAttribute("errorCode", "����ʧ��");
			return "teacher_exam_edit";
		}
	}
	@RequestMapping("deleteExam")
	public String deleteExam(HttpServletRequest request) {
		try {
			examService.deleteExamById(Integer.parseInt(request.getParameter("eid")));
			return "redirect:examManager";
		} catch (Exception e) {
			request.setAttribute("errorCodeDel", "ɾ��ʧ��");
			return "forward:editExam";
		}
	}
	@RequestMapping("importStudent")
	public String importStudent(HttpServletRequest request) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		Exam exam = examService.selectExamById(eid);
		List<Student> students;
		try {
			students = examService.selectStudents(eid);
			request.setAttribute("students", students);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("exam", exam);
		return "teacher_import";
	}
	@RequestMapping("addResult")
	public String addStudent(HttpServletRequest request) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		String sid = request.getParameter("sid");
		String sname = request.getParameter("sname");
		Student student = studentService.selectStudentById(sid);
		if(student!=null&&student.getSname().equals(sname)) {
			Result result = new Result();
			result.setEid(eid);
			result.setSid(sid);
			try {
				resultService.insertResult(result);
				request.setAttribute("eid", eid);
				return "forward:importStudent";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorCode", "���ʧ��");
				request.setAttribute("eid", eid);
				return "forward:importStudent";
			}
		}else {
			request.setAttribute("errorCode", "�޴�ѧ��");
			request.setAttribute("eid", eid);
			return "forward:importStudent";
		}
	}
	@RequestMapping("examStatus")
	public String examStatus(HttpServletRequest request) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		List<Result> results = resultService.selectResultByEid(eid);
		request.setAttribute("results", results);
		return "teacher_exam_status";
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping("tPwd")
	public String tpwd(HttpServletRequest request) {
		return "teacher_pwd";
	}
	@RequestMapping("tUpdatePwd")
	public String tUpdatePwd(HttpServletRequest request) {
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd1");
		if(teacher.getTpwd().equals(oldpwd)) {
			try {
				teacher.setTpwd(newpwd);
				if(teacherService.updateTeacherById(teacher)>0) {
					return "redirect:tMain";
				}else {
					request.setAttribute("errorCode", "�޸�ʧ��");
					return "forward:tPwd";
				}
			} catch (Exception e) {
				request.setAttribute("errorCode", "�޸�ʧ��");
				return "forward:tPwd";
			}
		}else {
			request.setAttribute("errorCode", "ԭ���벻ƥ��");
			return "forward:tPwd";
		}
	}
	@RequestMapping("tLogout")
	public String tlogout(HttpServletRequest request) {
		logger.debug(((Teacher)request.getSession().getAttribute("teacher")).getTid()+"�˳�ϵͳ");
		request.getSession().invalidate();
		return "redirect:login.jsp";
	}
}
