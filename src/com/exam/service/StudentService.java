package com.exam.service;

import java.util.List;

import com.exam.pojo.Student;

public interface StudentService {
	//��ȡ����ѧ����Ϣ
	List<Student> selectAllStudent();
	//����ѧ�Ż�ȡѧ����Ϣ
	Student selectStudentById(String sid);
	//���ѧ��
	int insertStudent(Student student) throws Exception;
	//����ѧ����Ϣ
	int updateStudentById(Student student) throws Exception;
	//ɾ��ѧ��
	int deleteStudentById(String sid) throws Exception;
}
