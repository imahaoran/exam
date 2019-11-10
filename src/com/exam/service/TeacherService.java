package com.exam.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.exam.pojo.Teacher;

public interface TeacherService {
	//��ȡ���н�ʦ��Ϣ
	List<Teacher> selectAllTeacher();
	//���ݽ�ʦ�Ż�ȡ��ʦ��Ϣ
	Teacher selectTeacherById(String tid);
	//��ӽ�ʦ
	int insertTeacher(Teacher teacher) throws Exception;
	//�޸Ľ�ʦ��Ϣ
	int updateTeacherById(Teacher teacher) throws Exception;
	//ɾ����ʦ
	int deleteTeacherById(String tid)throws Exception;
	//�ϴ��Ծ�
	void upLoadPaper(int eid,String path,MultipartFile file)throws Exception;
}
