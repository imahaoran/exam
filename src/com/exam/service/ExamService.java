package com.exam.service;

import java.util.List;

import com.exam.pojo.Exam;
import com.exam.pojo.Student;

public interface ExamService {
	//获取所有考试信息
	List<Exam> selectAllExam();
	//根据id获取考试信息
	Exam selectExamById(int eid);
	//添加考试
	int insertExam(Exam exam) throws Exception;
	//删除考试
	int deleteExamById(int eid) throws Exception;
	//更新考试信息
	int updateExam(Exam exam) throws Exception;
	//根据考试id获取参与考试学生
	List<Student> selectStudents(int eid) throws Exception;
}
