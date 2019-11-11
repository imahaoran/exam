package com.exam.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.exam.pojo.Exam;
import com.exam.pojo.Result;
import com.exam.pojo.Student;
import com.exam.service.ExamService;
import com.exam.service.ResultService;
import com.exam.service.StudentService;

@Controller
public class StudentController {
	Logger logger = Logger.getLogger(getClass());
	private Student student;
	@Resource
	private StudentService studentService;
	@Resource
	private ExamService examService;
	@Resource
	private ResultService resultService;
	@RequestMapping("sLogin")
	public String login(HttpServletRequest request) {
		logger.debug("----------"+request.getParameter("username")+"发起登录");
		student = studentService.selectStudentById(request.getParameter("username"));
		if(student!=null) {
			if(student.getSpwd().equals(request.getParameter("password"))) {
				logger.debug("----------登录成功");
				request.getSession().setAttribute("student", student);
				return "redirect:sMain";
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
	@RequestMapping("sMain")
	public String main(HttpServletRequest request){
		request.setAttribute("active_0", "active");
		return "student_main";
	}
	@RequestMapping("exams")
	public String exams(HttpServletRequest request){
		List<Exam> exams = examService.selectActiveExams();
		request.setAttribute("exams", exams);
		request.setAttribute("active_1", "active");
		return "student_exams";
	}
	@RequestMapping("grades")
	public String grades(HttpServletRequest request){
		List<Result> results = resultService.selectResultBySid(student.getSid());
		request.setAttribute("results", results);
		request.setAttribute("active_2", "active");
		return "student_grades";
	}
	@RequestMapping("examming")
	public String examming(HttpServletRequest request){
		int eid = Integer.parseInt(request.getParameter("eid"));
		Result result = resultService.selectBySE(student.getSid(), eid);
		if(result!=null) {
			request.setAttribute("result", result);
			return "student_examming";
		}else {
			request.setAttribute("errorEid", eid);
			return "forward:exams";
		}
		
	}
	@RequestMapping("download")
	public void download(HttpServletRequest request,HttpServletResponse response) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		Exam exam = examService.selectExamById(eid);
		String rootPath = request.getServletContext().getRealPath("files");
		String realPath = rootPath + "\\" + exam.getEpaper();
		File file = new File(realPath);
		if(file.exists()) {
			try {
				String fileName = file.getName();
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
				response.setHeader("Content-Disposition", "attachment;filename="+fileName);
				byte[] bytes = FileUtils.readFileToByteArray(file);
				ServletOutputStream os = response.getOutputStream();
				os.write(bytes);
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug("下载失败");
			}
		}else {
			logger.debug("文件不存在");
		}
	}
	@RequestMapping("upload")
	public String upload(HttpServletRequest request,MultipartFile file) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		String sid = student.getSid();
		String path = request.getServletContext().getRealPath("files");
		try {
			studentService.upload(eid, sid, path, file);
			return "forward:examming";
		} catch (Exception e) {			
			e.printStackTrace();
			request.setAttribute("errorCodeup", "本次上传失败");
			return "forward:examming";
		}
	}
	
	
	
	
	
	
	
	@RequestMapping("sPwd")
	public String tpwd(HttpServletRequest request) {
		return "student_pwd";
	}
	@RequestMapping("sUpdatePwd")
	public String tUpdatePwd(HttpServletRequest request) {
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd1");
		if(student.getSpwd().equals(oldpwd)) {
			try {
				student.setSpwd(newpwd);
				if(studentService.updateStudentById(student)>0) {
					return "redirect:sMain";
				}else {
					request.setAttribute("errorCode", "修改失败");
					return "forward:sPwd";
				}
			} catch (Exception e) {
				request.setAttribute("errorCode", "修改失败");
				return "forward:sPwd";
			}
		}else {
			request.setAttribute("errorCode", "原密码不匹配");
			return "forward:sPwd";
		}
	}
	@RequestMapping("sLogout")
	public String tlogout(HttpServletRequest request) {
		logger.debug(((Student)request.getSession().getAttribute("student")).getSid()+"退出系统");
		request.getSession().invalidate();
		return "redirect:login.jsp";
	}
}
