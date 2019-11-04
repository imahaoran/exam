package com.exam.service;

import java.util.List;

import com.exam.pojo.Exam;
import com.exam.pojo.Student;

public interface ExamService {
	//��ȡ���п�����Ϣ
	List<Exam> selectAllExam();
	//����id��ȡ������Ϣ
	Exam selectExamById(int eid);
	//��ӿ���
	int insertExam(Exam exam) throws Exception;
	//ɾ������
	int deleteExamById(int eid) throws Exception;
	//���¿�����Ϣ
	int updateExam(Exam exam) throws Exception;
	//���ݿ���id��ȡ���뿼��ѧ��
	List<Student> selectStudents(int eid) throws Exception;
}
