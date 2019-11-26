package com.exam.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
		logger.debug("----------"+request.getParameter("username")+"发起登录");
		teacher = teacherService.selectTeacherById(request.getParameter("username"));
		String password = request.getParameter("password");
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		if(teacher!=null) {
			if(teacher.getTpwd().equals(password)) {
				logger.debug("----------登录成功");
				request.getSession().setAttribute("teacher", teacher);
				return "redirect:tMain";
			}else {
				logger.debug("----------登录失败（密码错误）");
				request.setAttribute("errorCode", "密码错误");
				return "forward:login.jsp";
			}
		}else {
			logger.debug("----------登录失败（用户不存在）");
			request.setAttribute("errorCode", "该教师不存在");
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
				request.setAttribute("errorCode", "添加失败");
				return "forward:examManager";
			}
		} catch (Exception e) {
			request.setAttribute("errorCode", "添加失败");
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
			request.setAttribute("successCode", "保存成功");
			return "teacher_exam_edit";
		} catch (Exception e) {
			request.setAttribute("exam", exam);
			request.setAttribute("errorCode", "保存失败");
			return "teacher_exam_edit";
		}
	}
	@RequestMapping("deleteExam")
	public String deleteExam(HttpServletRequest request) {
		try {
			int eid = Integer.parseInt(request.getParameter("eid"));
			examService.deleteExamById(eid);
			String path = request.getServletContext().getRealPath("files")+"\\"+teacher.getTid()+"\\"+eid;
			FileUtils.deleteDirectory(new File(path));
			return "redirect:examManager";
		} catch (Exception e) {
			request.setAttribute("errorCodeDel", "删除失败");
			return "forward:editExam";
		}
	}
	@RequestMapping("upLoadPaper")
	public String upLoadPaper(HttpServletRequest request,MultipartFile file) {
		try {
			int eid = Integer.parseInt(request.getParameter("eid"));
			String path = request.getServletContext().getRealPath("files");
			teacherService.upLoadPaper(eid, path, file);
			request.setAttribute("successCodeup", "上传成功");
			return "forward:editExam";
		} catch (Exception e) {
			request.setAttribute("errorCodeup", "上传失败");
			return "forward:editExam";
		}
	}
	@RequestMapping("importStudent")
	public String importStudent(HttpServletRequest request) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		List<Result> results = resultService.selectResultByEid(eid);
		Exam exam = examService.selectExamById(eid);
		request.setAttribute("exam", exam);
		request.setAttribute("results", results);
		return "teacher_import";
	}
	@RequestMapping("addResult")
	public String addStudent(HttpServletRequest request) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		String sid = request.getParameter("sid");
		String sname = request.getParameter("sname");
		Student student = studentService.selectStudentById(sid);
		if(student!=null&&student.getSname().equals(sname)) {
			Result r = resultService.selectBySE(sid, eid);
			if(r!=null) {
				request.setAttribute("errorCode", "该生已添加");
				request.setAttribute("eid", eid);
				return "forward:importStudent";
			}
			Result result = new Result();
			result.setEid(eid);
			result.setSid(sid);
			try {
				resultService.insertResult(result);
				request.setAttribute("eid", eid);
				return "forward:importStudent";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorCode", "添加失败");
				request.setAttribute("eid", eid);
				return "forward:importStudent";
			}
		}else {
			request.setAttribute("errorCode", "无此学生");
			request.setAttribute("eid", eid);
			return "forward:importStudent";
		}
	}
	@RequestMapping("deleteResult")
	public String deleteResult(HttpServletRequest request) {
		int rid = Integer.parseInt(request.getParameter("rid"));
		try {
			resultService.deleteResultById(rid);
			return "forward:importStudent";
		} catch (Exception e) {
			e.printStackTrace();
			return "forward:importStudent";
		}
	}
	@RequestMapping("examStart")
	public String examStart(HttpServletRequest request) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		Exam exam = examService.selectExamById(eid);
		exam.setEactive(true);
		try {
			examService.updateExam(exam);
			return "redirect:examManager";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:examManager";
		}
	}
	@RequestMapping("examStatus")
	public String examStatus(HttpServletRequest request) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		List<Result> results = resultService.selectResultByEid(eid);
		Exam exam = examService.selectExamById(eid);
		request.setAttribute("exam", exam);
		request.setAttribute("results", results);
		return "teacher_exam_status";
	}
	@RequestMapping("sendInfo")
	public String sendInfo(HttpServletRequest request,HttpServletResponse response) {
		String message = "info"+request.getParameter("eid");
		ServletContext servletContext = request.getServletContext();
		StringBuilder messages = (StringBuilder)servletContext.getAttribute(message);
		if(messages==null) {
			messages = new StringBuilder();
			servletContext.setAttribute(message, messages);
		}
		messages.append(teacher.getTname()+"老师："+request.getParameter("info")+"\n"); 
		return "redirect:examStatus"+"?eid="+request.getParameter("eid");
	}
	@RequestMapping("getInfo")
	public void getInfo(HttpServletRequest request,HttpServletResponse response) {
		String message = "info"+request.getParameter("eid");
		ServletContext servletContext = request.getServletContext();
		StringBuilder messages = (StringBuilder)servletContext.getAttribute(message);
		if(messages==null) {
			messages = new StringBuilder();
		}
		try {
			PrintWriter out = response.getWriter();
			out.write(messages.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("examFinish")
	public String examFinish(HttpServletRequest request) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		Exam exam = examService.selectExamById(eid);
		exam.setEactive(false);
		exam.setEfinish(true);
		ServletContext servletContext = request.getServletContext();
		Object sb = servletContext.getAttribute("infp"+eid);
		if(sb!=null) {
			servletContext.removeAttribute("infp"+eid);
		}
		try {
			examService.updateExam(exam);
			return "redirect:examManager";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:examManager";
		}
	}
	@RequestMapping("downloadPapers")
	public void downloadPapers(HttpServletRequest request,HttpServletResponse response) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		Exam exam = examService.selectExamById(eid);
		String rootPath = request.getServletContext().getRealPath("files");
		String examdir = rootPath + File.separator + teacher.getTid() + File.separator + eid;
		String zipFile = rootPath + File.separator + teacher.getTid() + File.separator + exam.getEname() + "["+eid+"]" + ".zip";
		teacherService.zipExam(examdir, zipFile);
		File file = new File(zipFile);
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
	@RequestMapping("grade")
	public String grade(HttpServletRequest request) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		List<Result> results = resultService.selectResultByEid(eid);
		Exam exam = examService.selectExamById(eid);
		request.setAttribute("exam", exam);
		request.setAttribute("results", results);
		return "teacher_grade";
	}
	@RequestMapping("importGrade")
	public String importGrade(HttpServletRequest request) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		Exam exam = examService.selectExamById(eid);
		List<Result> results = resultService.selectResultByEid(eid);
		try {
			for (Result result : results) {
				double grade = Double.parseDouble(request.getParameter(String.valueOf(result.getRid())));
				result.setGrade(grade);
				resultService.updateResultById(result);
			}
			exam.setEarchive(true);
			examService.updateExam(exam);
			request.setAttribute("exam", exam);
			request.setAttribute("results", results);
			request.setAttribute("successCode", "保存成功");
			return "teacher_grade";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("exam", exam);
			request.setAttribute("results", results);
			request.setAttribute("errorCode", "保存失败");
			return "teacher_grade";
		}
	}
	@RequestMapping("clearExam")
	public String clearExam(HttpServletRequest request) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		Exam exam = examService.selectExamById(eid);
		String rootPath = request.getServletContext().getRealPath("files");
		String examdir = rootPath + File.separator + teacher.getTid() + File.separator + eid;
		try {
			teacherService.deleteFile(examdir);
			exam.setEcleared(true);
			examService.updateExam(exam);
			return "redirect:examManager";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:examManager";
		}
	}
	
	
	
	
	
	@RequestMapping("tPwd")
	public String tpwd(HttpServletRequest request) {
		return "teacher_pwd";
	}
	@RequestMapping("tUpdatePwd")
	public String tUpdatePwd(HttpServletRequest request) {
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd1");
		oldpwd = DigestUtils.md5DigestAsHex(oldpwd.getBytes());
		newpwd = DigestUtils.md5DigestAsHex(newpwd.getBytes());
		if(teacher.getTpwd().equals(oldpwd)) {
			try {
				teacher.setTpwd(newpwd);
				if(teacherService.updateTeacherById(teacher)>0) {
					return "redirect:tMain";
				}else {
					request.setAttribute("errorCode", "修改失败");
					return "forward:tPwd";
				}
			} catch (Exception e) {
				request.setAttribute("errorCode", "修改失败");
				return "forward:tPwd";
			}
		}else {
			request.setAttribute("errorCode", "原密码不匹配");
			return "forward:tPwd";
		}
	}
	@RequestMapping("tLogout")
	public String tlogout(HttpServletRequest request) {
		logger.debug(((Teacher)request.getSession().getAttribute("teacher")).getTid()+"退出系统");
		request.getSession().invalidate();
		return "redirect:login.jsp";
	}
}
