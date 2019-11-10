package com.exam.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.exam.pojo.Teacher;

public interface TeacherService {
	//获取所有教师信息
	List<Teacher> selectAllTeacher();
	//根据教师号获取教师信息
	Teacher selectTeacherById(String tid);
	//添加教师
	int insertTeacher(Teacher teacher) throws Exception;
	//修改教师信息
	int updateTeacherById(Teacher teacher) throws Exception;
	//删除教师
	int deleteTeacherById(String tid)throws Exception;
	//上传试卷
	void upLoadPaper(int eid,String path,MultipartFile file)throws Exception;
}
