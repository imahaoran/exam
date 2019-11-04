package com.exam.service;

import java.util.List;

import com.exam.pojo.Student;

public interface StudentService {
	//获取所有学生信息
	List<Student> selectAllStudent();
	//根据学号获取学生信息
	Student selectStudentById(String sid);
	//添加学生
	int insertStudent(Student student) throws Exception;
	//更改学生信息
	int updateStudentById(Student student) throws Exception;
	//删除学生
	int deleteStudentById(String sid) throws Exception;
}
