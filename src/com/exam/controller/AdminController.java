package com.exam.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.exam.pojo.Exam;
import com.exam.pojo.PageInfo;
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
		logger.debug("----------"+request.getParameter("username")+"�����¼");
		Teacher teacher = teacherService.selectTeacherById(request.getParameter("username"));
		String password = request.getParameter("password");
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		if(teacher!=null&&teacher.isTadmin()) {
			if(teacher.getTpwd().equals(password)) {
				logger.debug("----------��¼�ɹ�");
				request.getSession().setAttribute("admin", teacher);
				return "redirect:aMain";
			}else {
				logger.debug("----------��¼ʧ�ܣ��������");
				request.setAttribute("errorCode", "�������");
				return "forward:login.jsp";
			}
		}else {
			logger.debug("----------��¼ʧ�ܣ��û������ڣ�");
			request.setAttribute("errorCode", "�ù���Ա������");
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
//		List<Teacher> teachers = teacherService.selectAllTeacher();
		PageInfo pageInfo = new PageInfo();
		String pageNumberString = request.getParameter("pageNumber");
		if(pageNumberString!=null) {
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			pageInfo.setPageNumber(pageNumber);
		}
		pageInfo = teacherService.selectByPage(pageInfo);
		request.setAttribute("pageInfo", pageInfo);
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
		teacher.setTpwd(DigestUtils.md5DigestAsHex(request.getParameter("tpwd").getBytes()));
		teacher.setTadmin(Boolean.parseBoolean(request.getParameter("tadmin")));
		try {
			if(teacherService.insertTeacher(teacher)>0){
				return "redirect:tManager";
			}else {
				request.setAttribute("errorCode", "����ʧ��");
				return "forward:tManager";
			}
		} catch (Exception e) {
			request.setAttribute("errorCode", "����ʧ��");
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
		String password = request.getParameter("tpwd");
		if(password.length()==32) {
			
		}else {
			password = DigestUtils.md5DigestAsHex(password.getBytes());
		}
		teacher.setTpwd(password);
		teacher.setTadmin(Boolean.parseBoolean(request.getParameter("tadmin")));
		try {
			if(teacherService.updateTeacherById(teacher)>0){
				return "redirect:tManager";
			}else {
				request.setAttribute("errorCode", "����ʧ��");
				request.setAttribute("tid", teacher.getTid());
				return "forward:editTeacher";
			}
		} catch (Exception e) {
			request.setAttribute("errorCode", "����ʧ��");
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
				request.setAttribute("errorCodeDel", "ɾ��ʧ��");
				return "forward:tManager";
			}
		} catch (Exception e) {
			request.setAttribute("errorCodeDel", "ɾ��ʧ��");
			return "forward:tManager";
		}
	}
	
	@RequestMapping("sManager")
	public String studentManager(HttpServletRequest request) {
//		List<Student> students = studentService.selectAllStudent();
		PageInfo pageInfo = new PageInfo();
		String pageNumberString = request.getParameter("pageNumber");
		if(pageNumberString!=null) {
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			pageInfo.setPageNumber(pageNumber);
		}
		pageInfo = studentService.selectByPage(pageInfo);
		request.setAttribute("pageInfo", pageInfo);
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
		student.setSpwd(DigestUtils.md5DigestAsHex(request.getParameter("spwd").getBytes()));
		try {
			if(studentService.insertStudent(student)>0){
				return "redirect:sManager";
			}else {
				request.setAttribute("errorCode", "����ʧ��");
				return "forward:sManager";
			}
		} catch (Exception e) {
			request.setAttribute("errorCode", "����ʧ��");
			return "forward:sManager";
		}
	}
	
	@RequestMapping("upLoadSExcel")
	public String upLoadPaper(HttpServletRequest request,MultipartFile file) {
		int alRow = 0,suRow = 0;
		try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            int maxRow = sheet.getLastRowNum();
            alRow = maxRow + 1;
            System.out.println("������Ϊ��" + maxRow);
            for (int row = 0; row <= maxRow; row++) {
            	Student student = new Student();
                int maxRol = sheet.getRow(row).getLastCellNum();
                String[] strings = {null,null,null};
                for (int rol = 0; rol < 3; rol++){
                	Cell cell = sheet.getRow(row).getCell(rol);
                	CellType cellType = cell.getCellType();
                	String cellValue = null;
                	if(cellType.equals(CellType.NUMERIC)) {
                		cellValue = new DecimalFormat("#.######").format(sheet.getRow(row).getCell(rol).getNumericCellValue());
                	}
                	else {
                		cellValue = cell.toString();
					}
                    strings[rol] = cellValue;
                }
                student.setSid(strings[0]);
                student.setSname(strings[1]);
                student.setSpwd(DigestUtils.md5DigestAsHex(strings[2].getBytes()));
                try {
                	studentService.insertStudent(student);
                	suRow += 1;
				} catch (Exception e) {
					suRow += 0;
				}
            }
            if(alRow == suRow) {
            	return "forward:sManager";
            }else {
            	request.setAttribute("errorCode2", "�������ݣ�"+ suRow +"/"+ alRow +"��");
    			return "forward:sManager";
			} 
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorCode2", "�ϴ�ʧ��");
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
		String password = request.getParameter("spwd");
		if(password.length()==32) {
			
		}else {
			password = DigestUtils.md5DigestAsHex(password.getBytes());
		}
		student.setSpwd(password);
		try {
			if(studentService.updateStudentById(student)>0){
				return "redirect:sManager";
			}else {
				request.setAttribute("errorCode", "����ʧ��");
				request.setAttribute("sid", student.getSid());
				return "forward:editStudent";
			}
		} catch (Exception e) {
			request.setAttribute("errorCode", "����ʧ��");
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
				request.setAttribute("errorCodeDel", "ɾ��ʧ��");
				return "forward:sManager";
			}
		} catch (Exception e) {
			request.setAttribute("errorCodeDel", "ɾ��ʧ��");
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
		logger.debug(((Teacher)request.getSession().getAttribute("admin")).getTid()+"�˳�ϵͳ");
		request.getSession().invalidate();
		return "redirect:login.jsp";
	}
	
	@RequestMapping("aConfig")
	public String config(HttpServletRequest request) {
		return "admin_config";
	}
}
